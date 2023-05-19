package com.example.ideer.scrap;

import com.google.firebase.Timestamp;

public class Scrap {

    String conversationText;
    Timestamp timestamp;

    public Scrap() {

    }



    public String getContent() {
        return conversationText;
    }

    public void setContent(String scrapContent) {
        this.conversationText = scrapContent;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp now) {
        this.timestamp = now;
    }
}