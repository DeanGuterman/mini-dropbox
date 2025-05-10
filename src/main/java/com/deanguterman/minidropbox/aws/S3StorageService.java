package com.deanguterman.minidropbox.aws;

import org.springframework.web.multipart.MultipartFile;

public interface S3StorageService {
    String uploadFileToS3(MultipartFile file, String key);
}
