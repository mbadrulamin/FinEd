package com.enigma.finexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class ProtectActivity extends AppCompatActivity {

    private Button askHint;
    private ImageView checkAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect);

        final Dialog dialog = new Dialog(ProtectActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(false);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.pop_question_layout);

        dialog.show();

        askHint = dialog.findViewById(R.id.popQuiz_hintButton);
        checkAnswer = dialog.findViewById(R.id.popQuiz_checkAnswer);

        askHint.setOnClickListener(v -> {
            //Code for get Hint from the lecture
        });

        checkAnswer.setOnClickListener(v -> {
            dialog.dismiss();
        });

    }
}