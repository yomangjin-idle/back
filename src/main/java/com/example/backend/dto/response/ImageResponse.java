package com.example.backend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageResponse {
    private Integer id;
    private String content;
    private String imgPath;

    @Builder
    public ImageResponse(Integer id, String content, String imgPath) {
        this.id = id;
        this.content = content;
        this.imgPath = imgPath;
    }
}
