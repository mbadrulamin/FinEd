package com.enigma.finexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProtectActivity extends AppCompatActivity {

    private Button askHint, okeyButton;
    private ImageView checkAnswer;
    private DatabaseReference mStudent, getHintDB;
    private TextView hint_text;
    private EditText quizAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect);

        mStudent = FirebaseDatabase.getInstance().getReference().child("hint");
        getHintDB = FirebaseDatabase.getInstance().getReference().child("hintAnswer");

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
        quizAnswer = dialog.findViewById(R.id.popQuiz_answer);

        askHint.setOnClickListener(v -> {
            getHint();
            Toast.makeText(this, "Request for hint has been send to the lecturer", Toast.LENGTH_LONG).show();
        });

        checkAnswer.setOnClickListener(v -> {
            String answer = quizAnswer.getText().toString();
            if(!answer.equals("Scam")){
                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        getHintDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists() && snapshot.getChildrenCount() > 0){

                    if(snapshot.child("hintAnswer") != null){
                        final Dialog dialog = new Dialog(ProtectActivity.this);
                        //We have added a title in the custom layout. So let's disable the default title.
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
                        dialog.setCancelable(true);
                        //Mention the name of the layout of your custom dialog.
                        dialog.setContentView(R.layout.get_hint_layout);

                        okeyButton = dialog.findViewById(R.id.hintPop_correct);
                        hint_text = dialog.findViewById(R.id.getHint);

                        hint_text.setText(snapshot.child("hintAnswer").getValue().toString());

                        okeyButton.setOnClickListener(v -> {
                            getHintDB.child("hintAnswer").removeValue();
                            dialog.dismiss();
                        });

                        dialog.show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getHint(){

        String number = "1";

        Map userHint =  new HashMap();
        userHint.put("hintID",number);
        mStudent.updateChildren(userHint);

    }

}