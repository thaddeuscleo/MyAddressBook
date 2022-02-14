package com.cleo.myaddressbook.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cleo.myaddressbook.R;
import com.cleo.myaddressbook.database.AppDatabase;
import com.cleo.myaddressbook.database.entities.EmployeeEntity;
import com.cleo.myaddressbook.models.Employee;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Employee employee;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView name = view.findViewById(R.id.detail_item_name);
        TextView city = view.findViewById(R.id.detail_item_city);
        TextView phone = view.findViewById(R.id.detail_item_phone);
        TextView member = view.findViewById(R.id.detail_item_member);
        Button addAddressBtn = view.findViewById(R.id.add_to_address_btn);


        if (getArguments() != null) {
            setComponentValue(name, city, phone, member, addAddressBtn);
        }

        addChildMapFragment();
        return view;
    }

    private void addChildMapFragment() {
        Fragment mapFragment = new DetailMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Data", employee);
        mapFragment.setArguments(bundle);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.detail_item_map, mapFragment)
                .commit();
    }

    private void setComponentValue(TextView name, TextView city, TextView phone, TextView member, Button addAddressBtn) {
        String memberStr;
        String nameStr;
        String cityStr;
        String phoneStr;

        employee = getArguments().getParcelable("Data");
        nameStr = getArguments().getString("Name");
        cityStr = getArguments().getString("City");
        phoneStr = getArguments().getString("Phone");
        memberStr = getArguments().getString("Member");

        name.setText(nameStr);
        city.setText(cityStr);
        phone.setText(phoneStr);
        member.setText(memberStr);

        addAddressBtn.setOnClickListener(v -> {
            AppDatabase database = AppDatabase.getInstance(this.getContext());
            EmployeeEntity entity = new EmployeeEntity(
                    employee.getName().getFirst(),
                    employee.getName().getLast(),
                    employee.getEmail(),
                    employee.getCell(),
                    employee.getPhone(),
                    employee.getLocation().getCountry(),
                    employee.getLocation().getCity(),
                    memberStr,
                    employee.getPicture().getThumbnail()
            );

            database.employeeDao().insertUser(entity);
            Toast
                    .makeText(this.getContext(), "Added to your address", Toast.LENGTH_SHORT)
                    .show();
        });
    }
}