package com.example.backend.controller;

import com.example.backend.dto.response.TourListResponse;
import com.example.backend.dto.response.TourDetailResponse;
import com.example.backend.dto.response.TourSpeakResponse;
import com.example.backend.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TourController {

    private final TourService tourService;
    @GetMapping("/test")
    public ResponseEntity<Void> testController() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tour/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.OK).body("되라!!");
    }

    @GetMapping("/tour")
    public ResponseEntity<List<TourListResponse>> getTourList() {
        return ResponseEntity.status(HttpStatus.OK).body(tourService.getTourList());
    }

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<TourDetailResponse> getTourDetail(@PathVariable int tourId) {
        return ResponseEntity.status(HttpStatus.OK).body(tourService.getTourDetail(tourId));
    }

    @GetMapping("/tour{tourId}/speak")
    public ResponseEntity<TourSpeakResponse> getTourSpeak(@PathVariable int tourId) {
        return ResponseEntity.status(HttpStatus.OK).body(tourService.getTourSpeak(tourId));
    }

}
