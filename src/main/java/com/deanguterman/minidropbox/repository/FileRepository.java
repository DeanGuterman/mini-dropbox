package com.deanguterman.minidropbox.repository;

import com.deanguterman.minidropbox.entity.StoredFile;
import com.deanguterman.minidropbox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JPA repo for saving and finding uploaded files
public interface FileRepository extends JpaRepository<StoredFile, Long> {
    List<StoredFile> findByUser(User user);
}
