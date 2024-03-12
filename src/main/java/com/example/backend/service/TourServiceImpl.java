package com.example.backend.service;

import com.example.backend.dto.response.*;
import com.example.backend.entity.Tour;
import com.example.backend.repository.NearTourRepository;
import com.example.backend.repository.TourRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    @Value("${naver.clientId}")
    String clientId; //애플리케이션 클라이언트 아이디값";

    @Value("${naver.clientSecret}")
    String clientSecret; //애플리케이션 클라이언트 시크릿값";

    private final TourRepository tourRepository;
    private final NearTourRepository nearTourRepository;

    @Override
    public List<TourListResponse> getTourList() {
        List<Tour> tours = tourRepository.findAll();

        List<TourListResponse> tourListResponse =
                tours.stream().map(tour -> TourListResponse.builder()
                        .id(tour.getId())
                        .address(tour.getAddress())
                        .name(tour.getName())
                        .build()).collect(Collectors.toList());

        return tourListResponse;
    }

    @Override
    public TourDetailResponse getTourDetail(int tourId) {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() ->
                new NoSuchElementException("해당 id의 여행이 없습니다."));

        List<NearTourResponse> nearTours = nearTourRepository.findByTourId(tourId).stream().map(near -> {
            return NearTourResponse.builder()
                    .name(near.getName())
                    .content(near.getContent())
                    .address(near.getAddress())
                    .dis(near.getDis())
                    .imgPath(near.getImgPath()).build();
        }).collect(Collectors.toList());

        for(NearTourResponse n: nearTours) {
            log.info(n.toString());
        }

        List<ImageResponse> images = tour.getImages().stream().map(image -> {
            return ImageResponse.builder()
                    .id(image.getId())
                    .imgPath(image.getImgPath())
                    .content(image.getContent())
                    .build();
        }).collect(Collectors.toList());

        TourResponse tourResponse = TourResponse.builder()
                .id(tour.getId())
                .name(tour.getName())
                .address(tour.getAddress())
                .latitude(tour.getLatitude())
                .longitude(tour.getLongitude())
                .outline(tour.getOutline())
                .content(tour.getContent())
                .images(images)
                .build();

        return new TourDetailResponse(tourResponse, nearTours);
    }

    @Override
    public TourSpeakResponse getTourSpeak(int tourId) {

        Tour tour = tourRepository.findById(tourId).orElseThrow(() ->
                new NoSuchElementException("해당 id의 여행이 없습니다."));
        String filePath = tour.getSpeakFilePath();

        return TourSpeakResponse.builder()
                .filePath(filePath).build();
    }
}
