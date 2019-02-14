package com.example.master.mlife.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;
import com.example.master.mlife.View.RegistrationMain;

//тут мы создаем новое событие
public class NewCreateFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.new_create_layout, container, false);


        return view;
    }
    private void onClickL(View view){

        Intent i = new Intent(getActivity(), Get_time_fragment.class);
        startActivity(i);
    }

}
