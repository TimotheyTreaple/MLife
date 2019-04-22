package com.example.master.mlife.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.master.mlife.Fragments.ChangeDataFragment;
import com.example.master.mlife.Fragments.GetTimeFragment;
import com.example.master.mlife.Fragments.NewCreateFragment;
import com.example.master.mlife.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NewCreateActivity extends AppCompatActivity {

    Fragment fragment = null;
    Class fragmentLayoutClass = null;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();

    String username;
    String date = null;
    String time = null;
    String title = null;
    String description = null;
    int privacy = 0;

    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_create);

        user = FirebaseAuth.getInstance().getCurrentUser();
        username=user.getDisplayName();

        fragmentLayoutClass = ChangeDataFragment.class;
        addToBackStackFragment(fragmentLayoutClass);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }


    public void addToBackStackFragment(Class afFragmentClass) {
        try {
            fragment = (Fragment) afFragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentManager
                .beginTransaction()
                .add(R.id.new_create_container, fragment, afFragmentClass.getSimpleName())
                .addToBackStack(afFragmentClass.getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.new_create_container);

        if (f instanceof ChangeDataFragment) {
            super.onBackPressed();
            super.onBackPressed();
        } else if (f instanceof GetTimeFragment) {
            fragmentManager.popBackStack();
        } else if (f instanceof NewCreateFragment) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void getInformation() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.new_create_container);

        if (f instanceof GetTimeFragment) {
            date = getIntent().getStringExtra("date");
            title = getIntent().getStringExtra("title");
            description= getIntent().getStringExtra("description");
            time= getIntent().getStringExtra("time");
            privacy= getIntent().getIntExtra("privacy",1);
            System.out.println(username);
            System.out.println(date);
            System.out.println(title);
            System.out.println(description);
            System.out.println(time);
            System.out.println(privacy);
            addValue();
        }


        finish();

    }
    void addValue(){
        Map<String, Object> event = new HashMap<>();
        event.put("nameEvent", title);
        event.put("time", time);
        event.put("description", description);
        event.put("date", date);
        event.put("privacy", privacy);

        firestore.collection("Schedule").document(username).collection(date).document(title).set(event);
    }


}













