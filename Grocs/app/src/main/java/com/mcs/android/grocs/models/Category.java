package com.mcs.android.grocs.models;

public class Category {
    private int categoryImageResourceID;
    private String categoryTitle;

    public Category(int categoryImageResourceID, String categoryTitle) {
        this.categoryImageResourceID = categoryImageResourceID;
        this.categoryTitle = categoryTitle;
    }

    public int getCategoryImageResourceID() {
        return categoryImageResourceID;
    }

    public void setCategoryImageResourceID(int categoryImageResourceID) {
        this.categoryImageResourceID = categoryImageResourceID;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
