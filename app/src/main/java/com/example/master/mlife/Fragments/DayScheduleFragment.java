package com.example.master.mlife.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;

public class DayScheduleFragment extends Fragment {
    @Nullable
    String targetDay;
    MainActivity mainActivity = (MainActivity) getActivity();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.day_schedule_layout, container, false);
        targetDay = MainActivity.day;
        System.out.println(targetDay);

        return view;
    }
}
