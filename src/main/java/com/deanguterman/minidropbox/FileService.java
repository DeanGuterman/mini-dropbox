package com.deanguterman.minidropbox;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    public String uploadFile(MultipartFile file) {

    }
}
