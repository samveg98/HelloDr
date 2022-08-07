package com.example.hellodr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellodr.ui.reminder.ReminderFragment;

import java.util.ArrayList;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder> {

    Context context;
    ArrayList<Reminder> reminderArrayList;

    public ReminderAdapter(Context context, ArrayList<Reminder> reminderArrayList) {
        this.context = context;
        this.reminderArrayList = reminderArrayList;
    }

    @NonNull
    @Override
    public ReminderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_reminder,parent,false);

        return new ReminderAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.MyViewHolder holder, int position) {

        Reminder reminder = reminderArrayList.get(position);

        holder.medName.setText(reminder.medName);
        holder.days.setText(reminder.days);
        holder.time.setText(reminder.time);

    }

    @Override
    public int getItemCount() {
        return reminderArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView medName,days,time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            medName = itemView.findViewById(R.id.tvMedName);
            days = itemView.findViewById(R.id.tvDays);
            time = itemView.findViewById(R.id.tvTime);
        }
    }
}
