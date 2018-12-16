package com.lam.eshopv2.chat.model;

public class ChatMessage {
    MessageType type;
    enum MessageType {
        CHAT,JOIN,LEAVE
    }
    private String content;
    private String sender;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
