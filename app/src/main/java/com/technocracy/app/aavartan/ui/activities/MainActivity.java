package com.technocracy.app.aavartan.ui.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tbuonomo.morphbottomnavigation.MorphBottomNavigationView;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.ui.fragments.AccountFragment;
import com.technocracy.app.aavartan.ui.fragments.EventsFragment;
import com.technocracy.app.aavartan.ui.fragments.HomeFragment;


public class MainActivity extends AppCompatActivity {

    MorphBottomNavigationView morphBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        setListeners();
        loadFragment(new HomeFragment());

    }

    private void initView(){

        morphBottomNavigationView = findViewById(R.id.nav_view);

    }

    private void setListeners(){

        morphBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        loadFragment(new HomeFragment());
                        break;
                    case R.id.nav_events:
                        loadFragment(new EventsFragment());
                        break;
                    /*case R.id.nav_attraction:
                        loadFragment(new AttractionsFragment());
                        break;
                    case R.id.nav_schedule:
                        loadFragment(new ScheduleFragment());
                        break;*/
                    case R.id.nav_account:
                        loadFragment(new AccountFragment());
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if (item.getItemId() == R.id.options_location) {
            intent = new Intent(MainActivity.this, LocationActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.commit();
    }

}