package org.github.pajelonek.empik.empikgithubapi.service;

import lombok.extern.slf4j.Slf4j;
import org.github.pajelonek.empik.empikgithubapi.model.DefaultException;
import org.github.pajelonek.empik.empikgithubapi.model.Error;
import org.github.pajelonek.empik.empikgithubapi.model.github.UserInfoResponse;
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
    private final RestTemplate restTemplate;

    public GitHubApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<UserInfoResponse> getUserInfo(final String user) throws DefaultException {
        log.info("Sending GET request to GitHub Api /users/{user} endpoint with user: {}", user);

        URI uri = UriComponentsBuilder
                .fromUriString(URL)
                .build()
                .expand(Collections.singletonMap("user", user)).toUri();

        ResponseEntity<UserInfoResponse> response;

        try {
            response = restTemplate.getForEntity(uri.toString(), UserInfoResponse.class);
        } catch (RestClientException e) {
            log.error("Unexpected error happened during getUserInfo call");
            throw new DefaultException(Error.USERINFO_GITHUB_API_EXCEPTION_ERROR);
        }

        log.info("GitHub Api /users/{user} response: {}", response);
        return response;
    }
}
