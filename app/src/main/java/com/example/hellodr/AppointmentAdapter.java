package com.example.hellodr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.MyViewHolder> {

    Context context;
    ArrayList<Appointment> appointmentArrayList;

    public AppointmentAdapter(Context context, ArrayList<Appointment> appointmentArrayList) {
        this.context = context;
        this.appointmentArrayList = appointmentArrayList;
    }

    @NonNull
    @Override
    public AppointmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_appointment,parent,false);

        return new AppointmentAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.MyViewHolder holder, int position) {

        Appointment appointment = appointmentArrayList.get(position);

        holder.dfname.setText(appointment.dfname);
        holder.dlname.setText(appointment.dlname);
        holder.date.setText(appointment.date);
        holder.slot.setText(appointment.slot);
        holder.location.setText(appointment.location);

    }

    @Override
    public int getItemCount() {
        return appointmentArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dfname,dlname,date,slot,location;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dfname = itemView.findViewById(R.id.tvFirst);
            dlname = itemView.findViewById(R.id.tvLast);
            date = itemView.findViewById(R.id.tvDate);
            slot = itemView.findViewById(R.id.tvSlot);
            location = itemView.findViewById(R.id.tvLocation);
        }
    }
}
