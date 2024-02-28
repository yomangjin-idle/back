package com.example.backend.service;

import com.example.backend.dto.response.*;
import com.example.backend.entity.NearTour;
import com.example.backend.entity.SpeakFile;
import com.example.backend.entity.Tour;
import com.example.backend.repository.NearTourRepository;
import com.example.backend.repository.SpeakFileRepository;
import com.example.backend.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final NearTourRepository nearTourRepository;
    private final SpeakFileRepository speakFileRepository;

    @Override
    public List<TourListResponse> getTourList() {
        List<Tour> tours = tourRepository.findAll();

        List<TourListResponse> tourListResponse = tours.stream().map(tour -> {
            return TourListResponse.builder()
                    .id(tour.getId())
                    .x(tour.getX())
                    .y(tour.getY())
                    .address(tour.getAddress())
                    .imgPath(tour.getImgPath())
                    .name(tour.getName())
                    .build();
        }).collect(Collectors.toList());

        return tourListResponse;
    }

    @Override
    public TourDetailResponse getTourDetail(int tourId) {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() ->
                new NoSuchElementException("해당 id의 여행이 없습니다."));

        List<NearTour> test = nearTourRepository.findByTourId(tourId);
        log.info("===========================");
        log.info(String.valueOf(test.size()));
        List<NearTourResponse> nearTours = nearTourRepository.findByTourId(tourId).stream().map(near -> {
            return NearTourResponse.builder()
                    .name(near.getName())
                    .address(near.getAddress())
                    .dis(near.getDis()).build();
        }).collect(Collectors.toList());


        log.info("========== 근처 유적지에 대한 정보입니다.");
        log.info(String.valueOf(nearTours.size()));
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
                .content(tour.getContent())
                .images(images)
                .build();

        return new TourDetailResponse(tourResponse, nearTours);
    }

    @Override
    public TourSpeakResponse getTourSpeak(int tourId) {
        SpeakFile speakFile = speakFileRepository.findByTourId(tourId);

        return TourSpeakResponse.builder()
                .filePath(speakFile.getFilePath()).build();
    }
}
