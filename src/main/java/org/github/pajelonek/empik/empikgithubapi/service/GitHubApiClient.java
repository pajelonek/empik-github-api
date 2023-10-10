package org.github.pajelonek.empik.empikgithubapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.pajelonek.empik.empikgithubapi.model.GithubApiUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class GitHubApiClient {

    private static final String URL = "https://api.github.com/users/{user}";

    private final RestTemplate restTemplate;

    public ResponseEntity<GithubApiUserResponse> getUserInfo(final String user) {
        log.info("Sending GET request to GitHub Api /users/{user} endpoint with user: {}", user);

        URI uri = UriComponentsBuilder
                .fromUriString(URL)
                .build()
                .expand(Collections.singletonMap("user", user)).toUri();

        final ResponseEntity<GithubApiUserResponse> response = restTemplate
                .getForEntity(uri, GithubApiUserResponse.class);

        log.info("GitHub Api /users/{user} response: {}", response);
        return response;
    }
}
