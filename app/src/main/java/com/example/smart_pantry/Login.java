package com.example.smart_pantry;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.example.smart_pantry.MainActivity;

import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button loginButton;
    ProgressBar progressBar; // Add this line for ProgressBar

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Initialize UI elements
        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.Password);
        loginButton = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progressBar); // Initialize ProgressBar here

        View textView = findViewById(R.id.RegisterNow);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar when login starts

                Amplify.Auth.signIn(
                        email,
                        password,
                        result -> runOnUiThread(() -> {
                            progressBar.setVisibility(View.GONE); // Hide the ProgressBar on successful login
                            if(result.isSignedIn()) {
                                Log.i("AuthQuickstart", "Sign in succeeded");
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Log.i("AuthQuickstart", "Sign in not complete");
                                Toast.makeText(Login.this, "Sign in not complete", Toast.LENGTH_SHORT).show();
                            }
                        }),
                        error -> runOnUiThread(() -> {
                            progressBar.setVisibility(View.GONE); // Hide the ProgressBar on failure
                            Log.e("AuthQuickstart", error.toString());
                            Toast.makeText(Login.this, "Login failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        })
                );
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(Login.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}