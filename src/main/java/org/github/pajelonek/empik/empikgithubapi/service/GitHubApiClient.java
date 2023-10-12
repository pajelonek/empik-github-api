package org.github.pajelonek.empik.empikgithubapi.service;

import lombok.extern.slf4j.Slf4j;
import org.github.pajelonek.empik.empikgithubapi.model.GithubApiUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Service
@Slf4j
public class GitHubApiClient {

    private static final String URL = "https://api.github.com/users/{user}";
    @Autowired
    private final RestTemplate restTemplate;

    public GitHubApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<GithubApiUserResponse> getUserInfo(final String user) {
        log.info("Sending GET request to GitHub Api /users/{user} endpoint with user: {}", user);

        URI uri = UriComponentsBuilder
                .fromUriString(URL)
                .build()
                .expand(Collections.singletonMap("user", user)).toUri();

        ResponseEntity<GithubApiUserResponse> response;

        try {
            response = restTemplate.getForEntity(uri.toString(), GithubApiUserResponse.class);
        } catch (RestClientException e) {
            log.error("Unexpected error happened during getUserInfo call");
            throw e;
        }

        log.info("GitHub Api /users/{user} response: {}", response);
        return response;
    }
}
