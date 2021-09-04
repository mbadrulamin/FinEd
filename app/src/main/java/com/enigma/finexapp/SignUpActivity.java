package com.enigma.finexapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEmailField, mPasswordField, mNameField, mStaffIdField, mPhoneField, mBusRegistrationNumberField;
    private Button mSignup, mBack;
    private String user_id;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private DatabaseReference mUserDatabase;

    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailField = findViewById(R.id.emailSignup);
        mPasswordField = findViewById(R.id.passwordSignup);
        mNameField = findViewById(R.id.nameSignup);
        mSignup = findViewById(R.id.signupButton);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();


        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate signup info
                if (!validateEmail() | !validatePassword() | !validateName() ) {

                    return;
                }

                final String email = mEmailField.getText().toString();
                final String password = mPasswordField.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Sign Up Error", Toast.LENGTH_SHORT).show();
                        } else {
                            spinner.setVisibility(View.VISIBLE);
                            user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                            current_user_db.setValue(true);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                                    saveUserInformation();

                                    Intent intent = new Intent(SignUpActivity.this, EarnActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            },5000);
                        }
                    }
                });



            }
        });
    }


    private void saveUserInformation() {
        String mName = mNameField.getText().toString();
        Integer mCredit = 1000;
        Integer mSpend = 0;

        Map userInfo = new HashMap();
        userInfo.put("name", mName);
        userInfo.put("balance", mCredit);
        userInfo.put("spend", mSpend);

        mUserDatabase.updateChildren(userInfo);
        finish();

    }


    private Boolean validateEmail() {
        String emailInput = mEmailField.getText().toString();
        if (emailInput.isEmpty()) {
            mEmailField.setError("Field cannot be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mEmailField.setError("Please enter a valid email address");
            return false;
        } else {
            mEmailField.setError(null);
            return true;
        }
    }

    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

    private Boolean validatePassword() {

        // if password field is empty
        // it will display error message "Field can not be empty"
        String passwordInput = mPasswordField.getText().toString();
        if (passwordInput.isEmpty()) {
            mPasswordField.setError("Field cannot be empty");
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            mPasswordField.setError("Password is too weak");
            return false;
        } else {
            mPasswordField.setError(null);
            return true;
        }
    }

    private Boolean validateName() {
        String val = mNameField.getText().toString();
        if (val.isEmpty()) {
            mNameField.setError("Field cannot be empty");
            return false;
        } else {
            mNameField.setError(null);
            return true;
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent_back = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent_back);
        finish();
    }

}