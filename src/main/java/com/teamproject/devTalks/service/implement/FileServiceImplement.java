package com.teamproject.devTalks.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teamproject.devTalks.service.FileService;

@Service
public class FileServiceImplement implements FileService{

    @Value("${file.path}") 
    private String FILE_PATH;
    @Value("${file.url}") 
    private String FILE_URL;

    @Override
    public String upload(MultipartFile file) {
        
        if (file.isEmpty()) {
            return null;
        }

        String OriginalfileName = file.getOriginalFilename();

        int extensionIndex = OriginalfileName.lastIndexOf(".");
        String extension = OriginalfileName.substring(extensionIndex);

        String fileUuid = UUID.randomUUID().toString();
        String saveName = fileUuid + extension;

        String savePath = FILE_PATH + saveName;


        try {
            file.transferTo(new File(savePath));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        String fileUrl = FILE_URL + saveName;
        return fileUrl;        

    }

    @Override
    public Resource getFile(String fileName) {
        
        Resource file = null;

        try {

            String url = "file:" + FILE_PATH + fileName;
            file = new UrlResource(url);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return file;
    }
    
}
