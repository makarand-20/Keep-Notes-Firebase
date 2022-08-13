package com.example.notesapptutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity {

    RatingBar ratingBar;
    RadioGroup radiogrp;
    RadioButton good,modrate,bad;
    EditText feed;
    Button btn;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().hide();

        feed = findViewById(R.id.feed);
        radiogrp = findViewById(R.id.radiogrp);
        ratingBar = findViewById(R.id.ratingBar);
        btn = findViewById(R.id.btn);

        good = findViewById(R.id.good);
        modrate = findViewById(R.id.modrate);
        bad = findViewById(R.id.bad);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float star = ratingBar.getRating();

                String feedback = feed.getText().toString();
                String radiobtn = "";

                if (good.isChecked()){
                    radiobtn = good.getText().toString();
                }else if (modrate.isChecked()){
                    radiobtn = modrate.getText().toString();
                }else {
                    radiobtn = bad.getText().toString();
                }

                if (feedback.isEmpty()){
                    Toast.makeText(FeedbackActivity.this, "Enter Your Feedback", Toast.LENGTH_SHORT).show();
                }else {
                DocumentReference documentReference=firebaseFirestore.collection("notes").document(firebaseUser.getEmail()).collection("feedback").document();
                Map<String ,Object> fb= new HashMap<>();
                fb.put("feedback",feedback);
                fb.put("status",radiobtn);
                fb.put("rating",star);

                    documentReference.set(fb).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Thank You For Feedback",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FeedbackActivity.this,notesactivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Failed To Send Feedback",Toast.LENGTH_SHORT).show();
                        }
                    });
            }
            }
        });

    }
}