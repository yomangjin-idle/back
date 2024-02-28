package com.example.backend.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class TourDetailResponse {
    private TourResponse tourInfo;
    private List<NearTourResponse> nearToursInfo;

    public TourDetailResponse(TourResponse tourInfo, List<NearTourResponse> nearTours) {
        this.tourInfo = tourInfo;
        this.nearToursInfo = nearTours;
    }
}
