package org.github.pajelonek.empik.empikgithubapi.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.github.pajelonek.empik.empikgithubapi.model.UserResponse;
import org.github.pajelonek.empik.empikgithubapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.github.pajelonek.empik.empikgithubapi.utils.TestUtils.json2ClassType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnMessage() throws Exception {
        String user = "octocat";
        UserResponse mockedResponse = json2ClassType("getUserInfo-response-ok.json", UserResponse.class);
        given(service.getUserInfo(anyString())).willReturn(ResponseEntity.ok(mockedResponse));
        ResultActions resultActions = mockMvc.perform(get("/users/" + user))
                .andExpect(status().isOk());

        UserResponse actualResponse = mapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), UserResponse.class);
        assertThat(mockedResponse).isEqualTo(actualResponse);
    }

}
