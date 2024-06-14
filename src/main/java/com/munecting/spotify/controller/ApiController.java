//package com.munecting.spotify.controller;
//
//
////import com.wrapper.spotify.SpotifyApi;
////import com.wrapper.spotify.SpotifyHttpManager;
////import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
//import com.munecting.spotify.service.GetAccess;
//import com.wrapper.spotify.exceptions.SpotifyWebApiException;
//import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.hc.core5.http.ParseException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//
//@Slf4j
//@Getter
//@RequiredArgsConstructor
//@Controller
//public class ApiController {
//    private final GetAccess getAccess;
//
//    @GetMapping("/api/callback")
//    public String api(@RequestParam String code, @RequestParam String state) throws URISyntaxException {
//        log.info("성공");
//        log.info(code);
//        log.info(state);
//        getAccess.myMethod(code);
//        return "api";
//    }
//
//    @PostMapping("/api/callback")
//    public String api() {
////        log.info(String.valueOf(code.length()));
//        return "api";
//    }
//
//    @ResponseBody
//    @PostMapping("/api/search")
//    public AlbumSimplified[] show(HttpServletRequest request) throws IOException, ParseException, SpotifyWebApiException {
//        String keyword = request.getParameter("keyword");
//
//        return getAccess.getMusics(keyword);
//    }
//}
