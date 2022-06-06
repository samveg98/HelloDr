package com.example.hellodr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView tvSignup;
    EditText etEmail,etPassword;
    Button login;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvSignup = findViewById(R.id.tvSignup);
        etEmail =(EditText) findViewById(R.id.etEmail);
        etPassword =(EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.login);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Signup.class);
                startActivity(i);
                Login.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }
}