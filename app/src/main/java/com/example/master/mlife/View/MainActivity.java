package com.example.master.mlife.View;


import android.content.Intent;
import android.os.Bundle;

import static android.app.PendingIntent.getActivity;
import static android.support.constraint.Constraints.TAG;
import static com.example.master.mlife.R.*;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.master.mlife.Fragments.CalendarFragment;
import com.example.master.mlife.Fragments.ChangeDataFragment;
import com.example.master.mlife.Fragments.DayScheduleFragment;
import com.example.master.mlife.Fragments.FriendsListFragment;
import com.example.master.mlife.Fragments.DaysListFragment;
import com.example.master.mlife.Fragments.GetTimeFragment;
import com.example.master.mlife.Fragments.MyProfileFragment;
import com.example.master.mlife.Fragments.NewCreateFragment;
import com.example.master.mlife.Fragments.ToolbarBackButtonFragment;
import com.example.master.mlife.Fragments.ToolbarDrawerFragment;
import com.example.master.mlife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView mListUserTasks;


    LinearLayout mMondayLayout;
    LinearLayout mTuesdayLayout;
    LinearLayout mWednesdayLayout;
    LinearLayout mThursdayLayout;
    LinearLayout mFridayLayout;
    LinearLayout mSaturdayLayout;
    LinearLayout mSundayLayout;


    Fragment fragment = null;
    Class fragmentLayoutClass = null;
    Class fragmentToolbarClass = null;
    FragmentManager fragmentManager = getSupportFragmentManager();

    FirebaseFirestore Firestore = FirebaseFirestore.getInstance();

    String stEmail;
    String stUsername;

    FirebaseUser mUser;

    FirebaseAuth mAuth;

    TextView tvEmail;
    TextView tvUsername;

    DrawerLayout dlDrawer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.activity_main);
        fragmentLayoutClass = DaysListFragment.class;
        replaceFragment();
        Toolbar toolbar = findViewById(id.toolbar_drawer);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentLayoutClass = ChangeDataFragment.class;
                addToBackStackFragment(fragmentLayoutClass);
                fragmentToolbarClass = ToolbarBackButtonFragment.class;
                addToBackStackToolbar(fragmentToolbarClass);
                fragmentLayoutClass = null;

            }
        });

        dlDrawer = findViewById(id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dlDrawer, toolbar, string.navigation_drawer_open, string.navigation_drawer_close);
        dlDrawer.addDrawerListener(toggle);
        toggle.syncState();

        // Read from the database


        NavigationView navigationView = findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        tvEmail = findViewById(id.tv_email);
        tvUsername = findViewById(id.tv_nickname);

        mMondayLayout = findViewById(id.monday_button_go);
        mTuesdayLayout = findViewById(id.tuesday_button_go);
        mWednesdayLayout = findViewById(id.wednesday_button_go);
        mThursdayLayout = findViewById(id.thursday_button_go);
        mFridayLayout = findViewById(id.friday_button_go);
        mSaturdayLayout = findViewById(id.saturday_button_go);
        mSundayLayout = findViewById(id.sunday_button_go);

        mListUserTasks = findViewById(id.discr_for_task);

        mAuth = FirebaseAuth.getInstance();

        mUser = FirebaseAuth.getInstance().getCurrentUser();


        if (mUser == null) {
            Intent iIntent = new Intent(MainActivity.this, RegistrationMain.class);
            startActivity(iIntent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mUser != null) {
            stEmail = mUser.getEmail();
            stUsername = mUser.getDisplayName();
            dlDrawer.addDrawerListener(getDrawerListener());
            Intent intent =new Intent();
            String nikName=stUsername;
            intent.putExtra("nikName",nikName);

            Firestore.collection("users")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener <QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task <QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
    }

    private DrawerLayout.DrawerListener getDrawerListener() {

        return new ActionBarDrawerToggle(MainActivity.this, dlDrawer, string.navigation_drawer_open, string.navigation_drawer_close) {
            public void onDraverClosed(View view) {
                super.onDrawerClosed(view);

                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {


                tvEmail = drawerView.findViewById(id.tv_email);
                tvUsername = drawerView.findViewById(id.tv_nickname);
                tvEmail.setText(stEmail);
                tvUsername.setText(stUsername);
                super.onDrawerOpened(drawerView);
            }
        };
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(id.fragment_layout);
        Fragment t = getSupportFragmentManager().findFragmentById(id.toolbar_container);

        if(t instanceof ToolbarBackButtonFragment){
            if (f instanceof ChangeDataFragment) {
                fragmentManager.popBackStack();
                fragmentManager.popBackStack();
            } else if (f instanceof GetTimeFragment) {
                fragmentManager.popBackStack();
            } else if (f instanceof NewCreateFragment){
                super.onBackPressed();
            }
        }else if(t instanceof ToolbarDrawerFragment) {
            DrawerLayout drawer = findViewById(id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        } else {
            System.out.println("Error!");
        }

    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.item_calendar) {
            fragmentLayoutClass = CalendarFragment.class;
        } else if (id == R.id.item_calendar_list) {
            fragmentLayoutClass = DaysListFragment.class;
        }

        try {
            fragment = (Fragment) fragmentLayoutClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Вставляем фрагмент, заменяя текущий фрагмент
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, fragment).commit();
        // Выделяем выбранный пункт меню в шторке
        item.setChecked(true);
        // Выводим выбранный пункт в заголовке
        setTitle(item.getTitle());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        //noinspection SimplifiableIfStatemen

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_profile) {
            fragmentLayoutClass = MyProfileFragment.class;
        } else if (id == R.id.nav_main_schedule) {
            fragmentLayoutClass = DaysListFragment.class;
        } else if (id == R.id.nav_friends_list) {
            fragmentLayoutClass = FriendsListFragment.class;
        } else if (id == R.id.nav_day_schedule) {
            fragmentLayoutClass = DayScheduleFragment.class;
        } else if (id == R.id.nav_sign_out) {

            mAuth.signOut();
            stUsername = null;
            stEmail = null;
            mUser = null;
            dlDrawer.closeDrawers();
            Intent intent = new Intent(this, RegistrationMain.class);
            startActivityForResult(intent, 1);
            return true;
        }
        // Выделяем выбранный пункт меню в шторке
        item.setChecked(true);
        // Выводим выбранный пункт в заголовке
        setTitleDrawer(item);

        replaceFragment();
        return true;
    }

    public void setTitleDrawer(MenuItem item) {
        setTitle(item.getTitle());


    }

    public void replaceFragment() {
        try {
            fragment = (Fragment) fragmentLayoutClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Вставляем фрагмент, заменяя текущий фрагмент
        fragmentManager.beginTransaction().replace(id.fragment_layout, fragment).commit();

        DrawerLayout drawer = findViewById(id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }


    public void addToBackStackToolbar(Class tbFragmentClass) {
        try {
            fragment = (Fragment) tbFragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Вставляем фрагмент, заменяя текущий фрагмент
        fragmentManager
                .beginTransaction()
                .add(id.toolbar_container, fragment, tbFragmentClass.getSimpleName())
                .addToBackStack(tbFragmentClass.getSimpleName())
                .commit();
    }

    public void addToBackStackFragment(Class afFragmentClass) {
        try {
            fragment = (Fragment) afFragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Вставляем фрагмент, заменяя текущий фрагмент
        fragmentManager
                .beginTransaction()
                .replace(id.fragment_layout, fragment, afFragmentClass.getSimpleName())
                .addToBackStack(afFragmentClass.getSimpleName())
                .commit();

        DrawerLayout drawer = findViewById(id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }


    public void onDayLayoutClick(View view) {
        int nameday = 0;
        switch (view.getId()) {
            case id.monday_button_go:
                fragmentLayoutClass = DayScheduleFragment.class;
                nameday = string.monday;
                break;
            case id.tuesday_button_go:
                fragmentLayoutClass = DayScheduleFragment.class;
                nameday = string.tuesday;
                break;
            case id.wednesday_button_go:
                fragmentLayoutClass = DayScheduleFragment.class;
                nameday = string.wednesday;
                break;
            case id.thursday_button_go:
                fragmentLayoutClass = DayScheduleFragment.class;
                nameday = string.thursday;
                break;
            case id.friday_button_go:
                fragmentLayoutClass = DayScheduleFragment.class;
                nameday = string.friday;
                break;
            case id.saturday_button_go:
                fragmentLayoutClass = DayScheduleFragment.class;
                nameday = string.saturday;
                break;
            case id.sunday_button_go:
                fragmentLayoutClass = DayScheduleFragment.class;
                nameday = string.sunday;
                break;
        }

        addToBackStackFragment(fragmentLayoutClass);
        setTitle(nameday);
    }


}
