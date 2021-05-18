package com.mcs.android.asynctask.models;

public class Comment {
    private long postId;
    private long id;
    private String authorName;
    private String authorEmail;
    private String body;

    public Comment(long postId, long id, String authorName, String authorEmail, String body) {
        this.postId = postId;
        this.id = id;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.body = body;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
