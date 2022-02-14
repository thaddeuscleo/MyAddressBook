package com.cleo.myaddressbook.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EmployeeWrapper implements Parcelable {
    private String statusCode;
    private String nim;
    private String nama;
    private String employeeId;
    private String credits;

    @SerializedName("employees")
    private ArrayList<Employee> employees;

    protected EmployeeWrapper(Parcel in) {
        statusCode = in.readString();
        nim = in.readString();
        nama = in.readString();
        employeeId = in.readString();
        credits = in.readString();
    }

    public static final Creator<EmployeeWrapper> CREATOR = new Creator<EmployeeWrapper>() {
        @Override
        public EmployeeWrapper createFromParcel(Parcel in) {
            return new EmployeeWrapper(in);
        }

        @Override
        public EmployeeWrapper[] newArray(int size) {
            return new EmployeeWrapper[size];
        }
    };

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(statusCode);
        dest.writeString(nim);
        dest.writeString(nama);
        dest.writeString(employeeId);
        dest.writeString(credits);
    }
}
