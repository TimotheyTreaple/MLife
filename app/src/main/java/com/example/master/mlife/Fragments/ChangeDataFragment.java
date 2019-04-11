package com.example.master.mlife.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.master.mlife.R;
import com.example.master.mlife.View.NewCreateActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ChangeDataFragment extends Fragment {

    Button btNext;
    View view;
    CalendarView calendarView;
    String finalDate;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.change_data_layout, container, false);

        btNext = view.findViewById(R.id.bt_next_1);
        calendarView = view.findViewById(R.id.calendarView2);
        setListeners();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                finalDate= String.valueOf((month + 1) +
                        "-" + dayOfMonth + "-" + year) ;
            }
        });


        return view;
    }

    public void setListeners() {
        btNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().getIntent().putExtra("date",finalDate);
                ((NewCreateActivity) Objects.requireNonNull(getActivity())).addToBackStackFragment(NewCreateFragment.class);

            }
        });

    }


}
