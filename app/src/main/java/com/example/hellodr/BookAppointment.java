package com.example.hellodr;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookAppointment extends AppCompatActivity {

    Button btnLocation,btnBookAppointment;
    Spinner slot;
    EditText etDate;
    DatePickerDialog datePickerDialog;
    TextView tvRegion, tvAddress,tvFname,tvLname,tvSpeciality,tvExperience;
    String dirURL, docEmail,region, address,fname,lname,speciality,experience,userEmail;
    FirebaseFirestore db,fstore;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        btnLocation = findViewById(R.id.btnLocation);
        btnBookAppointment = findViewById(R.id.btnBook);
        etDate = findViewById(R.id.etDate);
        slot = findViewById(R.id.spinnerSlot);
        Intent intent = getIntent();
        docEmail = intent.getStringExtra("docEmail");
        tvRegion = findViewById(R.id.tvRegion);
        tvAddress = findViewById(R.id.tvAddress);
        tvFname = findViewById(R.id.tvFirstname);
        tvLname = findViewById(R.id.tvLastname);
        tvSpeciality = findViewById(R.id.tvSpeciality);
        tvExperience = findViewById(R.id.tvExperience);

        mAuth = FirebaseAuth.getInstance();
        userEmail = mAuth.getCurrentUser().getEmail();


        //Toast.makeText(BookAppointment.this, docEmail,Toast.LENGTH_SHORT).show();

        String [] values =
                {"9:00 AM","11:00 AM","12:00 PM","2:00 PM","4:00 PM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        slot.setAdapter(adapter);


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(BookAppointment.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                etDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        /*btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q=91 Dixon Street");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });*/

        db = FirebaseFirestore.getInstance();
        db.collection("Doctor")
                .whereEqualTo("email", docEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(getActivity(), "success accessing database", Toast.LENGTH_SHORT).show();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Fetch from database as Map
                                fname = (String) document.getData().get("FirstName");
                                lname = (String) document.getData().get("LastName");
                                speciality = (String) document.getData().get("speciality");
                                experience = (String) document.getData().get("experience");
                                region = (String) document.getData().get("region");
                                address = (String) document.getData().get("address");
                                dirURL = (String) document.getData().get("location");

                                tvRegion.setText(region);
                                tvAddress.setText(address);
                                tvFname.setText(fname);
                                tvLname.setText(lname);
                                tvSpeciality.setText(speciality);
                                tvExperience.setText(experience);
                            }
                        }
                    }
                });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(BookAppointment.this, "success accessing location" + dirURL, Toast.LENGTH_SHORT).show();
                Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q=" + dirURL);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        btnBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fstore = FirebaseFirestore.getInstance();
                Map<String, Object> Appointment = new HashMap<>();
                Appointment.put("userEmail",userEmail);
                Appointment.put("docEmail",docEmail);
                Appointment.put("date",etDate.getText().toString());
                Appointment.put("slot",slot.getSelectedItem().toString());
                Appointment.put("dfname",fname);
                Appointment.put("dlname",lname);
                Appointment.put("location",dirURL);

                fstore.collection("Appointment")//.document("reminder")
                        .add(Appointment)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
                Toast.makeText(BookAppointment.this, "Appointment Booked !" +userEmail+docEmail, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}

