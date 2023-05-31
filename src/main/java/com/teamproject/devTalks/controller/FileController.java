package com.teamproject.devTalks.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teamproject.devTalks.common.constant.RequestPattern;
import com.teamproject.devTalks.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(RequestPattern.FILE_API)
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    private final String UPLOAD_URL = "upload";
    private final String GET_URL = "{fileName}";

    @PostMapping(UPLOAD_URL)
    public String upload(
            @RequestParam("file") MultipartFile file) {
        return fileService.upload(file);
    }

    @GetMapping(value = GET_URL, produces = { MediaType.ALL_VALUE })
    public Resource getFile(
            @PathVariable("fileName") String fileName) {

        return fileService.getFile(fileName);

    }
}
