package com.example.hellodr;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddReminder extends AppCompatActivity {

    FloatingActionButton btnCheck;
    TextView tvTime;
    EditText etMedname;
    CheckBox sunday, monday, tuesday, wednesday, thursday, friday, saturday;
    String medname, time, days = ",",userEmail;
    ArrayList<Integer> alarmDays= new ArrayList<Integer>();
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        btnCheck = findViewById(R.id.btnDone);
        tvTime = findViewById(R.id.tv_medicine_time);
        etMedname = findViewById(R.id.etMedName);
        sunday = findViewById(R.id.dv_sunday);
        monday = findViewById(R.id.dv_monday);
        tuesday = findViewById(R.id.dv_tuesday);
        wednesday = findViewById(R.id.dv_wednesday);
        thursday = findViewById(R.id.dv_thursday);
        friday = findViewById(R.id.dv_friday);
        saturday = findViewById(R.id.dv_saturday);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medname = etMedname.getText().toString();
                time = tvTime.getText().toString();

                if(sunday.isChecked()) {
                    days = days + "Sunday,";
                    alarmDays.add(Calendar.SUNDAY);
                }
                if(monday.isChecked()) {
                    days = days + "Monday,";
                    alarmDays.add(Calendar.MONDAY);
                }
                if(tuesday.isChecked()) {
                    days = days + "Tuesday,";
                    alarmDays.add(Calendar.TUESDAY);
                }
                if(wednesday.isChecked()) {
                    days = days + "Wednesday,";
                    alarmDays.add(Calendar.WEDNESDAY);
                }
                if(thursday.isChecked()) {
                    days = days + "Thursday,";
                    alarmDays.add(Calendar.THURSDAY);
                }
                if(friday.isChecked()) {
                    days = days + "Friday,";
                    alarmDays.add(Calendar.FRIDAY);
                }
                if(saturday.isChecked()) {
                    days = days + "Saturday,";
                    alarmDays.add(Calendar.SATURDAY);
                }

                mAuth = FirebaseAuth.getInstance();
                userEmail = mAuth.getCurrentUser().getEmail();
                fstore = FirebaseFirestore.getInstance();

                /*fstore = FirebaseFirestore.getInstance();
                fstore.collection("Users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        userEmail = documentSnapshot.getString("Email");
                    }

                });*/
                System.out.println(userEmail);

                Map<String, Object> MedReminder = new HashMap<>();
                MedReminder.put("email",userEmail);
                MedReminder.put("medName",medname);
                MedReminder.put("time",time);
                MedReminder.put("days",days);

                fstore.collection("Reminder")//.document("reminder")
                        .add(MedReminder)
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
                        /*.add(MedReminder)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });*/
                /*DocumentReference dfReminder = fstore.collection("Medicine Reminder").document();
                Map<String, Object> MedReminder = new HashMap<>();
                MedReminder.put("docEmail",userEmail);
                MedReminder.put("medName",medname);
                MedReminder.put("time",time);
                MedReminder.put("days",days);
                dfReminder.set(MedReminder);*/

//                openclock.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                openclock.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
//                openclock.putExtra(AlarmClock.EXTRA_HOUR, d[0]);
//                openclock.putExtra(AlarmClock.EXTRA_MINUTES, 0);
//                openclock.putExtra(AlarmClock.EXTRA_MESSAGE, medname);
//                openclock.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);



                finish();
            }
        });

        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddReminder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tvTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }
}