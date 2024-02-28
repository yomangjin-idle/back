package com.example.backend.repository;

import com.example.backend.entity.SpeakFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakFileRepository extends JpaRepository<SpeakFile, Integer> {
    SpeakFile findByTourId(int tourId);
}
