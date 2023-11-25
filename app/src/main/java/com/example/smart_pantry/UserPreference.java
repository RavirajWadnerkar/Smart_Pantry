package com.example.smart_pantry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.core.Amplify;
import com.example.smart_pantry.model.Cuisine;
import com.example.smart_pantry.model.MealType;
import com.example.smart_pantry.model.UserDetails;
import com.example.smart_pantry.model.UserPreferenceData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserPreference extends AppCompatActivity {

    CheckBox veg;
    CheckBox nonVeg;
    Spinner cuisine;
    Spinner mealType;
    Button save;

    String email = "";
    String name = "";

    private static String accessToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        Intent intent = getIntent();
        if(intent != null) {
            email = intent.getStringExtra("email");
            name = intent.getStringExtra("name");
        }

        veg = findViewById(R.id.veg);
        nonVeg = findViewById(R.id.nonveg);
        cuisine = findViewById(R.id.cuisine);
        mealType = findViewById(R.id.mealtype);
        save = findViewById(R.id.savepreference);

        ArrayAdapter<CharSequence> cuisineAdapter = ArrayAdapter.createFromResource(this,
                R.array.cuisine, android.R.layout.simple_spinner_item);
        cuisineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cuisine.setAdapter(cuisineAdapter);

        ArrayAdapter<CharSequence> mealAdapter = ArrayAdapter.createFromResource(this,
                R.array.meal_type, android.R.layout.simple_spinner_item);
        mealAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealType.setAdapter(mealAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectMapper objectMapper = new ObjectMapper();

                UserPreferenceData preference = new UserPreferenceData();
                preference.setVeg(veg.isChecked());
                preference.setNonVeg(nonVeg.isChecked());
                preference.setCuisine(Cuisine.valueOf(cuisine.getSelectedItem().toString()));
                preference.setMealType(MealType.valueOf(mealType.getSelectedItem().toString()));

                UserDetails userDetails = new UserDetails();
                userDetails.setName(name);
                userDetails.setEmail(email);
                userDetails.setUserPreferenceData(preference);

                RestOptions options = null;
                try {
                    options = RestOptions.builder()
                            .addPath("/preference")
                            .addBody(objectMapper.writeValueAsBytes(userDetails))
                            .build();
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                Amplify.API.post(options,
                        response -> {
                            Log.i("MyAmplifyApp", "POST succeeded: " + response);
                            Intent intent =  new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        },
                        error -> Log.e("MyAmplifyApp", "POST failed.", error)
                );
            }
        });



    }

    private void setAuthToken() {
        Amplify.Auth.fetchUserAttributes(
                attributes -> {
                    for (AuthUserAttribute attribute : attributes) {
                        Log.i("AuthDemo", "Attribute: " + attribute.getKey() + " Value: " + attribute.getValue());
                    }
                },
                error -> {
                    Log.e("AuthDemo", "Error fetching user attributes: " + error.toString());
                }
        );
    }
}
