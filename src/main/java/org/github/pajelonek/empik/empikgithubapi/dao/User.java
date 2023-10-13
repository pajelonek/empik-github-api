package org.github.pajelonek.empik.empikgithubapi.dao;


import jakarta.persistence.*;


@Entity
@Table(name = "GITHUB_USERS")
public class User {

    @Id
    @Column(name = "NAME")
    private String name;
    @Column(name = "REQUEST_COUNT")
    private long requestCount;

}