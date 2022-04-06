package com.classpath.oauth2client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/userinfo")
@RequiredArgsConstructor
public class UserController {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @GetMapping
    public Map<String, String> userDetails(OAuth2AuthenticationToken oAuth2AuthenticationToken){

        OAuth2AuthorizedClient oAuth2AuthorizedClient = this.oAuth2AuthorizedClientService
                .loadAuthorizedClient(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                        oAuth2AuthenticationToken.getPrincipal().getName());

        OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("access-token", accessToken.getTokenValue());
        resultMap.put("issued-at", accessToken.getIssuedAt().toString());
        resultMap.put("expirty-at", accessToken.getExpiresAt().toString());
        return resultMap;
    }
}
