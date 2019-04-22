package com.example.master.mlife.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.master.mlife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class ViewAtitleViewModel extends Fragment {
    TextView time;
    TextView date;
    TextView privacy;
    EditText title;
    EditText description;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String username;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DayScheduleFragment dayScheduleFragment;
//    String targetDay = dayScheduleFragment.targetDay;
  //  String stTitle = dayScheduleFragment.title;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_atitle_fragment, container, false);
        time = view.findViewById(R.id.text_view_time);
        date = view.findViewById(R.id.text_view_date);
        privacy = view.findViewById(R.id.text_view_privacy);
        title = view.findViewById(R.id.edit_text_title);
        description = view.findViewById(R.id.edit_text_description);

        setInformation();
        username = user.getDisplayName();
        return view;
    }



    public void setInformation() {


    }
}
