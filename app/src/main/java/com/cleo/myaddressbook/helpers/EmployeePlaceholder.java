package com.cleo.myaddressbook.helpers;

import com.cleo.myaddressbook.models.EmployeeWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeePlaceholder {

    @GET("?nim=2301878403&nama=ThaddeusCleo")
    Call<EmployeeWrapper> getAllEmployee();

    @GET("{id}/?nim=2301878403&nama=ThaddeusCleo")
    Call<EmployeeWrapper> getEmployee(@Path("id") int employeeId);
}
