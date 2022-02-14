package com.cleo.myaddressbook.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Registered implements Parcelable {
    private Date date;
    private int age;

    protected Registered(Parcel in) {
        age = in.readInt();
    }

    public static final Creator<Registered> CREATOR = new Creator<Registered>() {
        @Override
        public Registered createFromParcel(Parcel in) {
            return new Registered(in);
        }

        @Override
        public Registered[] newArray(int size) {
            return new Registered[size];
        }
    };

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
    }
}
