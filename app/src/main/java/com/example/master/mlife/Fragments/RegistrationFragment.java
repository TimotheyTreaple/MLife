package com.example.master.mlife.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.master.mlife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.support.constraint.Constraints.TAG;

public class RegistrationFragment extends Fragment {
    FirebaseUser us1;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    View view = null;
    EditText etEmail;
    EditText etPassword;
    EditText etUsername;
    Button btApplyRegistration;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.registration_layout, container, false);
        etUsername = view.findViewById(R.id.et_username);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        mAuth = FirebaseAuth.getInstance();

        btApplyRegistration = view.findViewById(R.id.bt_apply_registration);
        us1 = FirebaseAuth.getInstance().getCurrentUser();
        setListeners();

        return view;
    }

    public void setListeners() {
        btApplyRegistration.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etEmail.getText() == null || etPassword.getText() == null || etEmail.getText().length() == 0 || etPassword.getText().length() == 0 || etUsername.getText() == null || etUsername.getText().length() == 0) {
                    Toast.makeText(getContext(), "Поля должны быть заполнены!!", Toast.LENGTH_LONG).show();
                } else if (etEmail.getText() != null && etPassword.getText() != null) {
                    registration(etEmail.getText().toString(), etPassword.getText().toString());
                }

            }
        });

    }

    public void onClickGetUsname() {
        String username = etUsername.getText().toString();
        Intent intent5 = new Intent(getActivity(), GetTimeFragment.class);
        intent5.putExtra("nameUser", username);

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();

        us1.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener <Void>() {
                    @Override
                    public void onComplete(@NonNull Task <Void> task) {
                        if (task.isSuccessful()) {
                        } else {

                        }
                    }
                });

        Map <String, Object> user = new HashMap <>();
        user.put("Username", etUsername.getText().toString());

// Add a new document with a generated ID
        firestore.collection("users").document(etUsername.getText().toString())
                .set(user);
        firestore.collection("Schedule").document(etUsername.getText().toString())
                .set(user);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            view.findViewById(R.id.bt_apply_registration).setVisibility(View.VISIBLE);
        } else {
        }
    }


    public void registration(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener <AuthResult>() {
            @Override
            public void onComplete(@NonNull Task <AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    us1 = user;
                    updateUI(user);
                    onClickGetUsname();
                    Toast.makeText(getContext(), "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    updateUI(null);
                    Toast.makeText(getContext(), "Регистрация провалена", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}