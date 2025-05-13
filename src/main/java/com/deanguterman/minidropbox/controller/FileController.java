package com.deanguterman.minidropbox.controller;

import com.deanguterman.minidropbox.aws.S3StorageService;
import com.deanguterman.minidropbox.aws.S3StorageServiceImpl;
import com.deanguterman.minidropbox.exception.FileEmptyException;
import com.deanguterman.minidropbox.service.FileService;
import com.deanguterman.minidropbox.entity.User;
import com.deanguterman.minidropbox.repository.UserRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

// Handles file upload and download endpoints
@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileService fileService;
    private final UserRepository userRepository;
    private final S3StorageService s3StorageService;

    public FileController(FileService fileService, UserRepository userRepository, S3StorageService s3StorageService){
        this.fileService = fileService;
        this.userRepository = userRepository;
        this.s3StorageService = s3StorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file, @RequestParam String username){

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            try {
                String s3key = s3StorageService.uploadFileToS3(file, username);
                fileService.uploadFile(file, user.get(), s3key);
                return ResponseEntity.ok("File uploaded successfully");
            } catch (FileEmptyException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id){
        try{
            String s3key = fileService.getS3KeyFromId(id);
            InputStream inputStream = s3StorageService.downloadFileFromS3(s3key);
            InputStreamResource resource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + s3key + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (FileNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        try {
            String s3key = fileService.deleteFile(id);
            s3StorageService.deleteFileFromS3(s3key);
            return ResponseEntity.ok("File deleted successfully");
        } catch (FileNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete file");
        }
    }

//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<Resource> downloadFIle(@PathVariable Long fileId){
//        try{
//            Resource fileResource = fileService.downloadFile(fileId);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
//                    .body(fileResource);
//        } catch(Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
}
