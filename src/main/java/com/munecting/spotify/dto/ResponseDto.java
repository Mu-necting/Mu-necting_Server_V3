package com.munecting.spotify.dto;


import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ResponseDto {
    private String access_token;
    private String token_type;
    private String scope;
    private Integer expires_in;
    private String refresh_token;
}
