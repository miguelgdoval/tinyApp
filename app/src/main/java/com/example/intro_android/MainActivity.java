package com.example.intro_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.multidex.MultiDex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.firebase.ui.auth.AuthUI;



public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private BottomNavigationView bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.navigation);
        bar.setVisibility(View.GONE);

        bar.setOnNavigationItemSelectedListener(this);

        Fragment fragment = new InputFragment();
        changeFragment(fragment);
    }

    public void showOutput() {
        bar = findViewById(R.id.navigation);

        bar.setVisibility(View.VISIBLE);
        Bundle args = new Bundle();
        args.putString("value", "A");
        Fragment fragment = new OutputFragment();
        fragment.setArguments(args);
        changeFragment(fragment);
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Bundle bundle = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                //fragment = new OutputFragment();
                break;
            case R.id.navigation_favourites:
                //fragment = new ProfileFragment();
                break;
            case R.id.navigation_users:
                fragment = new OutputFragment();
                break;

            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment, false);
    }

    public boolean loadFragment(Fragment fragment, boolean firstFragment) {

        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);

            if (!firstFragment) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
            return true;
        }
        return false;
    }

    public void setBarVisible() {
        bar.setVisibility(View.VISIBLE);
    }


}
