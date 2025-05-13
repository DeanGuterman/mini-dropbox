package com.deanguterman.minidropbox.aws;

import com.deanguterman.minidropbox.exception.FileEmptyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface S3StorageService {
    String uploadFileToS3(MultipartFile file, String key);
    InputStream downloadFileFromS3(String s3key) throws FileNotFoundException;
    void deleteFileFromS3(String s3Key);

}
