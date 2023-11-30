package com.example.smart_pantry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_pantry.Adapter.IngredientsAdapter;
import com.example.smart_pantry.Adapter.InstructionsAdapter;
import com.example.smart_pantry.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DetailedRecipe extends AppCompatActivity {

    ImageView recipeImage;
    TextView recipeName;
    TextView cookingTime;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_layout);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        recipeImage = findViewById(R.id.recipeImageView);
        recipeName = findViewById(R.id.detailedRecipeName);
        cookingTime = findViewById(R.id.cookingTime);

        RecyclerView ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);
        RecyclerView instructionsRecyclerView = findViewById(R.id.instructionsRecyclerView);

        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra("recipe");

        Picasso.get().load(recipe.getImageURL()).into(recipeImage);
        recipeName.setText(recipe.getRecipeName());
        cookingTime.setText(recipe.getTotalTime()+" mins");

        // Set up Ingredients RecyclerView
        List<String> ingredientsList = Arrays.asList(recipe.getIngredients().split(","));
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredientsList);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up Instructions RecyclerView
        List<String> instructionsList = Arrays.asList(recipe.getInstructions().split("\\."));
        InstructionsAdapter instructionsAdapter = new InstructionsAdapter(instructionsList);
        instructionsRecyclerView.setAdapter(instructionsAdapter);
        instructionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(DetailedRecipe.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
