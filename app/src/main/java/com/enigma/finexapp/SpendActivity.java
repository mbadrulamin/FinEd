package com.enigma.finexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpendActivity extends AppCompatActivity {

    TextView mStartSpend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend);

        mStartSpend = findViewById(R.id.start_spend);

        mStartSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpendActivity.this, spendStartActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}