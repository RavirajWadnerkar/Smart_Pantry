package com.example.smart_pantry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.core.Amplify;
import com.example.smart_pantry.Adapter.CardAdapter;
import com.example.smart_pantry.model.Card;
import com.example.smart_pantry.model.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RecommendedRecipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_recipes);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        List<Card> cardItems = new ArrayList<>();
        RestOptions options = RestOptions.builder()
                .addPath("/recipe").build();
        final Recipe[][] recipe = new Recipe[1][1];

        Amplify.API.get("RecipeDetailsApi", options,
                response -> {
                    runOnUiThread(() -> {
                        Log.i("MyAmplifyApp", "GET succeeded: " + response);
                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            recipe[0] = objectMapper.readValue(response.getData().asString(), Recipe[].class);
                            for (Recipe r : recipe[0]) {
                                    cardItems.add(new Card(r.getImageURL(), r.getRecipeName(),r));
                            }

                            RecyclerView recyclerView = findViewById(R.id.recommendedRecipesRecyclerView);
                            CardAdapter cardAdapter = new CardAdapter(this, cardItems);
                            recyclerView.setAdapter(cardAdapter);

                            // Set the layout manager
                            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                },
                apiFailure -> {
                    runOnUiThread(() -> {
                        Log.i("MyAmplifyApp", "GET failed: " + apiFailure);
                    });
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(RecommendedRecipes.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
