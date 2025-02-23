package com.larrykin.file_manager.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileManagerController {

    @PostMapping("/upload-file")
    public void uploadFile(@RequestParam("file") MultipartFile file) {

    }

}
