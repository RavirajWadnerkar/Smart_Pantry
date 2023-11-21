package com.example.smart_pantry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class Register extends AppCompatActivity {
    EditText editTextFullName, editTextEmail, editTextPassword;
    Button buttonRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextFullName = findViewById(R.id.FullName);
        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.Password);
        buttonRegister = findViewById(R.id.buttonRegister);
        View textView = findViewById(R.id.loginNow);

        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });

        // Set up the button click listener
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName, email, password, spinnerPreferredMeal;

                fullName = String.valueOf(editTextFullName.getText());
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                if (TextUtils.isEmpty(fullName)){
                    Toast.makeText(Register.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.givenName(), fullName)
                        .build();
                Amplify.Auth.signUp(email, password, options,
                        result -> {
                    Log.i("AuthQuickStart", "Result: " + result.toString());
                            Intent intent =  new Intent(getApplicationContext(), ConfirmSignUp.class);
                            intent.putExtra("email",email);
                            intent.putExtra("name",fullName);
                            startActivity(intent);
                        },
                        error -> Log.e("AuthQuickStart", "Sign up failed", error)
                );



            }
        });
    }
}
