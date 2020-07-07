package com.example.midterm2_skeletonproject;

import android.graphics.Bitmap;

public class Car {
    private String ownerName;
    private String imageData;
    private int year;
    private String model;

    public Car(String ownerName, String imageData, int year, String model) {
        this.ownerName = ownerName;
        this.imageData = imageData;
        this.year = year;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getImageData() {
        return imageData;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
