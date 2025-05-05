package com.deanguterman.minidropbox;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    public String uploadFile(MultipartFile file, User user) {
        // If the uploads folder does not exist, create one
        File uploadsFolder = new File("Uploads");
        if (!uploadsFolder.exists()) uploadsFolder.mkdir();

        File destination = new File(uploadsFolder, file.getOriginalFilename());
        try{
            file.transferTo(uploadsFolder);
        } catch (IOException e) {
            return "File upload failed";
        }

        FileEntity newFile = new FileEntity();

        fileRepository.save(newFile);
    }
}
