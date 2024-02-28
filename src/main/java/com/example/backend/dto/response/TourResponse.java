package com.example.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class TourResponse {
    private Integer id;
    private String content;
    private List<ImageResponse> images;

    @Builder
    public TourResponse(Integer id, String content, List<ImageResponse> images) {
        this.id = id;
        this.content = content;
        this.images = images;
    }
}
