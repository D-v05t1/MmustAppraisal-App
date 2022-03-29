package com.example.mmustappraisal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button goals,targets,dev,values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goals=findViewById(R.id.obj);
        targets=findViewById(R.id.targets);
        dev=findViewById(R.id.training);
        values=findViewById(R.id.values);
        goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g=new Intent(MainActivity.this,Edtobjective.class);
                startActivity(g);
            }
        });
        targets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t=new Intent(MainActivity.this,Edttargets.class);
                startActivity(t);
            }
        });
        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d=new Intent(MainActivity.this,Edtraining.class);
                startActivity(d);
            }
        });
        values.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v=new Intent(MainActivity.this,Edtvandcovalues.class);
                startActivity(v);
            }
        });
    }
}