package com.deanguterman.minidropbox;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

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
            file.transferTo(destination);
        } catch (IOException e) {
            return "File upload failed";
        }

        FileEntity newFile = new FileEntity(file.getOriginalFilename(), destination.getAbsolutePath(), file.getSize(), LocalDateTime.now(), user);
        fileRepository.save(newFile);
        return "File uploaded successfully";
    }

    public Resource downloadFile(Long fileId) throws Exception{
        Optional<FileEntity> file = fileRepository.findById(fileId);
        if (file.isPresent()){
            String filePath = file.get().getPath();
            File actualFile = new File(filePath);
            if (!actualFile.exists()) throw new FileNotFoundException("File not found on disk");
            return new InputStreamResource(new FileInputStream(actualFile));
        } else {
            throw new Exception("File not found in database");
        }
    }
}
