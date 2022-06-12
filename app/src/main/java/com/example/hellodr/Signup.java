package com.example.hellodr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    TextView tvLogin;
    EditText fname1, lname1, email1, pwd, repswd;
    Button signup;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    String fname = null,lname=null,email=null,password=null;

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

        fAuth = FirebaseAuth.getInstance();
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
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fname = fname1.getText().toString();
                lname = lname1.getText().toString();
                email = email1.getText().toString();
                password = pwd.getText().toString();


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
                            Toast.makeText(Signup.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
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