package org.github.pajelonek.empik.empikgithubapi.web;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
public class EmpikGithubApiUsersController {


    @GetMapping
    public ResponseEntity<Void> user(@RequestParam @NonNull String user) {
        return ResponseEntity.ok().build();
    }
}
