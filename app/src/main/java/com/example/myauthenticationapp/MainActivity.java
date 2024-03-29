package com.example.myauthenticationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void view(View view) {
        FirebaseAuth.getInstance().signOut();//logs the user out
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }
}
