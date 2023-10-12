package org.github.pajelonek.empik.empikgithubapi.model;

import java.io.Serial;

public class DefaultException extends Exception {
    @Serial
    private static final long serialVersionUID = 100L;

    public DefaultException(Error error) {
        super("#[" + error.getId() + "]: " + error.getMessage());
    }
}