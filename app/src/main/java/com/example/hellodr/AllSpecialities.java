package com.example.hellodr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AllSpecialities extends AppCompatActivity implements View.OnClickListener {

    TextView general,dental,eye,mental,women,ent,skin,sexual,digestive,diet,kidney,lung,physio,bones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_specialities);

        general = findViewById(R.id.tvGeneral);
        dental = findViewById(R.id.tvDental);
        eye = findViewById(R.id.tvEye);
        mental = findViewById(R.id.tvMental);
        women = findViewById(R.id.tvWomen);
        ent = findViewById(R.id.tvENT);
        skin = findViewById(R.id.tvSkin);
        sexual = findViewById(R.id.tvSexual);
        digestive = findViewById(R.id.tvDigestive);
        diet = findViewById(R.id.tvDiet);
        kidney = findViewById(R.id.tvKidney);
        lung = findViewById(R.id.tvLung);
        physio = findViewById(R.id.tvPhysio);
        bones = findViewById(R.id.tvBones);


        general.setOnClickListener(this);
        dental.setOnClickListener(this);
        eye.setOnClickListener(this);
        mental.setOnClickListener(this);
        women.setOnClickListener(this);
        ent.setOnClickListener(this);
        skin.setOnClickListener(this);
        sexual.setOnClickListener(this);
        digestive.setOnClickListener(this);
        diet.setOnClickListener(this);
        kidney.setOnClickListener(this);
        lung.setOnClickListener(this);
        physio.setOnClickListener(this);
        bones.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tvGeneral:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","General Physician"));
                break;
            case R.id.tvDental:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Dentist"));
                break;
            case R.id.tvWomen:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Gynecologist"));
                break;
            case R.id.tvMental:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Psychiatrist"));
                break;
            case R.id.tvEye:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Eye"));
                break;
            case R.id.tvBones:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Bones"));
                break;
            case R.id.tvENT:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","ENT"));
                break;
            case R.id.tvDigestive:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Digestive"));
                break;
            case R.id.tvKidney:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Kidney"));
                break;
            case R.id.tvSexual:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Sexual"));
                break;
            case R.id.tvPhysio:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Physiotherapy"));
                break;
            case R.id.tvLung:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Lung"));
                break;
            case R.id.tvSkin:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Skin"));
                break;
            case R.id.tvDiet:
                startActivity(new Intent(getApplicationContext(),AllDoctor.class).putExtra("type","Diet"));
                break;

        }
    }
}