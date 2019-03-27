package com.example.master.mlife.Fragments;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;


public class ToolbarBackButtonFragment extends Fragment {

    MainActivity mainActivity = new MainActivity();
    View view;
    Toolbar toolbar;
    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.toolbar_back_button_layout, container, false);
        toolbar=view.findViewById(R.id.toolbar_back_button);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
            }
        });



        return view;
    }

}
