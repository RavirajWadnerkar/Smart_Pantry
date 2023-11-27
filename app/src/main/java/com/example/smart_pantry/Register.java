package com.example.smart_pantry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class Register extends AppCompatActivity {
    EditText editTextFullName, editTextEmail, editTextPassword;
    Button buttonRegister;
    ProgressBar progressBarRegister; // Add this line for ProgressBar

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        editTextFullName = findViewById(R.id.FullName);
        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.Password);
        buttonRegister = findViewById(R.id.buttonRegister);
        progressBarRegister = findViewById(R.id.progressBarRegister); // Initialize ProgressBar here

        View textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = editTextFullName.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (TextUtils.isEmpty(fullName)) {
                    Toast.makeText(Register.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBarRegister.setVisibility(View.VISIBLE); // Show the ProgressBar

                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.givenName(), fullName)
                        .build();

                Amplify.Auth.signUp(email, password, options,
                        result -> runOnUiThread(() -> {
                            progressBarRegister.setVisibility(View.GONE); // Hide the ProgressBar
                            Log.i("AuthQuickStart", "Result: " + result.toString());
                            Intent intent = new Intent(getApplicationContext(), ConfirmSignUp.class);
                            intent.putExtra("email", email);
                            intent.putExtra("name", fullName);
                            startActivity(intent);
                        }),
                        error -> runOnUiThread(() -> {
                            progressBarRegister.setVisibility(View.GONE); // Hide the ProgressBar
                            Log.e("AuthQuickStart", "Sign up failed", error);
                            Toast.makeText(Register.this, "Registration failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        })
                );
            }
        });
    }
}
