package com.deanguterman.minidropbox.service;

import com.deanguterman.minidropbox.entity.StoredFile;
import com.deanguterman.minidropbox.entity.User;
import com.deanguterman.minidropbox.repository.FileRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


// Business logic for registration and login (hashing, checking DB, returning JWT)
@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    public String uploadFile(MultipartFile file, User user, String s3Key) {
        StoredFile newFile = new StoredFile(
                file.getOriginalFilename(),
                s3Key,
                file.getSize(),
                LocalDateTime.now(),
                user
        );

        fileRepository.save(newFile);
        System.out.println("File entity saved to database.");
        return "File uploaded successfully";
    }

    public String getS3KeyFromId(Long id){
        return fileRepository.getReferenceById(id).getS3Key();
    }

//    public Resource downloadFile(Long fileId) throws Exception{
//        Optional<StoredFile> file = fileRepository.findById(fileId);
//        if (file.isPresent()){
//            String filePath = file.get().getPath();
//            File actualFile = new File(filePath);
//            if (!actualFile.exists()) throw new FileNotFoundException("File not found on disk");
//            return new InputStreamResource(new FileInputStream(actualFile));
//        } else {
//            throw new Exception("File not found in database");
//        }
//    }

    public String deleteFile(Long fileId) throws FileNotFoundException {
        StoredFile file = fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));

        fileRepository.delete(file);
        return file.getS3Key();
    }

    public List<StoredFile> getAllFiles(){
        return fileRepository.findAll();
    }
}
