package com.example.mmustappraisal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class Register extends AppCompatActivity {
private TextInputEditText email,password,confirmnpasssword,personalno,username;
private Button register,loginme;
private FirebaseDatabase firebaseDatabase;
private DatabaseReference databaseReference;
private ProgressBar pb;
regemployee Regemployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pb=findViewById(R.id.pbar);


        Toolbar toolbar =findViewById(R.id.topbar);
        setSupportActionBar(toolbar);


        email=findViewById(R.id.regemail);
        username=findViewById(R.id.uname);
        password=findViewById(R.id.rpassword);
        confirmnpasssword=findViewById(R.id.cnpassword);
        personalno=findViewById(R.id.regpersonalno);
        register=findViewById(R.id.regokay);
        loginme=findViewById(R.id.login);


        firebaseDatabase=FirebaseDatabase.getInstance();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        regemployee Regemployee=new regemployee();
         databaseReference = firebaseDatabase.getReference("EmployeeInfo");


        loginme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          Intent p=new Intent(Register.this,Edtobjective.class);
          startActivity(p);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrmail = email.getText().toString();
                String usrname = username.getText().toString();
                String pass = password.getText().toString();
                String confirm = confirmnpasssword.getText().toString();
                String no = personalno.getText().toString();



                 if (TextUtils.isEmpty(usrmail) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirm)
                        || TextUtils.isEmpty(no)||TextUtils.isEmpty(usrname) ) {
                    Toast.makeText(Register.this, "Hello, check all fields please", Toast.LENGTH_SHORT).show();
                 }
                 else {

                     add_data_to_firebase(usrmail, usrname, pass, confirm, no);

                 }
            } });}

    private void add_data_to_firebase(String usrmail, String usrname, String pass, String confirm, String no) {

        Regemployee.setUsermail(usrmail);
        Regemployee.setConfirmpasswd(confirm);
        Regemployee.setPasswd(pass);
        Regemployee.setUsername(usrname);
        Regemployee.setNumber(no);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // data base reference will sends data to firebase.
                databaseReference.setValue(Regemployee);


                Toast.makeText(Register.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Register.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }}
