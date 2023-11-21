package com.example.smart_pantry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

                Amplify.Auth.confirmSignUp(
                        email,
                        otp.getText().toString(),
                        result -> {
                            Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                            Intent intent =  new Intent(getApplicationContext(), UserPreference.class);
                            intent.putExtra("email",email);
                            intent.putExtra("name",name);
                            startActivity(intent);
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });
    }
}
