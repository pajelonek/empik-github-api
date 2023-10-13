package org.github.pajelonek.empik.empikgithubapi.web;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.github.pajelonek.empik.empikgithubapi.model.GithubApiUserResponse;
import org.github.pajelonek.empik.empikgithubapi.service.GitHubApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    GitHubApiClient client;

    @GetMapping("/{user}")
    public ResponseEntity<GithubApiUserResponse> user(@PathVariable @NonNull String user) {
        return client.getUserInfo(user);
    }
}
