package com.content.module.events.ExepectionsHandle;

public class DBError extends RuntimeException {
    public DBError(String message) {
        super(message);
    }
}
