package com.example.master.mlife.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;
import com.example.master.mlife.View.RegistrationMain;

import java.util.Objects;

public class DaysListFragment extends Fragment {


    View view;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.days_list_layout, container, false);



        return view;
    }

}
