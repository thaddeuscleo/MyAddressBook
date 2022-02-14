package com.cleo.myaddressbook.fragments;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cleo.myaddressbook.R;
import com.cleo.myaddressbook.adapters.SearchAdapter;
import com.cleo.myaddressbook.helpers.EmployeePlaceholder;
import com.cleo.myaddressbook.models.Employee;
import com.cleo.myaddressbook.models.EmployeeWrapper;
import com.cleo.myaddressbook.utils.APICaller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Employee> employeesMaster;
    private ArrayList<Employee> employees;
    private SearchAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        EditText searchField = view.findViewById(R.id.search_fr_edit_text);
        Button searchButton = view.findViewById(R.id.search_fr_button);

        // Init Arraylist
        employees = new ArrayList<>();
        employeesMaster = new ArrayList<>();

        // Implement Recycler View
        RecyclerView recyclerView = view.findViewById(R.id.search_recycler_view);
        adapter = new SearchAdapter(employees);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Pull data from the API
        getAllEmployeeFromAPI();

        searchButton.setOnClickListener(v -> {
            String name = searchField.getText().toString().toLowerCase();

            if (name.isEmpty()) {
                employees.clear();
                employees.addAll(employeesMaster);
                adapter.notifyDataSetChanged();
                return;
            }

            List<Employee> employeesTemp = employeesMaster
                    .stream()
                    .filter(employee -> {
                String employeeFirstName = employee.getName().getFirst().toLowerCase();
                String employeeLastName = employee.getName().getFirst().toLowerCase();
                return employeeFirstName.contains(name) || employeeLastName.contains(name);
            })
                    .collect(Collectors.toList());

            employees.clear();
            employees.addAll(employeesTemp);
            adapter.notifyDataSetChanged();
        });
        return view;
    }

    private void getAllEmployeeFromAPI() {
        EmployeePlaceholder placeholder = APICaller
                .getInstance()
                .create(EmployeePlaceholder.class);

        Call<EmployeeWrapper> allEmployee = placeholder.getAllEmployee();

        allEmployee.enqueue(new Callback<EmployeeWrapper>() {
            @Override
            public void onResponse(Call<EmployeeWrapper> call, Response<EmployeeWrapper> response) {
                if (response.body() != null) {
                    employeesMaster.addAll(response.body().getEmployees());
                    employees.addAll(employeesMaster);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<EmployeeWrapper> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }


}