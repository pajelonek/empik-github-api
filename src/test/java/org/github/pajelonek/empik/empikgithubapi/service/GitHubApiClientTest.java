package org.github.pajelonek.empik.empikgithubapi.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.github.pajelonek.empik.empikgithubapi.model.GithubApiUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GitHubApiClientTest {

    @Autowired
    GitHubApiClient client;

    @MockBean
    RestTemplate restTemplate;


    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    void test() throws IOException {
        //given
        final String user = "testUser";
        GithubApiUserResponse response = json2Java("github-api-userinfo-response-ok.json", GithubApiUserResponse.class);
        when(restTemplate.getForEntity(any(), any())).thenReturn(ResponseEntity.ok(response));
        //when
        client.getUserInfo(user);
        //then
        verify(restTemplate, times(1)).getForEntity("https://api.github.com/users/" + user, GithubApiUserResponse.class);
    }

    public static <T> T json2Java(String fileName, Class<T> classType) throws IOException {

        T t = null;
        ClassPathResource classPathResource = new ClassPathResource("test_data/" + fileName);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            t=mapper.readValue(classPathResource.getFile(), classType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    private static Object readFromFile(String path, Class neededClass) throws IOException {
        return new ObjectMapper().readValue(new ClassPathResource(path).getFile(), neededClass);
    }

}
