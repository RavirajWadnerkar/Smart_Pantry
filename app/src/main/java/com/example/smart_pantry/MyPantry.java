package com.example.smart_pantry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.core.Amplify;
import com.example.smart_pantry.Adapter.IngredientsAdapter;
import com.example.smart_pantry.Adapter.MyPantryAdapter;
import com.example.smart_pantry.model.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MyPantry extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypantry);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.mypantryRecyclerView);
        RestOptions options = RestOptions.builder()
                .addPath("/pantry").build();
        List<String> ingredientsList = new ArrayList<>();
        Amplify.API.get("mypantryAPI",options,
                response -> {
                    runOnUiThread(() -> {
                        Log.i("MyAmplifyApp", "GET succeeded: " + response);
                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            MyPantryInfo[] myPantryInfo = objectMapper.readValue(response.getData().asString(), MyPantryInfo[].class);
                            MyPantryAdapter myPantryAdapter = new MyPantryAdapter(myPantryInfo[0].getLabels());
                            recyclerView.setAdapter(myPantryAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(this));

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                },
                apiFailure -> {
                    Log.i("MyAmplifyApp", "GET failed: " + apiFailure);
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(MyPantry.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
