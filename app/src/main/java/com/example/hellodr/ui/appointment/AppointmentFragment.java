package com.example.hellodr.ui.appointment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellodr.Appointment;
import com.example.hellodr.AppointmentAdapter;
import com.example.hellodr.R;
import com.example.hellodr.Reminder;
import com.example.hellodr.ReminderAdapter;
import com.example.hellodr.ui.setting.SettingFragment;
import com.example.hellodr.ui.setting.SettingViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AppointmentFragment extends Fragment {

    RecyclerView recyclerViewA;
    ArrayList<Appointment> appointmentArrayList;
    AppointmentAdapter appointmentAdapter;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    String userEmail;

    private AppointmentViewModel mViewModel;

    public static AppointmentFragment newInstance() {
        return new AppointmentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_appointment, container, false);

        recyclerViewA = root.findViewById(R.id.recyclerViewA);
        recyclerViewA.setHasFixedSize(true);
        recyclerViewA.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mAuth = FirebaseAuth.getInstance();
        userEmail = mAuth.getCurrentUser().getEmail();

        db = FirebaseFirestore.getInstance();
        appointmentArrayList = new ArrayList<Appointment>();
        appointmentAdapter = new AppointmentAdapter(this.requireContext(), appointmentArrayList);

        recyclerViewA.setAdapter(appointmentAdapter);

        EventChangeListener();



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AppointmentViewModel.class);
        // TODO: Use the ViewModel
    }

    private void EventChangeListener() {

        db.collection("Appointment")
                .whereEqualTo("userEmail", userEmail)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                appointmentArrayList.add(dc.getDocument().toObject(Appointment.class));
                            }
                            appointmentAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

}