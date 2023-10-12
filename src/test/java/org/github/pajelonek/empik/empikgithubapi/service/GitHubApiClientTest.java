package org.github.pajelonek.empik.empikgithubapi.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.github.pajelonek.empik.empikgithubapi.model.GithubApiUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GitHubApiClientTest {

    GitHubApiClient client;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    void startUp() {
        client = new GitHubApiClient(restTemplate);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    void getUserInfoHappyPath() {
        //given
        final String user = "testUser";
        GithubApiUserResponse response = json2Java("github-api-userinfo-response-ok.json", GithubApiUserResponse.class);
        given(restTemplate.getForEntity(anyString(), any())).willReturn(ResponseEntity.ok(response));
        //when
        ResponseEntity<GithubApiUserResponse> actualResponse = client.getUserInfo(user);
        //then
        assertThat(HttpStatus.OK).isEqualTo(actualResponse.getStatusCode());
        assertThat(response).isEqualTo(actualResponse.getBody());
        verify(restTemplate, times(1)).getForEntity("https://api.github.com/users/" + user, GithubApiUserResponse.class);
    }

    @Test
    void getUserInfoThrowsException() {
        //given
        final String user = "testUser";
        given(restTemplate.getForEntity(anyString(), any())).willThrow(RestClientException.class);
        //when
        assertThrows(RestClientException.class, () -> client.getUserInfo(user));
        //then
        verify(restTemplate, times(1)).getForEntity("https://api.github.com/users/" + user, GithubApiUserResponse.class);
    }

    public static <T> T json2Java(String fileName, Class<T> classType) {

        T response = null;
        ClassPathResource classPathResource = new ClassPathResource("test_data/" + fileName);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            response = mapper.readValue(classPathResource.getFile(), classType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
