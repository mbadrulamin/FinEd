package com.enigma.finexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.enigma.finexapp.bottomnavview.NotificationFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PopUpScam extends AppCompatActivity {

    private Button mYES, mNO, mOKLost, mOKWin;
    private String userId;

    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_scam);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        mYES = findViewById();
        mNO = findViewById();

        mYES.setOnClickListener(v -> {
            userLost();
        });

        mNO.setOnClickListener(v -> {
            userWin();
        });

    }

    private void userLost() {
        Integer balance = 0;
        Map userInfo = new HashMap();
        userInfo.put("balance", balance);
        mUserDatabase.updateChildren(userInfo);
        //Pop up congratulate
        final Dialog dialog = new Dialog(PopUpScam.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.lost_noti_popup);

        //Initializing the views of the dialog.
        mOKLost = dialog.findViewById(R.id.okLost_button);
        mOKLost.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        });
        dialog.show();
    }

    private void userWin() {
        UserInfo userinfo = new UserInfo();

        Integer balance = userinfo.getBalance() + 50;
        Map userInfo = new HashMap();
        userInfo.put("balance", balance);
        mUserDatabase.updateChildren(userInfo);
        //Pop up congratulate
        final Dialog dialog = new Dialog(PopUpScam.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.reward_noti_popup);

        //Initializing the views of the dialog.
        mOKWin = dialog.findViewById(R.id.okWin_button);
        mOKWin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        });
        dialog.show();
    }
}