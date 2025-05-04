package com.deanguterman.minidropbox;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository for the FileEntity class
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    // JPA automatically implements these functions
    List<FileEntity> findByUser(User user);
}
