package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double x;
    private Double y;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String imgPath;
    private String address;
    @OneToMany(mappedBy = "tour")
    private List<Image> images;
    @OneToMany(mappedBy = "tour")
    private List<NearTour> nearTours;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private SpeakFile speakFile;
}
