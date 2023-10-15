package org.github.pajelonek.empik.empikgithubapi.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.github.pajelonek.empik.empikgithubapi.dao.UserDao;
import org.github.pajelonek.empik.empikgithubapi.model.UserEntity;
import org.github.pajelonek.empik.empikgithubapi.model.DefaultException;
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

    final UserDao userDao;

    public UserService(GitHubApiClient client, UserDao userDao) {
        this.client = client;
        this.userDao = userDao;
    }

    @Transactional(rollbackOn = {DefaultException.class})
    public ResponseEntity<UserResponse> getUserInfo(final String user) throws DefaultException {
        ResponseEntity<UserInfoResponse> gitHubResponse = client.getUserInfo(user);
        UserEntity userEntity = userDao.getUserWithPessimisticWriteLock(user);
        userDao.incrementRequestCount(userEntity);
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
