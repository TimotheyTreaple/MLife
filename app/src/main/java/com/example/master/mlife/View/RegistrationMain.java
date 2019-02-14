package com.example.master.mlife.View;

import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.master.mlife.Fragments.SignInFragment;
import com.example.master.mlife.R;

public class RegistrationMain extends AppCompatActivity  {


    Fragment fragmentReg = null;
    Class fragmentClassReg = null;
    FragmentManager fragmentManagerReg = getSupportFragmentManager();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.null_reg_ragment_layout);


    }

    @Override
    protected void onStart() {
        super.onStart();
        fragmentClassReg = SignInFragment.class;
        setFragmentReg();

    }

    public void setFragmentReg(){
        try {
            fragmentReg = (Fragment) fragmentClassReg.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Вставляем фрагмент, заменяя текущий фрагмент
        fragmentManagerReg.beginTransaction().replace(R.id.null_reg_layout, fragmentReg).commit();
    }

    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.null_reg_layout, fragment)
                .commit();
    }






    public  void stopedActivity(){

        finish();
    }






}
