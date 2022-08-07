package com.example.hellodr.ui.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellodr.AddReminder;
import com.example.hellodr.AllDoctor;
import com.example.hellodr.Doctor;
import com.example.hellodr.DoctorAdapter;
import com.example.hellodr.MainActivity;
import com.example.hellodr.R;
import com.example.hellodr.Reminder;
import com.example.hellodr.ReminderAdapter;
import com.example.hellodr.ui.appointment.AppointmentFragment;
import com.example.hellodr.ui.appointment.AppointmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ReminderFragment extends Fragment {

    FloatingActionButton btnAddReminder;
    RecyclerView recyclerViewR;
    ArrayList<Reminder> reminderArrayList;
    ReminderAdapter reminderAdapter;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    String userEmail;

    private ReminderViewModel mViewModel;
    public static ReminderFragment newInstance() {
        return new ReminderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_reminder, container, false);

        btnAddReminder = root.findViewById(R.id.addReminder);
        recyclerViewR = root.findViewById(R.id.recyclerViewReminder);
        recyclerViewR.setHasFixedSize(true);
        recyclerViewR.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mAuth = FirebaseAuth.getInstance();
        userEmail = mAuth.getCurrentUser().getEmail();

        db = FirebaseFirestore.getInstance();
        reminderArrayList = new ArrayList<Reminder>();
        reminderAdapter = new ReminderAdapter(this.requireContext(), reminderArrayList);

        recyclerViewR.setAdapter(reminderAdapter);

        EventChangeListener();

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

    private void EventChangeListener() {

        db.collection("Reminder")
                .whereEqualTo("email", userEmail)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                reminderArrayList.add(dc.getDocument().toObject(Reminder.class));
                            }
                            reminderAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}