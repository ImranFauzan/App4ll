package com.example.app4ll;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IndianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_indian);

        // Set listener for the Chinese navigation
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.indianinstrument) {
                selectedFragment = new IndianInstrument();
            } else if (itemId == R.id.indianfood) { // Corrected the menu item ID from DestChinaFood
                selectedFragment = new IndianFood();
            } else if (itemId == R.id.IndianHome) {
                selectedFragment = new IndianFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_indian, selectedFragment)
                        .commit();
            }
            return true;
        });

        // Load ChineseFragment by default on startup
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_indian, new IndianFragment())
                    .commit();
            bottomNav.setSelectedItemId(R.id.IndianHome);
        }

        // Removed the incorrect private inner class definition
    }}