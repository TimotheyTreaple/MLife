package com.example.master.mlife.Fragments;

import android.content.*;
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
import android.widget.TimePicker;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class GetTimeFragment extends Fragment {
    TimePicker timePicker;
    int privacy;
    Button btComplete;
    int Hour;
    int Minute;
    HashMap shadule = new HashMap();
    String description;
    Intent intent;
    String title;
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    String name="Timothey";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.get_time_fragment, container, false);
        timePicker=view.findViewById(R.id.timePicker);

        btComplete = view.findViewById(R.id.bt_to_complete);
        intent = getActivity().getIntent();
        setListeners();



        return view;
    }

    public void setListeners() {
        btComplete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 Hour=timePicker.getCurrentHour();
                 Minute=timePicker.getCurrentMinute();
                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
               addValue();




            }
        });

    }
    void addTime(){
        shadule.put(Minute,Hour);
        shadule.put(description,title );
    }
    void getIntentValues(){
        description=intent.getStringExtra("description");
        title=intent.getStringExtra("title");
        privacy=intent.getIntExtra("privacy",1);
    }
    void addValue(){
        addTime();
        getIntentValues();
        Map<String, Object> event = new HashMap<>();
        event.put("description", description);
        event.put("privacy", privacy);
        event.put("Hour", Hour);
        event.put("Minute", Minute);
        event.put("Title", title);
        firestore.collection("Shadule").document(name)
                .set(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
}
