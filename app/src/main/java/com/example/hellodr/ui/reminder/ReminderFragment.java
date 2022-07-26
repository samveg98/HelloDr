package com.example.hellodr.ui.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hellodr.AddReminder;
import com.example.hellodr.MainActivity;
import com.example.hellodr.R;
import com.example.hellodr.ui.appointment.AppointmentFragment;
import com.example.hellodr.ui.appointment.AppointmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReminderFragment extends Fragment {

    FloatingActionButton btnAddReminder;

    private ReminderViewModel mViewModel;
    public static ReminderFragment newInstance() {
        return new ReminderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_reminder, container, false);

        btnAddReminder = root.findViewById(R.id.addReminder);

        btnAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddReminder.class));
            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        // TODO: Use the ViewModel
    }
}