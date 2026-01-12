package com.example.app4ll;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IndianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_chinese);

        // Set listener for the Chinese navigation
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.ChineseInstrument) {
                selectedFragment = new ChineseInstrumentFragment();
            } else if (itemId == R.id.ChineseFood) { // Corrected the menu item ID from DestChinaFood
                selectedFragment = new ChineseFoodFragment();
            } else if (itemId == R.id.ChineseHome) {
                selectedFragment = new ChineseFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_chinese,
                        selectedFragment).commit();
            }
            return true;
        });

        // Load ChineseFragment by default on startup
        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.DestHomeChinese);
        }
    }

}