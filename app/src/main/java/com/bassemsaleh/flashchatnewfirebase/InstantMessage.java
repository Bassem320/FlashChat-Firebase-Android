package com.bassemsaleh.flashchatnewfirebase;

public class InstantMessage {
    private String message;
    private String author;

    public InstantMessage() {
    }

    public InstantMessage(String author, String message) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
