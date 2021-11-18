package com.myGallary.mycontroller;


import com.myGallary.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequiredArgsConstructor
@Controller
public class TestController {

    private final S3Uploader s3Uploader;

    @GetMapping("/test")
    public String index() {
        return "test";
    }

    @PostMapping("/upload")
    public String upload(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        System.out.println("file name " + multipartFile.getName());

        return s3Uploader.upload(multipartFile, "static");
    }
}