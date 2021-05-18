package com.mcs.android.firebasechat.models;

import java.util.Date;

public class ChatMessage {
    private String id;
    private String senderUid;
    private String body;
    private Date sentAt;

    public ChatMessage() {
    }

    public ChatMessage(String id, String senderUid, String body, Date sentAt) {
        this.id = id;
        this.senderUid = senderUid;
        this.body = body;
        this.sentAt = sentAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}
