package com.larrykin.file_manager.controller;

import com.larrykin.file_manager.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class FileManagerController {

    @Autowired
    private FileStorageService fileStorageService;
    private static final Logger log = Logger.getLogger(FileManagerController.class.getName());

    @PostMapping("/upload-file")
    public boolean uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileStorageService.saveFile(file);
            return true;
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error occurred while saving file", e);
        }
        return false;
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("filename") String filename) {
        log.log(Level.INFO, "[SLOWER] Downloading using /download -slower"); //when using IDM, you will see this log 4 times but three will be rejected, only one will be accepted hence downloading as one chunk
        try {
            var fileToDownload = fileStorageService.getDownloadFile(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentLength(fileToDownload.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(Files.newInputStream(fileToDownload.toPath())));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**/
    @GetMapping("/download-faster")
    public ResponseEntity<Resource> downloadFileFaster(@RequestParam("filename") String filename) {
        log.log(Level.INFO, "[FASTER] Downloading with /download-faster"); //if you use IDM, you will see this log 4 times successively hence downloading as 4 chunks
        try {
            var fileToDownload = fileStorageService.getDownloadFile(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentLength(fileToDownload.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new FileSystemResource(fileToDownload)); //? this allows downloading file in chunks, similar to multiTasking
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}



























