package com.enigma.finexapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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


    private Button mGrabDriverJob, mOK;
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
        //Pop up congratulate
        final Dialog dialog = new Dialog(JobDescActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.earn_popup);

        //Initializing the views of the dialog.
        mOK = dialog.findViewById(R.id.ok_button);
        mOK.setOnClickListener(v1 -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        });
        dialog.show();

    }

}