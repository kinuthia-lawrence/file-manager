package com.larrykin.file_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // use @Controller instead of @RestController to return a view instead of a JSON response
public class FileManagerGuiController {
    @GetMapping("/uploader")
    public String uploader(){
        return "uploader";
    }
}
