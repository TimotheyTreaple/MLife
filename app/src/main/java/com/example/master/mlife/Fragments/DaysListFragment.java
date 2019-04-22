package com.example.master.mlife.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.example.master.mlife.View.MainActivity.day;

public class DaysListFragment extends Fragment {

    LinearLayout mMondayLayout;
    LinearLayout mTuesdayLayout;
    LinearLayout mWednesdayLayout;
    LinearLayout mThursdayLayout;
    LinearLayout mFridayLayout;
    LinearLayout mSaturdayLayout;
    LinearLayout mSundayLayout;

    String[] days = new String[7];
    Class frClass = null;
    View view;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.days_list_layout, container, false);

        getDayOfWeek();

        mMondayLayout = view.findViewById(R.id.monday_button_go);
        mTuesdayLayout = view.findViewById(R.id.tuesday_button_go);
        mWednesdayLayout = view.findViewById(R.id.wednesday_button_go);
        mThursdayLayout = view.findViewById(R.id.thursday_button_go);
        mFridayLayout = view.findViewById(R.id.friday_button_go);
        mSaturdayLayout = view.findViewById(R.id.saturday_button_go);
        mSundayLayout = view.findViewById(R.id.sunday_button_go);

        mMondayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDayLayoutClick(mMondayLayout);
            }
        });
        mTuesdayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDayLayoutClick(mTuesdayLayout);
            }
        });
        mWednesdayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDayLayoutClick(mWednesdayLayout);
            }
        });
        mThursdayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDayLayoutClick(mThursdayLayout);
            }
        });
        mFridayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDayLayoutClick(mFridayLayout);
            }
        });
        mSaturdayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDayLayoutClick(mSaturdayLayout);
            }
        });
        mSundayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDayLayoutClick(mSundayLayout);
            }
        });

        return view;
    }

    public void onDayLayoutClick(View view) {

        switch (view.getId()) {
            case R.id.monday_button_go:
                frClass = DayScheduleFragment.class;
                day = days[0];
                break;
            case R.id.tuesday_button_go:
                frClass = DayScheduleFragment.class;
                day = days[1];
                break;
            case R.id.wednesday_button_go:
                frClass = DayScheduleFragment.class;
                day = days[2];
                break;
            case R.id.thursday_button_go:
                frClass = DayScheduleFragment.class;
                day = days[3];
                break;
            case R.id.friday_button_go:
                frClass = DayScheduleFragment.class;
                day = days[4];
                break;
            case R.id.saturday_button_go:
                frClass = DayScheduleFragment.class;
                day = days[5];
                break;
            case R.id.sunday_button_go:
                frClass = DayScheduleFragment.class;
                day = days[6];
                break;
        }

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.addToBackStackFragment(frClass);
        mainActivity.setTitle(day);
    }

    public void getDayOfWeek() {

        Calendar now = Calendar.getInstance();
        now.setFirstDayOfWeek(Calendar.MONDAY);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");


        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }


    }


}
