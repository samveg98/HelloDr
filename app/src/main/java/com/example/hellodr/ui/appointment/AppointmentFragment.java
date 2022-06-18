package com.example.hellodr.ui.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hellodr.R;
import com.example.hellodr.ui.setting.SettingFragment;
import com.example.hellodr.ui.setting.SettingViewModel;

public class AppointmentFragment extends Fragment {

    private AppointmentViewModel mViewModel;

    public static AppointmentFragment newInstance() {
        return new AppointmentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AppointmentViewModel.class);
        // TODO: Use the ViewModel
    }

}