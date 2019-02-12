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
import android.widget.TextView;
import android.widget.Toast;

import com.example.master.mlife.R;
import com.example.master.mlife.View.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class GetUsername extends Fragment {
FirebaseUser mUser ;



    EditText etUsername;
    Button btGetUsername;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.get_username, container, false);
        etUsername = view.findViewById(R.id.et_username);
        btGetUsername = view.findViewById(R.id.bt_get_username);
        mUser= FirebaseAuth.getInstance().getCurrentUser();
        setListeners();

        return view;
    }

    public void setListeners(){
        btGetUsername.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onClickGetUsname();
            }
        });

    }

    public void onClickGetUsname(){
        String username = etUsername.getText().toString();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();

        mUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Информация обновлена!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}