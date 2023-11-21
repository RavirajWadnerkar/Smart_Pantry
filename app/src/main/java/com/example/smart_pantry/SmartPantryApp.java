package com.example.smart_pantry;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class SmartPantryApp extends Application {

    public void onCreate() {
        super.onCreate();
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("SmartPantryApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("SmartPantryApp", "Could not initialize Amplify", error);
        }

    }
}
