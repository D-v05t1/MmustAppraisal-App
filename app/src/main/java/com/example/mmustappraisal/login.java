package com.example.mmustappraisal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.viewmodel.AuthViewModelBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import java.util.Objects;

public class login extends AppCompatActivity {
private TextInputEditText Email,Password;
private Button bttn;
private TextView navigate;
private FirebaseAuth sAuth;
private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.lemail);
        Password = findViewById(R.id.lpassword);
        bttn = findViewById(R.id.slogin);
        navigate = findViewById(R.id.reg);
        sAuth = FirebaseAuth.getInstance();
        pb=findViewById(R.id.progressbar);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(login.this, Register.class);
                startActivity(p);
            }
        });
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = Email.getText().toString();
                String pass = Password.getText().toString();
                if (TextUtils.isEmpty(mail) && TextUtils.isEmpty(pass)||TextUtils.isEmpty(mail) && !TextUtils.isEmpty(pass)||
                        !TextUtils.isEmpty(mail) && TextUtils.isEmpty(pass)) {
                    Toast.makeText(login.this, "Empty field", Toast.LENGTH_SHORT).show();
                } else {
                    signin(mail,pass);

                }
            }
        });

    }

    private void signin(String mail, String pass) {
pb.setVisibility(View.VISIBLE);
sAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        pb.setVisibility(View.GONE);
        if (task.isSuccessful()){
        Intent c=new Intent(login.this,MainActivity.class);
c.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(c);
        finish();
        }else{
            Toast.makeText(login.this, task.getException().getMessage() , Toast.LENGTH_SHORT).show();

        }
    }
});
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=sAuth.getCurrentUser();
        if(user!=null){
            Intent s=new Intent(login.this,MainActivity.class);
            startActivity(s);
            this.finish();
        }
    }
}