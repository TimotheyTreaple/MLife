package com.example.master.mlife.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;
import com.example.master.mlife.View.NewCreateActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static android.support.constraint.Constraints.TAG;

public class DayScheduleFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //отправить
    ArrayList arrayList = new ArrayList();
    String title1;
    String username;


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String currentDateAndTime;
    ListView listView;

    String targetDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_schedule_layout, container, false);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        listView = view.findViewById(R.id.discr_for_task);

        currentDateAndTime = sdf.format(new Date());
        username = user.getDisplayName();
        getDocuments();
        addDataToListView();

        targetDay = MainActivity.day;
        return view;

    }

    void getDocuments() {
        db.collection("Shadule").document("test").collection("14-4-2019")
                .get()
                .addOnCompleteListener(new OnCompleteListener <QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task <QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                title1 = document.getString("title");
                                System.out.print(title1);


                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void addDataToListView() {
        ArrayAdapter <String> adapter = new ArrayAdapter <String>(getActivity(),
                android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        if (listView != null) {
            getActivity().getIntent().putStringArrayListExtra("arrayList", arrayList);
            MainActivity mainActivity = (MainActivity) getActivity();
            assert mainActivity != null;

        }
    }

}

