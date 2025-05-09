package com.deanguterman.minidropbox.controller;

import com.deanguterman.minidropbox.service.FileService;
import com.deanguterman.minidropbox.entity.User;
import com.deanguterman.minidropbox.repository.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

// Handles file upload and download endpoints
@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileService fileService;
    private final UserRepository userRepository;

    public FileController(FileService fileService, UserRepository userRepository){
        this.fileService = fileService;
        this.userRepository = userRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file, @RequestParam String username){
        if (file == null || file.isEmpty()) return ResponseEntity.badRequest().body("File is missing");

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            fileService.uploadFile(file, user.get());
            return ResponseEntity.ok("File uploaded successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFIle(@PathVariable Long fileId){
        try{
            Resource fileResource = fileService.downloadFile(fileId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                    .body(fileResource);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
