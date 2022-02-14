package com.cleo.myaddressbook;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cleo.myaddressbook.fragments.AddressFragment;
import com.cleo.myaddressbook.fragments.SearchFragment;
import com.cleo.myaddressbook.helpers.EmployeePlaceholder;
import com.cleo.myaddressbook.models.Employee;
import com.cleo.myaddressbook.models.EmployeeWrapper;
import com.cleo.myaddressbook.utils.APICaller;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private BottomNavigationView navigationView;
    private SearchFragment searchFragment;
    private AddressFragment addressFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setBottomNavigationListener();
        changeFragment(new SearchFragment());
        getAllEmployeeFromAPI();
    }

    private void getAllEmployeeFromAPI() {
        EmployeePlaceholder placeholder = APICaller
                .getInstance()
                .create(EmployeePlaceholder.class);

        Call<EmployeeWrapper> allEmployee = placeholder.getAllEmployee();

        allEmployee.enqueue(new Callback<EmployeeWrapper>() {
            @Override
            public void onResponse(Call<EmployeeWrapper> call, Response<EmployeeWrapper> response) {

            }

            @Override
            public void onFailure(Call<EmployeeWrapper> call, Throwable t) {
                Bundle args = new Bundle();
                args.putString("Message", "Fail");
                searchFragment.setArguments(args);
            }
        });
    }


    private void init() {
        navigationView = findViewById(R.id.bottom_navigation);
        searchFragment = new SearchFragment();
        addressFragment = new AddressFragment();
    }

    private void setBottomNavigationListener() {
        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_search:
                    changeFragment(searchFragment);
                    break;
                case R.id.nav_address:
                    changeFragment(addressFragment);
                    break;
            }
            return true;
        });
    }

    private void changeFragment(Fragment newFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, newFragment)
                .commit();
    }


}