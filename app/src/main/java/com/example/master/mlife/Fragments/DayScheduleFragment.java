package com.example.master.mlife.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class DayScheduleFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList <String> arrayList = new ArrayList <String>(0);

    String username;


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    ListView listView;


    String title;
    String targetDay;
    String id;
    MainActivity mainActivity;

    int size = 0;

    Fragment fragment = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_schedule_layout, container, false);
        listView = view.findViewById(R.id.discr_for_task);
        username = user.getDisplayName();
        getDocuments();

        mainActivity = (MainActivity) getActivity();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView <?> parent, View view,
                                    int position, long id) {
                title = ((TextView) view).getText().toString();
                Objects.requireNonNull(getActivity()).getIntent().putExtra("title4", title);
                mainActivity.addToBackStackFragment(ViewAtitleViewModel.class);
                arrayList.clear();
            }
        });


        return view;

    }


    void getDocuments() {
        targetDay = MainActivity.day;
        db.collection("Schedule").document(username).collection(targetDay)
                .get()
                .addOnCompleteListener(new OnCompleteListener <QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task <QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                id = document.getId();
                                arrayList.add(id);
                                Collections.sort(arrayList);
                                addDataToListView();
                                size=arrayList.size();
                            }
                        } else {
                            System.out.println("Error!");
                        }
                        if(size==0){
                            checkOfEventInDay();
                        }
                    }
                });



    }

    private void addDataToListView() {
        ArrayAdapter <String> adapter = new ArrayAdapter <String>(getActivity(),
                android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

    }

    protected void checkOfEventInDay() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Important message")
                .setMessage("There are no event today")
                .setCancelable(false)
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getActivity().onBackPressed();
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();


    }
}