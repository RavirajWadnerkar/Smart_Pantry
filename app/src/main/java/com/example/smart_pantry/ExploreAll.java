package com.example.smart_pantry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_pantry.Adapter.CardAdapter;
import com.example.smart_pantry.Adapter.RecipeTabAdapter;
import com.example.smart_pantry.model.Card;
import com.example.smart_pantry.model.RecipeTab;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExploreAll extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_all);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.exploreAllRecyclerView);

        List<RecipeTab> recipeTabList = new ArrayList<>();
        recipeTabList.add(new RecipeTab( "Card 1"));
        recipeTabList.add(new RecipeTab("Card 2"));
        recipeTabList.add(new RecipeTab("Card 3"));
        recipeTabList.add(new RecipeTab("Card 4"));

        // Create and set the adapter
        RecipeTabAdapter recipeTabAdapter = new RecipeTabAdapter(this, recipeTabList);
        recyclerView.setAdapter(recipeTabAdapter);

        // Set the layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(ExploreAll.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
