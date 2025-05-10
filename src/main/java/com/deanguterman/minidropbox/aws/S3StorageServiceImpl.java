package com.deanguterman.minidropbox.aws;

import com.deanguterman.minidropbox.exception.FileEmptyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

public class S3StorageServiceImpl implements S3StorageService {
    private final S3Client s3Client;

    @Value("${aws.bucket-name}")
    private String bucketName;

    public S3StorageServiceImpl(S3Client s3Client){
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFileToS3(MultipartFile file, String keyHint) throws FileEmptyException{
        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (IOException e) {
            throw new FileEmptyException("File is empty, failed to upload");
        }

        return key;
    }
}
