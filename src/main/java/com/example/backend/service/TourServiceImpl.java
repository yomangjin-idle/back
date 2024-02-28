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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    @Value("${naver.clientId}")
    String clientId; //애플리케이션 클라이언트 아이디값";

    @Value("${naver.clientSecret}")
    String clientSecret; //애플리케이션 클라이언트 시크릿값";

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
        List<NearTourResponse> nearTours = nearTourRepository.findByTourId(tourId).stream().map(near -> {
            return NearTourResponse.builder()
                    .name(near.getName())
                    .address(near.getAddress())
                    .dis(near.getDis()).build();
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
                .content(tour.getContent())
                .images(images)
                .build();

        return new TourDetailResponse(tourResponse, nearTours);
    }

    @Override
    public TourSpeakResponse getTourSpeak(int tourId) {
        String filePath = "";

        Tour tour = tourRepository.findById(tourId).orElseThrow(() ->
                new NoSuchElementException("해당 id의 여행이 없습니다."));

        try {
            String proxyHost = "http://krmp-proxy.9rum.cc";
            int proxyPort = 3128;

            String text = URLEncoder.encode(tour.getContent(), "UTF-8"); // 13자
            String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
            URL url = new URL(apiURL);

            // 프록시 설정
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            HttpURLConnection con = (HttpURLConnection)url.openConnection(proxy);

            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            // post request
            String postParams = "speaker=nseungpyo&volume=0&speed=0&pitch=0&format=mp3&text=" + text;

            log.info(postParams);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 mp3 파일 생성
                String tempname = tour.getId().toString();

                File dir = new File("voice");
                if(!dir.exists()) dir.mkdir();

                File f = new File(dir, tempname + ".mp3");

                if(f.exists()) {
                    filePath = f.getAbsolutePath();
                    log.info(">>> 파일이 있습니다!!");
                } else {
                    f.createNewFile();

                    OutputStream outputStream = new FileOutputStream(f);
                    while ((read =is.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }

                    filePath = f.getAbsolutePath();
                }
                is.close();

            } else {  // 오류 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return TourSpeakResponse.builder()
                .filePath(filePath).build();
    }
}
