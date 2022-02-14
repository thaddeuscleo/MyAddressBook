package com.cleo.myaddressbook.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cleo.myaddressbook.R;
import com.cleo.myaddressbook.database.entities.EmployeeEntity;
import com.cleo.myaddressbook.tasks.LoadImageTask;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private ArrayList<EmployeeEntity> employees;
    private Context context;

    public AddressAdapter(ArrayList<EmployeeEntity> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EmployeeEntity entity = employees.get(position);

        String name = String
                .format("name: %s %s",
                        entity.getFirstName(), entity.getLastName());
        String city = String
                .format("city: %s, %s",
                        entity.getCity(), entity.getCountry());

        String phone = String.format("phone: %s / %s",
                entity.getPhone(), entity.getCell());

        String member = entity.getRegisteredDate();

        holder.name.setText(name);
        holder.city.setText(city);
        holder.phone.setText(phone);
        holder.member.setText(member);

        new LoadImageTask(holder.thumbnail).execute(entity.getThumbnailUrl());

        holder.callButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", entity.getPhone(), null));
            context.startActivity(intent);
        });

        holder.emailButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.fromParts("mailto", entity.getEmail(), null));
            context.startActivity(intent);
            Toast.makeText(context, "Started", Toast.LENGTH_SHORT).show();
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
        Button callButton;
        Button emailButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.address_item_name);
            city = itemView.findViewById(R.id.address_item_city);
            phone = itemView.findViewById(R.id.address_item_phone);
            member = itemView.findViewById(R.id.address_item_member);
            thumbnail = itemView.findViewById(R.id.address_item_thumbnail);
            callButton = itemView.findViewById(R.id.address_call_btn);
            emailButton = itemView.findViewById(R.id.address_email_btn);
        }
    }
}
