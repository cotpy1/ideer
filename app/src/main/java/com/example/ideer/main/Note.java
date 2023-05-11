package com.example.ideer.main;

import com.google.firebase.Timestamp;

public class Note {
    String title;
    String content;
    Timestamp timestamp;

    public Note() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String noteTitle) {
        this.title = noteTitle;
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
