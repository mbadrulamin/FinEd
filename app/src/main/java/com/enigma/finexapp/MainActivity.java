package com.enigma.finexapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private View mLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        mLogOut = findViewById(R.id.logout);
        mLogOut.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(getApplicationContext(), "You've been logout", Toast.LENGTH_SHORT).show();
            return;
        });

    }


    @Override
    public void onClick(View v) {

        Intent intent;
        intent = new Intent(this, EarnActivity.class);
        startActivity(intent);

    }
}