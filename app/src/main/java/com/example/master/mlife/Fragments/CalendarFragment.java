package com.example.master.mlife.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;

import java.util.Date;

public class CalendarFragment extends Fragment {
    CalendarView calendarView;
    String finalDate;
    Date currentDate;
    private static long calendar_pressed;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.calendar_layout, container, false);

        calendarView = view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {

                    @Override
                    public void onSelectedDayChange(CalendarView view, int year,
                                                    int month, int dayOfMonth) {

                        if (calendar_pressed + 2000 > System.currentTimeMillis()) {
                            if (month + 1 < 10) {
                                if (dayOfMonth < 10) {
                                    finalDate = String.valueOf("0" + dayOfMonth + "-" + "0" + (month + 1) +
                                            "-" + year);
                                } else {
                                    finalDate = String.valueOf(dayOfMonth + "-" + "0" + (month + 1) +
                                            "-" + year);
                                }

                            } else if (month + 1 >= 10) {
                                if (dayOfMonth < 10) {
                                    finalDate = String.valueOf("0" + dayOfMonth + "-" + (month + 1) +
                                            "-" + year);
                                } else {
                                    finalDate = String.valueOf(dayOfMonth + "-" + (month + 1) +
                                            "-" + year);
                                }
                            } else {
                                System.out.println("Error!!!");
                            }
                            System.out.println(finalDate);
                            MainActivity.day=finalDate;
                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.addToBackStackFragment(DayScheduleFragment.class);
                        } else {
                            Toast.makeText(getContext(), "Press once again to go!", Toast.LENGTH_SHORT).show();
                        }
                        calendar_pressed = System.currentTimeMillis();


                    }
                });
        return view;
    }
}
