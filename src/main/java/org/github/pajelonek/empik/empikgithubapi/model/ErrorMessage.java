package org.github.pajelonek.empik.empikgithubapi.model;

import java.util.Date;

public record ErrorMessage(int statusCode, Date timestamp, String message, String description) { }
