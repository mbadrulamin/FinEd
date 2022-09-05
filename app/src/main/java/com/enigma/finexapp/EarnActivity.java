package com.enigma.finexapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class EarnActivity extends AppCompatActivity {

    private CardView mEarnGrabDriver, mEarnGrabRider, mEarnOnlineBusiness, mEarnPartTime, mEarnFoodpanda, mEarnScholar, mEarnWaiter;
    private Button inaqAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn);

        mEarnGrabDriver = findViewById(R.id.introProtectCard);
        mEarnGrabRider = findViewById(R.id.grabRiderCard);
        mEarnOnlineBusiness = findViewById(R.id.onlineBisCard);
        mEarnPartTime = findViewById(R.id.partTimeCard);
        mEarnFoodpanda = findViewById(R.id.foodPandaCard);
        mEarnScholar = findViewById(R.id.scholarshipCard);
        mEarnWaiter = findViewById(R.id.waiterCard);

        final Dialog dialog = new Dialog(EarnActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.inaq_element_layout);

        dialog.show();

        inaqAnswer = dialog.findViewById(R.id.inaq_answerButton);

        inaqAnswer.setOnClickListener(v -> {
            dialog.dismiss();
        });

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