package com.example.smart_pantry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class SideBarActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_nav);

//        drawerLayout = findViewById(R.id.drawer_layout);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
//
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//
//        // Set up the navigation view
//        NavigationView navigationView = findViewById(R.id.sidebar);
//        navigationView.setNavigationItemSelectedListener(item -> {
//            int id = item.getItemId();
//
//            if (id == R.id.nav_home) {
//                startActivity(new Intent(SideBarActivity.this, MainActivity.class));
//            } else if (id == R.id.nav_profile) {
//                startActivity(new Intent(SideBarActivity.this, Profile.class));
//            }else if (id == R.id.nav_pantry) {
//                startActivity(new Intent(SideBarActivity.this, MyPantry.class));
//            }else if (id == R.id.nav_recipes) {
//                startActivity(new Intent(SideBarActivity.this, RecommendedRecipes.class));
//            }else if (id == R.id.nav_logout) {
//                startActivity(new Intent(SideBarActivity.this, Profile.class));
//            }
//            drawerLayout.closeDrawers();
//            return true;
//        });

        // Enable the home button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            // Open or close the drawer when the home button is pressed
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
