package com.example.hellodr;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllDoctor extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Doctor> doctorArrayList;
    DoctorAdapter doctorAdapter;
    FirebaseFirestore db;
    String doctorType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_doctor);

        doctorType = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        doctorArrayList = new ArrayList<Doctor>();
        doctorAdapter = new DoctorAdapter(AllDoctor.this,doctorArrayList);

        recyclerView.setAdapter(doctorAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {

        db.collection("Doctor")
                .whereEqualTo("speciality", doctorType)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                doctorArrayList.add(dc.getDocument().toObject(Doctor.class));
                            }
                            doctorAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}