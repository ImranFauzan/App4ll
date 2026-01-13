package com.example.app4ll;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class IndianActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_indian);
        NavigationView navigationView = findViewById(R.id.nav_view_indian);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_indian);

        // Set listener for the Indian navigation
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.indianinstrument) {
                selectedFragment = new IndianInstrument();
            } else if (itemId == R.id.indianfood) {
                selectedFragment = new IndianFood();
            } else if (itemId == R.id.indianhome) {
                selectedFragment = new IndianFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_indian, selectedFragment)
                        .commit();
            }
            return true;
        });

        // Load IndianFragment by default on startup
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_indian, new IndianFragment())
                    .commit();
            bottomNav.setSelectedItemId(R.id.indianhome);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle side navigation item clicks here.
        int itemId = item.getItemId();
        Fragment selectedFragment = null;

        if (itemId == R.id.nav_mainPage) { // Assuming these IDs are from your main menu
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.nav_quiz) {
            selectedFragment = new QuizFragment();
        } else if (itemId == R.id.nav_about) {
            selectedFragment = new AboutAppFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_indian, selectedFragment)
                    .commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
