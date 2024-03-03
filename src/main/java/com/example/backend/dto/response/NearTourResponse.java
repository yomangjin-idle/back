package com.example.backend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NearTourResponse {
    private String name;
    private String content;
    private String address;
    private Integer dis;
    private String imgPath;

    @Builder
    public NearTourResponse(String name, String content, String address, Integer dis, String imgPath) {
        this.name = name;
        this.content = content;
        this.address = address;
        this.dis = dis;
        this.imgPath = imgPath;
    }
}
