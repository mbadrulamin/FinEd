package com.enigma.finexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class EarnActivity extends AppCompatActivity {

    CardView mEarnGrabDriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn);

        mEarnGrabDriver = findViewById(R.id.earnGrabDriver);

        mEarnGrabDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarnActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}