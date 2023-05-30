package com.example.ideer.scrap;

import com.google.firebase.Timestamp;

public class Scrap {
    String content;
    Timestamp timestamp;

    public Scrap() {

    }


    public String getContent() {
        return content;
    }

    public void setContent(String noteContent) {
        this.content = noteContent;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp now) {
        this.timestamp = now;
    }
}
