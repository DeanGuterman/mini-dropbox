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
}
