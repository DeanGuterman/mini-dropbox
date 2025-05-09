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
    private String path;
    private Long size;
    private LocalDateTime uploadTime;

    @ManyToOne
    private User user;

    public StoredFile(){}

    public StoredFile(String filename, String path, Long size, LocalDateTime uploadTime, User user){
        this.filename = filename;
        this.path = path;
        this.size = size;
        this.uploadTime = uploadTime;
        this.user = user;
    }

    // Getters
    public String getFilename(){return filename;}
    public String getPath(){return path;}
    public Long getSize(){return size;}
    public LocalDateTime getUploadTime(){return uploadTime;}
    public User getUser(){return user;}

    // Setters
    public void setFilename(String filename){this.filename = filename;}
    public void setPath(String path){this.path = path;}
    public void setSize(Long size){this.size = size;}
    public void setUploadTime(LocalDateTime uploadTime){this.uploadTime = uploadTime;}
    public void setUser(User user){this.user = user;}
}
