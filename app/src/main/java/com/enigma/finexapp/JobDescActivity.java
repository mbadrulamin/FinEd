package com.enigma.finexapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class JobDescActivity extends AppCompatActivity {


    private Button mGrabDriverJob;
    private String userId;

    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_desc);
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        mGrabDriverJob = findViewById(R.id.selectJob_button);

        mGrabDriverJob.setOnClickListener(v -> {
            saveUserInformation();
        });

    }

    private void saveUserInformation() {
        UserInfo userinfo = new UserInfo();

        Integer balance = userinfo.getBalance() + 10;
        String job = "Grab Driver";
        Map userInfo = new HashMap();
        userInfo.put("balance", balance);
        userInfo.put("job", job);
        mUserDatabase.updateChildren(userInfo);
        finish();

    }

}