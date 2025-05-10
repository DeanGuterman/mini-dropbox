package com.deanguterman.minidropbox.aws;

import org.springframework.web.multipart.MultipartFile;

public interface S3StorageService {
    String uploadFile(MultipartFile file, String key);
}
