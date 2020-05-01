package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

public class Activity_Social_Media extends AppCompatActivity {
    private android.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
     private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Social Media App!!!");
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar); //checkup for this method..

        viewPager = findViewById(R.id.viewPager);
        tabAdapter=new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager, false);




    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}
