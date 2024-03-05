package com.example.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class TourResponse {
    private Integer id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String content;
    private List<ImageResponse> images;

    @Builder
    public TourResponse(Integer id, String name, String address, Double latitude, Double longitude, String content, List<ImageResponse> images) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.content = content;
        this.images = images;
    }
}
