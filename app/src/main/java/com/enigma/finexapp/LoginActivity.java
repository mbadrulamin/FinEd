package com.enigma.finexapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.NotNull;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmail, mPassword, resetPass;
    private Button mLogin, mbackToHome ,resetPassDialog;

    private TextView mRegister, mForgetPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };


        mEmail = (EditText) findViewById(R.id.emailUser);
        mPassword = (EditText) findViewById(R.id.passwordUser);
        mLogin = (Button) findViewById(R.id.loginUser);
        mRegister = findViewById(R.id.registerUser);
        mForgetPassword = findViewById(R.id.forget_passwordUser);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate Login Info
                if (!validateEmail() | !validatePassword()) {
                    return;
                } else {
                    final String email = mEmail.getText().toString();
                    final String password = mPassword.getText().toString();
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Sign in error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });


        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(LoginActivity.this);
                //We have added a title in the custom layout. So let's disable the default title.
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
                dialog.setCancelable(true);
                //Mention the name of the layout of your custom dialog.
                dialog.setContentView(R.layout.forget_password);

                //Initializing the views of the dialog.
                resetPass = dialog.findViewById(R.id.editTextResetPassword);
                resetPassDialog = dialog.findViewById(R.id.resetButton);

                resetPassDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //get email and send reset link
                        String userEmail = resetPass.getText().toString();
                        try {
                            mAuth.sendPasswordResetEmail(userEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplication(), "Password reset email sent", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    Toast.makeText(getApplication(), "Failed! Please try again", Toast.LENGTH_LONG).show();
                                }
                            });
                            dialog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Email field cannot be empty", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();

            }
        });
    }




    private Boolean validateEmail() {
        String emailInput = mEmail.getText().toString();
        if (emailInput.isEmpty()) {
            mEmail.setError("Field cannot be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mEmail.setError("Please enter a valid email address");
            return false;
        } else {
            mEmail.setError(null);
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
        String passwordInput = mPassword.getText().toString();
        if (passwordInput.isEmpty()) {
            mPassword.setError("Field cannot be empty");
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            mPassword.setError("Wrong Password");
            return false;
        } else {
            mPassword.setError(null);
            return true;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent_back = new Intent(LoginActivity.this,HomeActivity.class);
//        startActivity(intent_back);
//        finish();
//    }

}