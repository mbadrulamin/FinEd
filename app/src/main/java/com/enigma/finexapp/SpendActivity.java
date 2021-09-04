package com.enigma.finexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpendActivity extends AppCompatActivity {

    TextView mStartSpend, mCheckAnswer;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10;
    Boolean c1 = false, c2 = false, c3 = false, c4 = false, c5 = false, c6 = false, c7 = false, c8 = false, c9 = false, c10 = false;

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


        mCheckAnswer = findViewById(R.id.checkAnswer);

        mCheckAnswer.setOnClickListener(v -> {
            if (c1 && c4 && c5 && c7 && c10){
                //Pop up congratulate
            }
        });

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);
        checkBox9 = findViewById(R.id.checkBox9);
        checkBox10 = findViewById(R.id.checkBox10);

        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                c1 = true;
            }
            else{
                c1 = false;
            }
        });

        checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                c2 = true;
            }
            else{
                c2 = false;
            }
        });

        checkBox3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                c3 = true;
            }
            else{
                c3 = false;
            }
        });

        checkBox4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                c4 = true;
            }
            else{
                c4 = false;
            }
        });

        checkBox5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                c5 = true;
            }
            else{
                c5 = false;
            }
        });

        checkBox6.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                c6 = true;
            }
            else{
                c6 = false;
            }
        });

        checkBox7.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                c7 = true;
            }
            else{
                c7 = false;
            }
        });

        checkBox8.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                c8 = true;
            }
            else{
                c8 = false;
            }
        });

        checkBox9.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                c9 = true;
            }
            else{
                c9 = false;
            }
        });

        checkBox10.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                c10 = true;
            }
            else{
                c10 = false;
            }
        });




    }
}