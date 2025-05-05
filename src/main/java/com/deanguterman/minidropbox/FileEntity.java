package com.deanguterman.minidropbox;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String path;
    private Long size;
    private LocalDateTime uploadTime;

    @ManyToOne
    private User user;

    public FileEntity(String filename, String path, Long size, LocalDateTime uploadTime, User user){
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
