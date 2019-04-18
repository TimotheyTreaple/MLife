package com.example.master.mlife.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;
import java.util.Objects;

import static android.support.constraint.Constraints.TAG;

public class DayScheduleFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //отправить
    ArrayList arrayList = new ArrayList();
    String title1;
    String username;


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    ListView listView;

    String targetDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_schedule_layout, container, false);
        listView = view.findViewById(R.id.discr_for_task);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View itemClicked, int position, long id) {
                Toast.makeText(getContext(), ((TextView) itemClicked).getText(),
                        Toast.LENGTH_SHORT).show();
                ((MainActivity) Objects.requireNonNull(getActivity())).addToBackStackFragment(ViewAtitleViewModel.class);


            }
        });
        username = user.getDisplayName();
        getDocuments();


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
                                arrayList.add(document.getId());
                                System.out.print(arrayList);
                                addDataToListView();

                            }
                            System.out.println(arrayList.toString());
                        } else {
                            System.out.println("Error!");
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

