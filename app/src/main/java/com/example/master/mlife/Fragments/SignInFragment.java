package com.example.master.mlife.Fragments;

import android.net.Uri;
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
import com.example.master.mlife.View.RegistrationMain;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignInFragment extends Fragment {

    private FirebaseAuth mAuth;
     EditText etEmail;
     EditText etPassword;

    Button btSignIn;
    Button btRegistration;
    private static final String TAG = "EmailPassword";
    static FirebaseUser us1;



    View view ;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_in_layout, container, false);

        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        mAuth = FirebaseAuth.getInstance();

        btRegistration = view.findViewById(R.id.bt_registration);
        btSignIn = view.findViewById(R.id.bt_sign_in);
        setListeners();
        return view;
    }
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }


    public void setListeners(){
        btSignIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                  onClickReg(v);
            }
        });

        btRegistration.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onClickReg(v);
            }
        });

    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            view.findViewById(R.id.bt_registration).setVisibility(View.VISIBLE);
        } else {
        }
    }

    public void signIn (String email, String password){
        Log.d(TAG, "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    us1=user;
                    updateUI(user);
                    Toast.makeText(getContext(), "Авторизация успешна", Toast.LENGTH_SHORT).show();
                    Objects.requireNonNull(getActivity()).finish();
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(getContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                    Toast.makeText(getContext(), "Авторизация провалена", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void onClickReg(View view) {

            if(view.getId()==R.id.bt_sign_in){
                checkEnterText();
            }else if(view.getId()==R.id.bt_registration){
                ((RegistrationMain) Objects.requireNonNull(getActivity())).addToBackStackFragment(RegistrationFragment.class);
            }


    }

    public void checkEnterText(){

        if(etEmail.getText()==null|| etPassword.getText()==null|| etEmail.getText().length()==0|| etPassword.getText().length()==0){
            Toast.makeText(getContext(), "Поля должны быть заполнены!!", Toast.LENGTH_SHORT).show();
        }
        else if(etEmail.getText()!=null&& etPassword.getText()!=null) {
            signIn(etEmail.getText().toString(), etPassword.getText().toString());
        }

    }

}
