package com.enigma.finexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class EarnActivity extends AppCompatActivity {

    CardView mEarnGrabDriver, mEarnGrabRider, mEarnOnlineBusiness, mEarnPartTime, mEarnFoodpanda, mEarnScholar, mEarnWaiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn);

        mEarnGrabDriver = findViewById(R.id.earnGrabDriverCard);
        mEarnGrabRider = findViewById(R.id.grabRiderCard);
        mEarnOnlineBusiness = findViewById(R.id.onlineBisCard);
        mEarnPartTime = findViewById(R.id.partTimeCard);
        mEarnFoodpanda = findViewById(R.id.foodPandaCard);
        mEarnScholar = findViewById(R.id.scholarshipCard);
        mEarnWaiter = findViewById(R.id.waiterCard);


        mEarnGrabDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarnActivity.this, GrabCarDriverActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mEarnGrabRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarnActivity.this, GrabRiderActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mEarnOnlineBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarnActivity.this, UnderMaintenanceActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mEarnPartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarnActivity.this, UnderMaintenanceActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mEarnFoodpanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarnActivity.this, UnderMaintenanceActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mEarnScholar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarnActivity.this, UnderMaintenanceActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mEarnWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarnActivity.this, UnderMaintenanceActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

    }
}