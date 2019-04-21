package com.example.master.mlife.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.master.mlife.R;
import com.example.master.mlife.View.NewCreateActivity;

public class GetTimeFragment extends Fragment {
    TimePicker timePicker;
    Button btComplete;
    int hour;
    int minute;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.get_time_fragment, container, false);
        timePicker = view.findViewById(R.id.timePicker);

        btComplete = view.findViewById(R.id.bt_to_complete);
        setListeners();

        return view;
    }



    public void setListeners() {
        btComplete.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                hour = timePicker.getHour();
                minute = timePicker.getMinute();
                String fullTime=(hour+":"+minute);

                getActivity().getIntent().putExtra("time",fullTime);

                NewCreateActivity newCreateActivity = (NewCreateActivity) getActivity();
                assert newCreateActivity != null;
                newCreateActivity.getInformation();

            }
        });

    }




}

