package com.cleo.myaddressbook;

import android.app.Application;

import com.cleo.myaddressbook.database.AppDatabase;
import com.cleo.myaddressbook.utils.APICaller;

public class MyAddressApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        APICaller.getInstance();
        AppDatabase.getInstance(getApplicationContext());
    }

}
