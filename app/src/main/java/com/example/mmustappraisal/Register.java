package com.example.mmustappraisal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class Register extends AppCompatActivity {
private TextInputEditText email,password,confirmnpasssword,personalno, faculty, unit,
        designation,jobgroup,speciladuty,supervisorname;
private RadioGroup gender;
private Button register,loginme;
private FirebaseAuth fAuth;
private DatabaseReference dbref;
private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pb=findViewById(R.id.pbar);
        email=findViewById(R.id.regemail);
        password=findViewById(R.id.rpassword);
        confirmnpasssword=findViewById(R.id.cnpassword);
        personalno=findViewById(R.id.regpersonalno);
        faculty=findViewById(R.id.regfaculty);
        unit=findViewById(R.id.regunit);
        designation=findViewById(R.id.regdesignation);
        jobgroup=findViewById(R.id.regjobgroup);
        speciladuty=findViewById(R.id.regspduty);
        supervisorname=findViewById(R.id.regsupervisorname);
        register=findViewById(R.id.regokay);
        loginme=findViewById(R.id.login);
        gender=findViewById(R.id.gen);
        fAuth =FirebaseAuth.getInstance();
        loginme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          Intent p=new Intent(Register.this,login.class);
          startActivity(p);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrmail = email.getText().toString();
                String pass = password.getText().toString();
                String confirm = confirmnpasssword.getText().toString();
                String no = personalno.getText().toString();
                String fac = faculty.getText().toString();
                String eunit = unit.getText().toString();
                String des = designation.getText().toString();
                int checkedId = gender.getCheckedRadioButtonId();
                String jobgrp = jobgroup.getText().toString();
                String special = speciladuty.getText().toString();
                String supervisor = supervisorname.getText().toString();
                RadioButton sgender = gender.findViewById(checkedId);
                if (sgender == null) {
                    Toast.makeText(Register.this, "select gender please", Toast.LENGTH_SHORT).show();
                } else if (!usrmail.contains("@gmail.com")) {
                    Toast.makeText(Register.this, "wrong mail format", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(usrmail) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirm)
                        || TextUtils.isEmpty(no) || TextUtils.isEmpty(fac) || TextUtils.isEmpty(eunit) || TextUtils.isEmpty(des) ||
                        TextUtils.isEmpty(jobgrp) || TextUtils.isEmpty(special) || TextUtils.isEmpty(supervisor)) {
                    Toast.makeText(Register.this, "Hello, check all fields please", Toast.LENGTH_SHORT).show();
                } else {
                    final String gender = sgender.getText().toString();
                    create(usrmail, pass, no, fac, eunit, des, jobgrp, special, supervisor);
                }

            }
        });
    }

    private void create(String usrmail, String pass, String no, String fac, String eunit, String des, String jobgrp, String special, String supervisor) {
        pb.setVisibility(View.VISIBLE);
        fAuth.createUserWithEmailAndPassword(usrmail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
    @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
        {
            if (task.isSuccessful()) {
                FirebaseUser rUser;
                rUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId = rUser.getUid();
               dbref = FirebaseDatabase.getInstance().getReference("User").child(userId);

                HashMap<String, String> hashmap = new HashMap<>();

                hashmap.put("userid", userId);
                hashmap.put("usrmail", usrmail);
                hashmap.put("no", no);
                hashmap.put("fac", fac);
                hashmap.put("eunit", eunit);
                hashmap.put("des", des);
                hashmap.put("jobgrp", jobgrp);
                hashmap.put("special", special);
                hashmap.put("supervisor", supervisor);
                dbref.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pb.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Intent r = new Intent(Register.this, MainActivity.class);
                            r.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(r);
                            finish();
                        } else {
                            Toast.makeText(Register.this, Objects.requireNonNull(task.getException().getMessage()), Toast.LENGTH_SHORT).show();
                        }

                    }
                });}
              else{
                Toast.makeText(Register.this, Objects.requireNonNull(task.getException().getMessage()), Toast.LENGTH_SHORT).show();
               }

                }

            } 


    });
    }


        }
/**/