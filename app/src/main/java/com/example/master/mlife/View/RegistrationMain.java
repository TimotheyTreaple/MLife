package com.example.master.mlife.View;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.master.mlife.Fragments.CalendarFragment;
import com.example.master.mlife.Fragments.DayScheduleFragment;
import com.example.master.mlife.Fragments.DaysListFragment;
import com.example.master.mlife.Fragments.FriendsListFragment;
import com.example.master.mlife.Fragments.MyProfileFragment;
import com.example.master.mlife.Fragments.SignInFragment;
import com.example.master.mlife.R;

public class RegistrationMain extends AppCompatActivity {

    Fragment fragmentReg = null;
    Class fragmentClassReg = null;
    FragmentManager fragmentManagerReg = getSupportFragmentManager();

    private static long back_pressed;


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
        addToBackStackFragment(fragmentClassReg);

    }


    @SuppressLint("NewApi")
    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.null_reg_layout);
        if (f instanceof SignInFragment) {

            if (back_pressed + 2000 > System.currentTimeMillis()) {
                this.finishAffinity();
            } else {
                Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
            }
            back_pressed = System.currentTimeMillis();


        } else {
            super.onBackPressed();
        }
    }

    public void addToBackStackFragment(Class afFragmentClass) {
        try {
            fragmentReg = (Fragment) afFragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Вставляем фрагмент, заменяя текущий фрагмент
        fragmentManagerReg
                .beginTransaction()
                .replace(R.id.null_reg_layout, fragmentReg, afFragmentClass.getSimpleName())
                .addToBackStack(afFragmentClass.getSimpleName())
                .commit();

    }


    public void stopedActivity() {

        finish();
    }


}
