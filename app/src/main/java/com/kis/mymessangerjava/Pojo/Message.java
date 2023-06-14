package com.kis.mymessangerjava.Pojo;

public class Message {

    private String text;
    private String senderId;
    private String receiverId;

    public Message(String text, String senderId, String receiverid) {
        this.text = text;
        this.senderId = senderId;
        this.receiverId = receiverid;
    }

    public Message() { }

    public String getText() {
        return text;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }


}
