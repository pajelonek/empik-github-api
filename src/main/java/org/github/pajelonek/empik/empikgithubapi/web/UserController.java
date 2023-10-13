package org.github.pajelonek.empik.empikgithubapi.web;

import lombok.extern.slf4j.Slf4j;
import org.github.pajelonek.empik.empikgithubapi.model.UserResponse;
import org.github.pajelonek.empik.empikgithubapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{user}")
    public ResponseEntity<UserResponse> user(@PathVariable String user) {
        return userService.getUserInfo(user);
    }
}
