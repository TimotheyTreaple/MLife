package com.example.master.mlife.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;
import com.example.master.mlife.View.RegistrationMain;

import java.util.Objects;

//тут мы создаем новое событие
public class NewCreateFragment extends Fragment {

    String title = null;
    String description = null;
    Button btNext;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.new_create_layout, container, false);
        btNext = view.findViewById(R.id.bt_next);

        setListeners();


        return view;
    }

    public void setListeners() {
        btNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((MainActivity) Objects.requireNonNull(getActivity())).addFragment(GetTimeFragment.class);


            }
        });

    }


}
