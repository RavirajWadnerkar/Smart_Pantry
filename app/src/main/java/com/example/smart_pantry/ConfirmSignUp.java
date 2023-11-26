package com.example.smart_pantry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;

public class ConfirmSignUp extends AppCompatActivity {
    EditText otp;
    Button confirmOTP;
    String email = "";
    String name = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_signup);

        // Initialize UI elements
        Intent intent = getIntent();
        if(intent != null) {
            email = intent.getStringExtra("email");
            name = intent.getStringExtra("name");
        }
        otp = findViewById(R.id.otp);
        confirmOTP = findViewById(R.id.confirmOTP);

        confirmOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpText = otp.getText().toString();

                // Check if OTP field is empty
                if (otpText.isEmpty()) {
                    Toast.makeText(ConfirmSignUp.this, "Please enter OTP.", Toast.LENGTH_LONG).show();
                    return;
                }

                Amplify.Auth.confirmSignUp(
                        email,
                        otpText,
                        result -> {
                            Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                            if (result.isSignUpComplete()) {
                                Intent intent = new Intent(getApplicationContext(), UserPreference.class);
                                intent.putExtra("email", email);
                                intent.putExtra("name", name);
                                startActivity(intent);
                            } else {
                                runOnUiThread(() -> Toast.makeText(ConfirmSignUp.this, "Sign up not complete. Please check your OTP.", Toast.LENGTH_LONG).show());
                            }
                        },
                        error -> {
                            Log.e("AuthQuickstart", error.toString());
                            // Display a toast message with the error on the UI thread
                            runOnUiThread(() -> Toast.makeText(ConfirmSignUp.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show());
                        }
                );
            }
        });



        
    }
}
