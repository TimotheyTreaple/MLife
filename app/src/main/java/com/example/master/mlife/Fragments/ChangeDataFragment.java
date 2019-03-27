package com.example.master.mlife.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Collection;
import java.util.Objects;

import static android.support.constraint.Constraints.TAG;

public class ChangeDataFragment extends Fragment {

    Button btNext;
    View view;
    CalendarView calendarView;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    Intent intent5;
    String username;
    String finalDate;
    Intent intent2=new Intent();
    String name;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.change_data_layout, container, false);

        btNext = view.findViewById(R.id.bt_next);
        calendarView=view.findViewById(R.id.calendarView2);
        setListeners();




        return view;
    }
    public void setListeners() {
        btNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((MainActivity) Objects.requireNonNull(getActivity())).addToBackStackFragment(NewCreateFragment.class);
                getDate();

                name=username;
                intent2.putExtra("finalDate",finalDate);
                Bundle b = new Bundle();
                b.putString("nameUser", username);
                b.putString("finalDate", finalDate);

                GetTimeFragment fragB = new GetTimeFragment();
                fragB.setArguments(b);
                getFragmentManager().beginTransaction();


            }
        });

    }
    void getDate(){
        long date = calendarView.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int Year = calendar.get(Calendar.YEAR);
        int Month = calendar.get(Calendar.MONTH);
        int Day = calendar.get(Calendar.DAY_OF_MONTH);
        // в GetTimeFragment
        finalDate=Year+"/"+Month+"/"+Day;
    }

}
