package com.example.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TourSpeakResponse {
    private String filePath;
    private String script;

    @Builder
    public TourSpeakResponse(String filePath, String script) {
        this.filePath = filePath;
        this.script = script;
    }
}
