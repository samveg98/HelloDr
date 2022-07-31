package com.example.hellodr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {

    Context context;
    ArrayList<Doctor> doctorArrayList;

    public DoctorAdapter(Context context, ArrayList<Doctor> doctorArrayList) {
        this.context = context;
        this.doctorArrayList = doctorArrayList;
    }

    @NonNull
    @Override
    public DoctorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_doctor,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.MyViewHolder holder, int position) {

        Doctor doctor = doctorArrayList.get(position);

        holder.fname.setText(doctor.FirstName);
        holder.lname.setText(doctor.LastName);
        holder.speciality.setText(doctor.speciality);
        holder.region.setText(doctor.region);
        holder.experience.setText(doctor.experience);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),BookAppointment.class);
                intent.putExtra("email", doctor.email);
                holder.itemView.getContext().startActivity(intent);
                //Toast.makeText(holder.itemView.getContext(), doctor.email,Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return doctorArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView fname, lname,speciality,experience,region;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.tvFirstname);
            lname = itemView.findViewById(R.id.tvLastname);
            speciality = itemView.findViewById(R.id.tvSpeciality);
            experience = itemView.findViewById(R.id.tvExperience);
            region = itemView.findViewById(R.id.tvRegion);

        }
    }
}
