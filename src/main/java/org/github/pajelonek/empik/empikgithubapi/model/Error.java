package org.github.pajelonek.empik.empikgithubapi.model;

public enum Error {
    USERINFO_PESSIMIST_LOCK_EXCEPTION_ERROR(5000, "Error on reading database, contact server admin"),
    USERINFO_ENTITY_NOT_FOUND_ERROR(5001, "Error on reading database, contact server admin"),
    USERINFO_DATA_ACCESS_EXCEPTION_ERROR(5002, "Error on reading database, contact server admin"),
    USERINFO_UNEXPECTED_EXCEPTION_ERROR(5003, "Error on reading database, contact server admin"),
    USERINFO_GITHUB_API_EXCEPTION_ERROR(5004, "Error with connecting to external api, contact server admin");

    private final int id;
    private final String message;

    Error(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}