package com.example.smart_pantry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.smart_pantry.adapter.CardAdapter;
import com.example.smart_pantry.model.Card;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SideBarActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         RecyclerView recyclerView = findViewById(R.id.recyclerView);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.sidebar);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }else if (id == R.id.nav_pantry) {
                startActivity(new Intent(MainActivity.this, MyPantry.class));
            }else if (id == R.id.nav_recipes) {
                startActivity(new Intent(MainActivity.this, RecommendedRecipes.class));
            }else if (id == R.id.nav_logout) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
            drawerLayout.closeDrawers();
            return true;
        });


        List<Card> cardItems = new ArrayList<>();
        cardItems.add(new Card(R.drawable.bg, "Card 1"));
        cardItems.add(new Card(R.drawable.bg, "Card 2"));
        cardItems.add(new Card(R.drawable.bg, "Card 3"));
        cardItems.add(new Card(R.drawable.bg, "Card 4"));

        // Create and set the adapter
        CardAdapter cardAdapter = new CardAdapter(this, cardItems);
        recyclerView.setAdapter(cardAdapter);

        // Set the layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}