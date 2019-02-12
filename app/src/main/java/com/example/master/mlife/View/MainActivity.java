package com.example.master.mlife.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.example.master.mlife.Fragments.DayScheduleFragment;
import com.example.master.mlife.Fragments.FriendsListFragment;
import com.example.master.mlife.Fragments.MainScheduleFragment;
import com.example.master.mlife.Fragments.MyProfileFragment;
import com.example.master.mlife.Fragments.NewCreateFragment;
import com.example.master.mlife.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView mListUserTasks;

    FragmentManager fragmentManager = getSupportFragmentManager();

    LinearLayout mMondayLayout;
    LinearLayout mTuesdayLayout;
    LinearLayout mWednesdayLayout;
    LinearLayout mThursdayLayout;
    LinearLayout mFridayLayout;
    LinearLayout mSaturdayLayout;
    LinearLayout mSundayLayout;

    Fragment fragment = null;
    Class fragmentClass = null;

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
        fragmentClass = MainScheduleFragment.class;
        setFragment();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentClass=NewCreateFragment.class;
                setTitle("New Create");
                setFragment();
            }
        });

        dlDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dlDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dlDrawer.addDrawerListener(toggle);
        toggle.syncState();

        // Read from the database



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



         tvEmail=findViewById(R.id.tv_email);
         tvUsername =findViewById(R.id.tv_nickname);


        mMondayLayout= findViewById(R.id.monday_button_go);
        mTuesdayLayout= findViewById(R.id.tuesday_button_go);
        mWednesdayLayout= findViewById(R.id.wednesday_button_go);
        mThursdayLayout= findViewById(R.id.thursday_button_go);
        mFridayLayout= findViewById(R.id.friday_button_go);
        mSaturdayLayout= findViewById(R.id.saturday_button_go);
        mSundayLayout= findViewById(R.id.sunday_button_go);

        mListUserTasks=(ListView)findViewById(R.id.discr_for_task);

        mAuth=FirebaseAuth.getInstance();

        mUser = FirebaseAuth.getInstance().getCurrentUser();
if(mUser==null){
    Intent iIntent = new Intent(MainActivity.this, RegistrationMain.class);
    startActivity(iIntent);
}else{
    stEmail =mUser.getEmail();
    stUsername =mUser.getDisplayName();
    dlDrawer.addDrawerListener(getDrawerListener());

}





    }

    private DrawerLayout.DrawerListener getDrawerListener(){

        return new ActionBarDrawerToggle(MainActivity.this, dlDrawer  ,R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            public void onDraverClosed(View view){
                super.onDrawerClosed(view);

                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                tvEmail = drawerView.findViewById(R.id.tv_email);
                tvUsername = drawerView.findViewById(R.id.tv_nickname);
                tvEmail.setText(mUser.getEmail());
                tvUsername.setText(mUser.getDisplayName());
            }
        };
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

        if(id==R.id.item_calendar) {
            fragmentClass=CalendarFragment.class;
        }
        else if (id==R.id.item_calendar_list) {
            fragmentClass=MainScheduleFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
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
            fragmentClass = MyProfileFragment.class;
        } else if (id == R.id.nav_main_schedule) {
            fragmentClass = MainScheduleFragment.class;
        } else if (id == R.id.nav_friends_list) {
            fragmentClass=FriendsListFragment.class;
        } else if (id == R.id.nav_day_schedule){
            fragmentClass=DayScheduleFragment.class;
        } else if(id == R.id.nav_sign_out){

            mAuth.signOut();
            Intent intent = new Intent(this, RegistrationMain.class);
            startActivityForResult(intent, 1);
            return true;
        }
        // Выделяем выбранный пункт меню в шторке
        item.setChecked(true);
        // Выводим выбранный пункт в заголовке
        setTitleDrawer(item);
        setFragment();
        return true;
    }

    public void setTitleDrawer(MenuItem item){
        setTitle(item.getTitle());



    }

    public void setFragment(){
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Вставляем фрагмент, заменяя текущий фрагмент
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, fragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }

    public void onDayLayoutClick(View view){
        switch (view.getId()){
            case R.id.monday_button_go:
                fragmentClass = DayScheduleFragment.class;
                break;
            case R.id.tuesday_button_go:
                fragmentClass = DayScheduleFragment.class;
                break;
            case R.id.wednesday_button_go:
                fragmentClass = DayScheduleFragment.class;
                break;
            case R.id.thursday_button_go:
                fragmentClass = DayScheduleFragment.class;
                break;
            case R.id.friday_button_go:
                fragmentClass = DayScheduleFragment.class;
                break;
            case R.id.saturday_button_go:
                fragmentClass = DayScheduleFragment.class;
                break;
            case R.id.sunday_button_go:
                fragmentClass = DayScheduleFragment.class;
                break;
        }
        setFragment();
    }


}
