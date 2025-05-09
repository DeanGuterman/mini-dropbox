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
        // Always create Uploads folder at the project root (not inside temp dirs)
        String basePath = System.getProperty("user.dir"); // Gets the actual project root
        File uploadsFolder = new File(basePath, "Uploads");

        if (!uploadsFolder.exists()) {
            boolean created = uploadsFolder.mkdir();
            System.out.println("Uploads folder created: " + created);
        }

        File destination = new File(uploadsFolder, file.getOriginalFilename());
        System.out.println("Writing to: " + destination.getAbsolutePath());

        try {
            file.transferTo(destination);
            System.out.println("File saved.");
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed";
        }

        FileEntity newFile = new FileEntity(
                file.getOriginalFilename(),
                destination.getAbsolutePath(),
                file.getSize(),
                LocalDateTime.now(),
                user
        );

        fileRepository.save(newFile);
        System.out.println("File entity saved to database.");
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
