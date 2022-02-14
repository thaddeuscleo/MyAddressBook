package com.cleo.myaddressbook.adapters;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cleo.myaddressbook.R;
import com.cleo.myaddressbook.fragments.DetailFragment;
import com.cleo.myaddressbook.models.Employee;
import com.cleo.myaddressbook.tasks.LoadImageTask;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<Employee> employees;
    private Context context;

    public SearchAdapter(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employees.get(position);

        LocalDate newDate = employee
                .getRegistered()
                .getDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        String name = String
                .format("name: %s %s",
                        employee.getName().getFirst(), employee.getName().getLast());
        String city = String
                .format("city: %s, %s",
                        employee.getLocation().getCity(), employee.getLocation().getCountry());
        String phone = String.format("phone: %s / %s",
                employee.getPhone(), employee.getCell());
        String member = String.format("Member since: %s %s", newDate.getMonth(), newDate.getYear());

        holder.name.setText(name);
        holder.city.setText(city);
        holder.phone.setText(phone);
        holder.member.setText(member);

        new LoadImageTask(holder.thumbnail).execute(employee.getPicture().getThumbnail());

        holder.cardView.setOnClickListener(v -> {
            Fragment fragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Data", employee);
            bundle.putString("Name", name);
            bundle.putString("City", city);
            bundle.putString("Phone", phone);
            bundle.putString("Member", member);
            fragment.setArguments(bundle);
            changeFragment(fragment);
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView city;
        TextView phone;
        TextView member;
        ImageView thumbnail;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.search_item_name);
            city = itemView.findViewById(R.id.search_item_city);
            phone = itemView.findViewById(R.id.search_item_phone);
            member = itemView.findViewById(R.id.search_item_member);
            thumbnail = itemView.findViewById(R.id.search_item_thumbnail);
            cardView = itemView.findViewById(R.id.search_item_card);
        }
    }

    private void changeFragment(Fragment newFragment) {
        ((FragmentActivity) this.context)
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, newFragment)
                .commit();
    }
}
