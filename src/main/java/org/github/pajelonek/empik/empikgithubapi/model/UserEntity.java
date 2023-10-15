package org.github.pajelonek.empik.empikgithubapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "GITHUB_USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "NAME")
    private String name;
    @Column(name = "REQUEST_COUNT")
    private long requestCount;

}