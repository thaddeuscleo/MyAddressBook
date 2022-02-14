package com.cleo.myaddressbook.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleo.myaddressbook.database.entities.EmployeeEntity;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employeeentity")
    List<EmployeeEntity> getAllEmployee();

    @Insert
    void insertUser(EmployeeEntity... entities);
}
