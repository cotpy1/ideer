package com.example.ideer.chat;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {
    private List<Message> messageList;

    public List<Message> getMessageList() {
        if (messageList == null) {
            messageList = new ArrayList<>();
        }
        return messageList;
    }

    public void addMessage(Message message) {
        getMessageList().add(message);
    }
}
