package com.example.hellodr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    TextView tvLogin;
    EditText fname1, lname1, email1, pwd, repswd;
    Button signup;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    ProgressBar progressBar;
    String fname = null,lname=null,email=null,password=null;
    RadioGroup rdgrpUser;
    RadioButton userIs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tvLogin = findViewById(R.id.tvLogin);

        fname1 = (EditText) findViewById(R.id.fname);
        lname1 = (EditText) findViewById(R.id.lname);
        email1 = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.password);
        repswd = (EditText) findViewById(R.id.etRPassword);
        signup = (Button) findViewById(R.id.signup);
        rdgrpUser = findViewById(R.id.radioUserType);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(Signup.this, Login.class);
                startActivity(l);
                Signup.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                finishAffinity();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fname = fname1.getText().toString();
                lname = lname1.getText().toString();
                email = email1.getText().toString();
                password = pwd.getText().toString();

                int selectedUser = rdgrpUser.getCheckedRadioButtonId();
                userIs = findViewById(selectedUser);
                Log.e("TAG", "onClick: "+userIs.getText().toString() );

                if (fname.equals("")) {
                    fname1.setError("Enter First Name");
                    return;
                } else if (!fname.matches("^[a-zA-Z]*$")) {
                    fname1.setError("Only Alphabets allowed");
                    return;

                } else if (lname.equals("")) {
                    lname1.setError("Enter Last Name");
                    return;

                } else if (!lname.matches("^[a-zA-Z]*$")) {
                    lname1.setError("Only Alphabets allowed");
                    return;

                } else if (email.equals("")) {
                    email1.setError("Enter Email");
                    return;

                } else if (!email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
                    email1.setError("Enter valid Email");
                    return;

                } else if (password.equals("")) {
                    pwd.setError("Enter Password");
                    return;

                } else if (password.length()<6) {
                    pwd.setError("Minimum 6 letter");
                    return;

                } else if (repswd.getText().toString().isEmpty()) {
                    repswd.setError("Enter Password again");
                    return;
                } else if (!repswd.getText().toString().matches(password)) {
                    repswd.setError("Enter correct Password");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(Signup.this,"User Created",Toast.LENGTH_SHORT).show();
                            DocumentReference df = fstore.collection("Users").document(user.getUid());
                            Map<String, Object> userInfo = new HashMap<>();

                            userInfo.put("FirstName",fname);
                            userInfo.put("LastName",lname);
                            userInfo.put("Email",email);

                            if(userIs.getText().toString().equals("Patient")){
                                userInfo.put("isPatient","1");
                                df.set(userInfo);
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                DocumentReference df2 = fstore.collection("Patient").document(user.getUid());
                                Map<String, Object> patientInfo = new HashMap<>();
                                patientInfo.put("email",email);
                                df2.set(patientInfo);
                                finish();
                            }
                            else if (userIs.getText().toString().equals("Doctor")){
                                userInfo.put("isDoctor","1");
                                df.set(userInfo);
                                startActivity(new Intent(getApplicationContext(),DoctorActivity.class));

                                DocumentReference df3 = fstore.collection("Doctor").document(user.getUid());
                                Map<String, Object> doctorInfo = new HashMap<>();
                                doctorInfo.put("email",email);
                                doctorInfo.put("FirstName",fname);
                                doctorInfo.put("LastName",lname);
                                doctorInfo.put("experience","5");
                                doctorInfo.put("region","Kitchener");
                                doctorInfo.put("speciality","General Physician");
                                df3.set(doctorInfo);
                                finish();
                            }
                        }
                        else {
                            Toast.makeText(Signup.this,"Error Occured" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}