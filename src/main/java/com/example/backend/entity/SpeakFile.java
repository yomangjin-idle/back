package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "speak_file")
public class SpeakFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String filePath;
    @OneToOne(mappedBy = "speakFile")
    private Tour tour;
}
