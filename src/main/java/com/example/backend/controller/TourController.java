package com.example.backend.controller;

import com.example.backend.dto.response.TourListResponse;
import com.example.backend.dto.response.TourDetailResponse;
import com.example.backend.dto.response.TourSpeakResponse;
import com.example.backend.service.TourService;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping("/tour")
    @Operation(summary = "메인 화면에서 여행지 리스트를 불러오는 API 입니다.")
    public ResponseEntity<List<TourListResponse>> getTourList() {
        return ResponseEntity.status(HttpStatus.OK).body(tourService.getTourList());
    }

    @GetMapping("/tour/{tourId}")
    @Operation(summary = "여행지의 상세 정보를 확인할 수 있는 API 입니다.")
    public ResponseEntity<TourDetailResponse> getTourDetail(@PathVariable int tourId) {
        return ResponseEntity.status(HttpStatus.OK).body(tourService.getTourDetail(tourId));
    }

    @GetMapping("/tour/{tourId}/speak")
    @Operation(summary = "음성파일의 경로를 반환하는 API 입니다. 음성파일은 mp4 파일입니다.")
    public ResponseEntity<TourSpeakResponse> getTourSpeak(@PathVariable int tourId) {
        return ResponseEntity.status(HttpStatus.OK).body(tourService.getTourSpeak(tourId));
    }
}
