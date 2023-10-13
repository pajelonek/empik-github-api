package org.github.pajelonek.empik.empikgithubapi.web;


import org.github.pajelonek.empik.empikgithubapi.model.GithubApiUserResponse;
import org.github.pajelonek.empik.empikgithubapi.service.GitHubApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.github.pajelonek.empik.empikgithubapi.utils.TestUtils.json2Java;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {


    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void test() {
        // given


        // when
//        this.restTemplate.getForObject("http://localhost:" + port + "/users/octocat", GithubApiUserResponse.class);
//        assertThat()
//                .isEqualTo(json2Java("github-api-userinfo-response-ok.json", GithubApiUserResponse.class));
//        ResponseEntity<GithubApiUserResponse> response = this.controller.user("john");

        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}
