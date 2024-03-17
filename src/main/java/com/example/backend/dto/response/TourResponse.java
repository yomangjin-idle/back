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
    private String outline;
    private String content;
    private String script;
    private List<ImageResponse> images;

    @Builder
    public TourResponse(Integer id, String name, String address, Double latitude, Double longitude, String outline, String content, String script, List<ImageResponse> images) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.outline = outline;
        this.content = content;
        this.script = script;
        this.images = images;
    }
}
