package com.enigma.finexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class spendStartActivity extends AppCompatActivity {


    TextView mBalance;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    Integer total = 0;
    Button mPay;
    UserInfo userinfo = new UserInfo();
    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend_start);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);


        UserInfo userInfo = new UserInfo();
        mBalance = findViewById(R.id.balanceMoney);
        mBalance.setText("RM" + userInfo.getBalance());
        mPay = findViewById(R.id.total_price);

        checkBox1 = findViewById(R.id.checkBoxSpend1);
        checkBox2 = findViewById(R.id.checkBoxSpend2);
        checkBox3 = findViewById(R.id.checkBoxSpend3);
        checkBox4 = findViewById(R.id.checkBoxSpend4);
        checkBox5 = findViewById(R.id.checkBoxSpend5);

        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                total += 20;
                mPay.setText("PAY RM" + total);
            }
            else {
                total -=20;
                mPay.setText("PAY RM" + total);
            }
        });

        checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                total += 70;
                mPay.setText("PAY RM" + total);
            }
            else {
                total -=70;
                mPay.setText("PAY RM" + total);
            }
        });

        checkBox3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                total += 250;
                mPay.setText("PAY RM" + total);
            }
            else {
                total -=250;
                mPay.setText("PAY RM" + total);
            }
        });

        checkBox4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                total += 500;
                mPay.setText("PAY RM" + total);
            }
            else {
                total -=500;
                mPay.setText("PAY RM" + total);
            }
        });

        checkBox5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                total += 2000;
                mPay.setText("PAY RM" + total);
            }
            else {
                total -=2000;
                mPay.setText("PAY RM" + total);
            }
        });

        mPay.setOnClickListener(v -> {

            saveUserInformation();

        });
    }


    private void saveUserInformation() {
        userinfo.setBalance(userinfo.getBalance() - total);
        userinfo.setSpend(userinfo.getSpend() + total);

        Integer balance = userinfo.getBalance();
        Integer spend = userinfo.getSpend();

        Map userInfo = new HashMap();
        userInfo.put("balance", balance);
        userInfo.put("spend", spend);
        mUserDatabase.updateChildren(userInfo);
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
        finish();

    }
}