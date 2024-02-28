package com.example.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TourSpeakResponse {
    private String filePath;

    @Builder
    public TourSpeakResponse(String filePath) {
        this.filePath = filePath;
    }
}
