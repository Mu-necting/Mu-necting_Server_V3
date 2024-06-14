//package com.munecting.spotify.service;
//
//import com.munecting.spotify.dto.ResponseDto;
//import com.neovisionaries.i18n.CountryCode;
//import com.wrapper.spotify.SpotifyApi;
//import com.wrapper.spotify.exceptions.SpotifyWebApiException;
//import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
//import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
//import com.wrapper.spotify.model_objects.specification.Paging;
//import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
//import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumsRequest;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.hc.core5.http.ParseException;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Objects;
//
//
//@Slf4j
//@Getter
//@RequiredArgsConstructor
//@Service
//public class GetAccess {
//
//    private final RestTemplate restTemplate;
//    private final String Client_id = "9ca92bd9257842e6972be816bbc63ad0";
//    private final String Client_secret_id = "d7df4ed7dc4d438f9459b9aec2d3dbf0";
//    private String accessToken = "";
//
//    public void myMethod(String code) throws URISyntaxException {
//        Base64.Encoder encoder = Base64.getEncoder();
//
//        //requestEntity to get accessToken
//
//        //request header
//        MultiValueMap<String,String> head = new LinkedMultiValueMap<>();
//
//        String auth = Client_id + ":" + Client_secret_id;
//        byte[] auth_byte = auth.getBytes();
//        String auth_string = encoder.encodeToString(auth_byte);
//        String input = "Basic "+auth_string;
//        log.info(input);
//
//
//        head.add("Content-Type", "application/x-www-form-urlencoded");
//        head.add("Authorization", input);
//
//        log.info(head.toString());
//        //request body
//
//        String body_string = "grant_type=authorization_code&code="+code+"&redirect_uri=http://localhost:8080/api/callback";
//        log.info("string1 = "+body_string);
//
//
//        URI uri = new URI("https://accounts.spotify.com/api/token");
//        RequestEntity<String> requestEntity = new RequestEntity<>(body_string, head, HttpMethod.POST, uri);
//        log.info(requestEntity.toString());
//
//        ResponseEntity<ResponseDto> response = restTemplate.exchange(requestEntity, ResponseDto.class);
//        log.info(response.toString());
//
//        accessToken = Objects.requireNonNull(response.getBody()).getAccess_token();
//
//    }
//
//
//    public String getAccessToken(){
//        final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(Client_id).setClientSecret(this.getClient_secret_id()).build();
//
//        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
//        try {
//            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
//            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
//            return spotifyApi.getAccessToken();
//        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
//            System.out.println("Error: " + e.getMessage());
//            return "error";
//        }
//    }
//
//    public AlbumSimplified[] getMusics(String keyword) throws IOException, ParseException, SpotifyWebApiException {
//        SpotifyApi spotifyApi = new SpotifyApi.Builder()
//                .setAccessToken(accessToken)
//                .build();
//        SearchAlbumsRequest searchAlbumsRequest = spotifyApi.searchAlbums(keyword)
//                .market(CountryCode.SE)
//                .limit(10)
//                .offset(0)
//                .includeExternal("audio")
//                .build();
//
//        Paging<AlbumSimplified> albumSimplifiedPaging  = searchAlbumsRequest.execute();
//        AlbumSimplified[] items = albumSimplifiedPaging.getItems();
//
//        log.info(Arrays.toString(items));
//        return items;
//
//    }
//}
