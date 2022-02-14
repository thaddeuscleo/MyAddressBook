package com.cleo.myaddressbook.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleo.myaddressbook.database.dao.EmployeeDao;
import com.cleo.myaddressbook.database.entities.EmployeeEntity;

@Database(entities = {EmployeeEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MyAddressDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
