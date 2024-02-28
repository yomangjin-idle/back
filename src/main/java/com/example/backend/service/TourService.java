package com.example.backend.service;

import com.example.backend.dto.response.TourListResponse;
import com.example.backend.dto.response.TourDetailResponse;
import com.example.backend.dto.response.TourSpeakResponse;

import java.util.List;

public interface TourService {
    List<TourListResponse> getTourList();
    TourDetailResponse getTourDetail(int tourId);
    TourSpeakResponse getTourSpeak(int tourId);
}
