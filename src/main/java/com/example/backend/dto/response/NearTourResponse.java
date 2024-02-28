package com.example.backend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NearTourResponse {
    private String name;
    private String address;
    private Integer dis;

    @Builder
    public NearTourResponse(String name, String address, Integer dis) {
        this.name = name;
        this.address = address;
        this.dis = dis;
    }
}
