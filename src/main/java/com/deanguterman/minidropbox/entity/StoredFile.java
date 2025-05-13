package com.deanguterman.minidropbox.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// Represents an uploaded file linked to a user
@Entity
public class StoredFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String s3key;
    private Long size;
    private LocalDateTime uploadTime;

    @ManyToOne
    private User user;

    public StoredFile(){}

    public StoredFile(String filename, String s3key, Long size, LocalDateTime uploadTime, User user){
        this.filename = filename;
        this.s3key = s3key;
        this.size = size;
        this.uploadTime = uploadTime;
        this.user = user;
    }

    // Getters
    public String getFilename(){return filename;}
    public String getS3Key(){return s3key;}
    public Long getSize(){return size;}
    public LocalDateTime getUploadTime(){return uploadTime;}
    public User getUser(){return user;}

    // Setters
    public void setFilename(String filename){this.filename = filename;}
    public void setS3Key(String s3key){this.s3key = s3key;}
    public void setSize(Long size){this.size = size;}
    public void setUploadTime(LocalDateTime uploadTime){this.uploadTime = uploadTime;}
    public void setUser(User user){this.user = user;}
}
