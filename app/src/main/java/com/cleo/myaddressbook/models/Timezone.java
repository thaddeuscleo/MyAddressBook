package com.cleo.myaddressbook.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Timezone implements Parcelable {
    private String offset;
    private String description;

    public String getOffset() {
        return offset;
    }

    public String getDescription() {
        return description;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
