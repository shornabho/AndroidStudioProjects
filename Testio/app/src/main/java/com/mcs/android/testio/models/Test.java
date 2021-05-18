package com.mcs.android.testio.models;

import java.util.Date;

public class Test {
    private String id;
    private String title;
    private String creator;
    private Date createdAt;

    public Test() {
    }

    public Test(String id, String title, String creator, Date createdAt) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
