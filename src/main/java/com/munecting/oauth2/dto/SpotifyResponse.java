package com.munecting.oauth2.dto;

import com.munecting.oauth2.dto.OAuth2Response;

import java.util.Map;

public class SpotifyResponse implements OAuth2Response {

    private final Map<String, Object> attribute;
//    @SuppressWarnings("unchecked")
    public SpotifyResponse(Map<String, Object> attributes) {

        this.attribute = attributes;
//        this.attribute = attribute;
    }

//    @Override
//    public String getProvider() {
//
//        return "spotify";
//    }
//
//    @Override
//    public String getProviderId() {
//
//        return attribute.get("id").toString();
//    }
//
//    @Override
//    public String getEmail() {
//
//        return attribute.get("email").toString();
//    }

    @Override
    public String getName() {

        return attribute.get("name").toString();
    }
}
