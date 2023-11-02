package com.content.module.posts.exepctionsHandles;

public class NotCompleted extends RuntimeException{
    public NotCompleted(String message) {
        super(message);
    }
}
