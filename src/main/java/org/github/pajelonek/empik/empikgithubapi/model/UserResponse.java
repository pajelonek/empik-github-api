package org.github.pajelonek.empik.empikgithubapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private Date createdAt;
    private BigDecimal calculations;
}
