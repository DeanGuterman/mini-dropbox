package com.deanguterman.minidropbox;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

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
}
