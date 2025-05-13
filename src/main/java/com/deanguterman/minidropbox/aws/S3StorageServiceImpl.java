package com.deanguterman.minidropbox.aws;

import com.deanguterman.minidropbox.exception.FileDoesntExistException;
import com.deanguterman.minidropbox.exception.FileEmptyException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    public InputStream downloadFileFromS3(String s3key) throws FileNotFoundException {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3key)
                    .build();
            ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(getObjectRequest);
            return s3Object;
        } catch (Exception e) {
            throw new FileNotFoundException("File not found in S3: " + s3key);
        }
    }
}
