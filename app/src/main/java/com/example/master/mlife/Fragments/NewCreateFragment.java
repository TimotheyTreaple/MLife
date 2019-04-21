package com.example.master.mlife.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.master.mlife.R;
import com.example.master.mlife.View.NewCreateActivity;

import java.util.Objects;

//тут мы создаем новое событие
public class NewCreateFragment extends Fragment {

    String title = null;
    String description = null;
    Button btNext;
    EditText eventName;
    EditText descriptionOfEvent;
    RadioGroup rgForPrivacy;
    int privacy =1;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.new_create_layout, container, false);
        btNext = view.findViewById(R.id.bt_next_2);
        eventName=view.findViewById(R.id.edit_text_title);
        rgForPrivacy=view.findViewById(R.id.radioGroup2);
        descriptionOfEvent=view.findViewById(R.id.et_description);

        rgForPrivacy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // в GetTimeFragment результат
                if (checkedId == R.id.rb_public) {
                    privacy=1;
                    System.out.println(privacy);
                } else  if (checkedId == R.id.rb_private) {
                    privacy=-1;
                    System.out.println(privacy);
                }

            }
        });

        setListeners();


        return view;
    }

    public void setListeners() {
        btNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                description=descriptionOfEvent.getText().toString();
                title=eventName.getText().toString();

                getActivity().getIntent().putExtra("privacy",privacy);
                getActivity().getIntent().putExtra("title",title);
                getActivity().getIntent().putExtra("description",description);



                ((NewCreateActivity) Objects.requireNonNull(getActivity())).addToBackStackFragment(GetTimeFragment.class);

            }
        });

    }



}

