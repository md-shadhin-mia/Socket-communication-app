package com.example.socketcomunication;

public class Messages {
    private String title;
    private Boolean sender;

    public Messages(String title, Boolean sender) {
        this.title = title;
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isSender(){
        return sender;
    }
}
