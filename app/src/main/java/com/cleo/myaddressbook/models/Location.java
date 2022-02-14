package com.cleo.myaddressbook.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Location implements Parcelable {
    private Street street;
    private String city;
    private String country;
    private String postcode;

    @SerializedName("coordinates")
    private Coordinate coordinate;

    private Timezone timezone;

    protected Location(Parcel in) {
        city = in.readString();
        country = in.readString();
        postcode = in.readString();
        coordinate = in.readParcelable(Coordinate.class.getClassLoader());
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public Street getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostcode() {
        return postcode;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(country);
        dest.writeString(postcode);
        dest.writeParcelable(coordinate, flags);
    }
}
