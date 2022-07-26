package com.example.hellodr.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hellodr.AllDoctor;
import com.example.hellodr.AllSpecialities;
import com.example.hellodr.Login;
import com.example.hellodr.R;
import com.example.hellodr.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment implements View.OnClickListener {

    Button btnView;
    TextView tvLearn;
    LinearLayout llGeneral, llDental, llEye, llMental, llGynecologist, llKidney, llEar, llSkin;

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnView = root.findViewById(R.id.btnView);
        tvLearn = root.findViewById(R.id.tvLearn);
        llGeneral = root.findViewById(R.id.llGeneral);
        llDental = root.findViewById(R.id.llDental);
        llEye = root.findViewById(R.id.llEye);
        llMental = root.findViewById(R.id.llMental);
        llGynecologist = root.findViewById(R.id.llGynecologist);
        llKidney = root.findViewById(R.id.llKidney);
        llEar = root.findViewById(R.id.llEar);
        llSkin = root.findViewById(R.id.llSkin);


        llGeneral.setOnClickListener(this);
        llDental.setOnClickListener(this);
        llEye.setOnClickListener(this);
        llMental.setOnClickListener(this);
        llGynecologist.setOnClickListener(this);
        llKidney.setOnClickListener(this);
        llEar.setOnClickListener(this);
        llSkin.setOnClickListener(this);


        tvLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.canada.ca/en/public-health/services/diseases/coronavirus-disease-covid-19.html");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), AllSpecialities.class));
            }
        });


        return root;
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.llGeneral:
                startActivity(new Intent(getContext(), AllDoctor.class).putExtra("type", "General Physician"));
                break;
            case R.id.llDental:
                startActivity(new Intent(getContext(), AllDoctor.class).putExtra("type", "Dentist"));
                break;
            case R.id.llEye:
                startActivity(new Intent(getContext(), AllDoctor.class).putExtra("type", "Eye"));
                break;
            case R.id.llMental:
                startActivity(new Intent(getContext(), AllDoctor.class).putExtra("type", "Psychiatrist"));
                break;
            case R.id.llGynecologist:
                startActivity(new Intent(getContext(), AllDoctor.class).putExtra("type", "Gynecologist"));
                break;
            case R.id.llKidney:
                startActivity(new Intent(getContext(), AllDoctor.class).putExtra("type", "Kidney"));
                break;
            case R.id.llEar:
                startActivity(new Intent(getContext(), AllDoctor.class).putExtra("type", "ENT"));
                break;
            case R.id.llSkin:
                startActivity(new Intent(getContext(), AllDoctor.class).putExtra("type", "Skin"));
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}