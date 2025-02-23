package com.larrykin.file_manager.controller;

import com.larrykin.file_manager.FileStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller // use @Controller instead of @RestController to return a view instead of a JSON response
public class FileManagerGuiController {

    //? upload files
    @GetMapping("/uploader")
    public String uploader(){
        return "uploader";
    }

    //? Method 1 -- to list files
    @GetMapping("/list-files")
    public String listfiles(Model model) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(new File(FileStorageService.STORAGE_DIRECTORY).toPath())) {
            model.addAttribute("files", StreamSupport.stream(stream.spliterator(), false)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList()));
        }
        return "file-list";
    }

    //? Method 2 -- to list files
    @GetMapping("/files")
    public String listFiles(Model model) {
        File folder = new File(FileStorageService.STORAGE_DIRECTORY);
        List<FileInfo> files = Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .filter(File::isFile)
                .map(file -> new FileInfo(file.getName(), file.length()))
                .collect(Collectors.toList());

        model.addAttribute("files", files);
        return "file-list";
    }

    // DTO to store file info
    static class FileInfo {
        private String name;
        private long size;

        public FileInfo(String name, long size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public long getSize() {
            return size;
        }
    }
}
