package com.example.smart_pantry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.core.Amplify;
import com.example.smart_pantry.model.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

public class Profile extends AppCompatActivity {

    Button edit;
    TextView name;
    TextView email;
    TextView isVeg;
    TextView isNonVeg;
    TextView cuisine;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        edit = findViewById(R.id.edit);
        name = findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        isVeg = findViewById(R.id.user_v);
        isNonVeg = findViewById(R.id.user_nv);
        cuisine = findViewById(R.id.user_cuisine);

        RestOptions options = RestOptions.builder()
                .addPath("/preference").build();
        Amplify.API.get("UserDetailsApi",options,
                response -> {
                    Log.i("MyAmplifyApp", "GET succeeded: " + response);
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        UserDetails[] userDetails = objectMapper.readValue(response.getData().asString(), UserDetails[].class);
                        name.setText(userDetails[0].getName());
                        email.setText(userDetails[0].getEmail());
                        isVeg.setText(userDetails[0].getUserPreferenceData().isVeg() ? "Yes":"No");
                        isNonVeg.setText(userDetails[0].getUserPreferenceData().isNonVeg() ? "Yes":"No");
                        cuisine.setText(userDetails[0].getUserPreferenceData().getCuisine().toString());
                    } catch ( IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                apiFailure -> {
                    Log.i("MyAmplifyApp", "GET failed: " + apiFailure);
                });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserPreference.class);
                intent.putExtra("name",name.getText());
                intent.putExtra("email",email.getText());
                startActivity(intent);
            }
        });


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(Profile.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
