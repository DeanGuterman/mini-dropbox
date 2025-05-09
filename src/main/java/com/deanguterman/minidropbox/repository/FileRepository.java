package com.deanguterman.minidropbox.repository;

import com.deanguterman.minidropbox.entity.StoredFile;
import com.deanguterman.minidropbox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository for the FileEntity class
public interface FileRepository extends JpaRepository<StoredFile, Long> {
    // JPA automatically implements these functions
    List<StoredFile> findByUser(User user);
}
