package com.example.master.mlife.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;
import java.util.Objects;

import static android.support.constraint.Constraints.TAG;

public class ViewAtitleViewModel extends Fragment {
    TextView time;
    TextView date;
    TextView privacy;
    EditText title;
    EditText description;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String username;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String targetDay = MainActivity.day;
    String stTitle;
    long p;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_atitle_fragment, container, false);
        time = view.findViewById(R.id.text_view_time);
        date = view.findViewById(R.id.text_view_date);
        privacy = view.findViewById(R.id.text_view_privacy);
        title = view.findViewById(R.id.edit_text_title);
        description = view.findViewById(R.id.edit_text_description);
        stTitle = getActivity().getIntent().getStringExtra("title4");

        System.out.println(stTitle);
        username = user.getDisplayName();
        setInformation();
        return view;
    }



    public void setInformation() {
        db.collection("Schedule").document(username).collection(targetDay).document(stTitle)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (Objects.requireNonNull(document).exists()) {
                        date.setText(document.getString("date"));
                        time.setText(document.getString("time"));
                        title.setText(document.getString("nameEvent"));
                        description.setText(document.getString("description"));
                        p=document.getLong("privacy");
                        if (p ==1){
                            privacy.setText("Public");
                        }else if(p ==-1){
                            privacy.setText("Private");
                        }else {
                            privacy.setText((int) p);
                        }

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}
