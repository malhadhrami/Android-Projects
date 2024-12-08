package com.example.adopet;


public class Item {
    private String mType;
    private String mDescription;
    private int mImageResource;

    public Item(String type, String description, int imageResource) {
        mType = type;
        mDescription = description;
        mImageResource = imageResource;
    }

    public String getType() {
        return mType;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getImageResource() {
        return mImageResource;
    }

    @Override
    public String toString() {
        return mType;
    }
}


