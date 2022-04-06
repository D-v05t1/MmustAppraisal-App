package com.example.mmustappraisal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Edtraining extends AppCompatActivity {
    private TextInputEditText comments,duration,agreed_Training;
    private Button send;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edtraining);
        comments=findViewById(R.id.trainingcomments);
        duration=findViewById(R.id.traininduration);
        agreed_Training=findViewById(R.id.trainingagreed);
        send=findViewById(R.id.trainingsend);
        firebaseDatabase=FirebaseDatabase.getInstance();
        dref=firebaseDatabase.getReference().child("training");
        Toolbar toolbar =findViewById(R.id.topbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String training_Agreed=agreed_Training.getText().toString();
                String training_duration=duration.getText().toString();
                String training_comments=comments.getText().toString();
            Training_Modal tm=new Training_Modal(training_Agreed, training_duration, training_comments);
            dref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                  dref.setValue(tm);
                    Toast.makeText(Edtraining.this,"Data captured successfully",Toast.LENGTH_SHORT).show();
                    Intent x=new Intent(Edtraining.this,MainActivity.class);
                    startActivity(x);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Edtraining.this,"Error"+error.toString(),Toast.LENGTH_SHORT).show();

                }
            });
            }
        });
    }
}