package org.github.pajelonek.empik.empikgithubapi.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
