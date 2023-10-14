package org.github.pajelonek.empik.empikgithubapi.service;

import org.assertj.core.util.DateUtil;
import org.github.pajelonek.empik.empikgithubapi.dao.UserDao;
import org.github.pajelonek.empik.empikgithubapi.model.DefaultException;
import org.github.pajelonek.empik.empikgithubapi.model.UserResponse;
import org.github.pajelonek.empik.empikgithubapi.model.github.UserInfoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private GitHubApiClient client;

    @Mock
    UserDao userDao;

    @BeforeEach
    void startUp() {
        userService = new UserService(client, userDao);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(client);
    }

    @Test
    void getUserInfoHappyPath() throws DefaultException {
        // given
        final String user = "testUserName";
        int followers = 3;
        int publicRepos = 2;
        given(client.getUserInfo(anyString())).willReturn(ResponseEntity.ok(correctUserInfo(user, followers, publicRepos)));

        // when
        ResponseEntity<UserResponse> response = userService.getUserInfo(user);

        // then
        assertThat(response.getBody())
                .usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(correctUserResponse(user, followers, publicRepos));
    }

    @Test
    void getUserInfoWith0PublicRepos() throws DefaultException {
        // given
        final String user = "testUserName2";
        int followers = 4;
        int publicRepos = 0;
        given(client.getUserInfo(anyString())).willReturn(ResponseEntity.ok(correctUserInfo(user, followers, publicRepos)));

        // when
        ResponseEntity<UserResponse> response = userService.getUserInfo(user);

        // then
        assertThat(response.getBody())
                .usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(correctUserResponse(user, followers, publicRepos));
    }


    private UserInfoResponse correctUserInfo(final String userName, final int followers, final int publicRepos) {
        return UserInfoResponse.builder()
                .id(1)
                .login("login")
                .name(userName)
                .type("type")
                .avatarUrl("http://localhost/avatar.png")
                .createdAt(DateUtil.now())
                .followers(followers)
                .publicRepos(publicRepos)
                .build();
    }

    private UserResponse correctUserResponse(final String userName, final int followers, final int publicRepos) {
        return UserResponse.builder()
                .id(1)
                .login("login")
                .name(userName)
                .type("type")
                .avatarUrl("http://localhost/avatar.png")
                .createdAt(DateUtil.now())
                .calculations(BigDecimal.valueOf(followers == 0 ? 0 : 6.0 / followers * (2 + publicRepos)))
                .build();
    }
}
