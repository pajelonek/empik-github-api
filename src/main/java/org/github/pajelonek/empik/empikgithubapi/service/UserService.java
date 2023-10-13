package org.github.pajelonek.empik.empikgithubapi.service;

import lombok.extern.slf4j.Slf4j;
import org.github.pajelonek.empik.empikgithubapi.model.UserResponse;
import org.github.pajelonek.empik.empikgithubapi.model.github.UserInfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@Slf4j
public class UserService {

    final GitHubApiClient client;

    public UserService(GitHubApiClient client) {
        this.client = client;
    }

    public ResponseEntity<UserResponse> getUserInfo(final String user) {
        ResponseEntity<UserInfoResponse> gitHubResponse = client.getUserInfo(user);
        UserResponse response = mapUserInfoRespFromGitHubResp(Objects.requireNonNull(gitHubResponse.getBody()));
        return ResponseEntity.ok(response);
    }

    private UserResponse mapUserInfoRespFromGitHubResp(UserInfoResponse githubResp) {
        return UserResponse.builder()
                .id(githubResp.getId())
                .login(githubResp.getLogin())
                .name(githubResp.getName())
                .type(githubResp.getType())
                .avatarUrl(githubResp.getAvatarUrl())
                .createdAt(githubResp.getCreatedAt())
                .calculations(createUserCalculations(githubResp.getFollowers(), githubResp.getPublicRepos()))
                .build();
    }

    private BigDecimal createUserCalculations(int followers, int publicRepos) {
        if (followers == 0) return BigDecimal.ZERO;

        return BigDecimal.valueOf(6.0 / followers * (2 + publicRepos));
    }
}
