package com.larrykin.file_manager;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;


@Service
public class FileStorageService {

    private static final String STORAGE_DIRECTORY = "C:\\Users\\USER\\Documents\\Storage";

    public void saveFile(MultipartFile fileToSave) throws IOException {
        if (fileToSave == null) {
            throw new NullPointerException("File to save is null");
        }
        var targetFile = new File(STORAGE_DIRECTORY + File.separator + fileToSave.getOriginalFilename());
        //secure the filename from hackers
        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Unsupported filename!");
        }
        Files.copy(fileToSave.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public File getDownloadFile(String filename) throws Exception {
        if (filename == null) {
            throw new NullPointerException("fileName is null");
        }
        var fileToDownload = new File(STORAGE_DIRECTORY + File.separator + filename);
        //secure the filename from hackers
        if (!Objects.equals(fileToDownload.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Unsupported filename!");
        }
        if (!fileToDownload.exists()) {
            throw new FileNotFoundException("No file Named: " + filename);
        }
        return fileToDownload;
    }
}






