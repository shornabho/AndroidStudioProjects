package com.mcs.android.foodiz.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItem implements Parcelable {
    private String itemName;
    private double itemPrice;
    private String itemDescription;
    private int itemImageResourceID;

    public FoodItem(String itemName, double itemPrice, String itemDescription, int itemImageResourceID) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemImageResourceID = itemImageResourceID;
    }

    protected FoodItem(Parcel in) {
        itemName = in.readString();
        itemPrice = in.readDouble();
        itemDescription = in.readString();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(itemName);
        parcel.writeDouble(itemPrice);
        parcel.writeString(itemDescription);
    }

    public int getItemImageResourceID() {
        return itemImageResourceID;
    }

    public void setItemImageResourceID(int itemImageResourceID) {
        this.itemImageResourceID = itemImageResourceID;
    }
}
