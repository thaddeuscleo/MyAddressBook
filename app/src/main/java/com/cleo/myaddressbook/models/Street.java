package com.cleo.myaddressbook.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Street implements Parcelable {
    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
