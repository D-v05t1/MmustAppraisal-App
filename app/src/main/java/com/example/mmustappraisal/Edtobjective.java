package com.example.mmustappraisal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class Edtobjective extends AppCompatActivity {
        private EditText perfomance_I1,campus,designation,Agreed_per_I1,proof1,Agreed_target1,perfomance_I2,Agreed_per_I2
        ,proof2,Agreed_target2,selfmark2,perfomance_I3,perfomance_I4,perfomance_I5,Agreed_per_I3,Agreed_per_I4,Agreed_per_I5
        ,proof3,proof4,proof5,Agreed_target3,Agreed_target4,Agreed_target5,selfmark1,selfmark3,selfmark4,selfmark5,name;
        private Button send,viewall,sendnw;
        private FirebaseDatabase fd;
        private DatabaseReference dr;
        private String objectiveOwnerId;
        private FloatingActionButton addview;
        private boolean seeview;
        private RelativeLayout objective_view1,objective_view2,objective_view3,objective_view4,objective_view5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edtobjective);
        Toolbar toolbar =findViewById(R.id.topbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fd=FirebaseDatabase.getInstance();
        dr=fd.getReference().child("Objectives");

        name=findViewById(R.id.r1a);
        campus=findViewById(R.id.r2);
        designation=findViewById(R.id.r3);
        sendnw=findViewById(R.id.ln);


        perfomance_I5=findViewById(R.id.indicator5a);
        Agreed_per_I5=findViewById(R.id.viewmore5);
        proof5=findViewById(R.id.proof5);
        Agreed_target5=findViewById(R.id.Agreedperformance5);
        selfmark5=findViewById(R.id.selfmark5);

        perfomance_I4=findViewById(R.id.indicator4a);
        Agreed_per_I4=findViewById(R.id.viewmore4);
        proof4=findViewById(R.id.proof4);
        Agreed_target4=findViewById(R.id.Agreedperformance4);
        selfmark4=findViewById(R.id.selfmark4);

        perfomance_I3=findViewById(R.id.indicator3a);
        Agreed_per_I3=findViewById(R.id.viewmore3);
        proof3=findViewById(R.id.proof3);
        Agreed_target3=findViewById(R.id.Agreedperformance3);
        selfmark3=findViewById(R.id.selfmark3);

        perfomance_I1=findViewById(R.id.indicator1a);
        Agreed_per_I1=findViewById(R.id.viewmore1);
        proof1=findViewById(R.id.proof1);
        Agreed_target1=findViewById(R.id.Agreedperformance);
        selfmark1=findViewById(R.id.selfmark1);

        perfomance_I2=findViewById(R.id.indicator2a);
        Agreed_per_I2=findViewById(R.id.viewmore2);
        proof2=findViewById(R.id.proof2);
        Agreed_target2=findViewById(R.id.Agreedperformance2);
        selfmark2=findViewById(R.id.selfmark2);

        send=findViewById(R.id.sendtargets);
        addview=findViewById(R.id.floatadd);
        objective_view2=findViewById(R.id.sl2);
        objective_view1=findViewById(R.id.sll2);
        objective_view3=findViewById(R.id.sll3);
        objective_view4=findViewById(R.id.sll4);
        objective_view5=findViewById(R.id.sll5);
        viewall=findViewById(R.id.viewall);
        sendnw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _name = name.getText().toString();
                String _campus = campus.getText().toString();
                String _designation = designation.getText().toString();
            }
        });

        seeview=true;
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seeview){
                objective_view1.setVisibility(View.VISIBLE);
                objective_view2.setVisibility(View.VISIBLE);
                objective_view3.setVisibility(View.VISIBLE);
                objective_view4.setVisibility(View.VISIBLE);
                objective_view5.setVisibility(View.VISIBLE);
                seeview=true;
                }
                else{
                    objective_view1.setVisibility(View.VISIBLE);
                    objective_view2.setVisibility(View.GONE);
                    objective_view3.setVisibility(View.GONE);
                    objective_view4.setVisibility(View.GONE);
                    objective_view5.setVisibility(View.GONE);
                    seeview=false;
                }
            }
        });
        addview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(objective_view1.getVisibility()==View.VISIBLE){
             objective_view2.setVisibility(View.VISIBLE);
             objective_view1.setVisibility(View.GONE);}
            else if(objective_view2.getVisibility()==View.VISIBLE){
                    objective_view3.setVisibility(View.VISIBLE);
                    objective_view2.setVisibility(View.GONE);
                }
                else if(objective_view3.getVisibility()==View.VISIBLE){
                    objective_view4.setVisibility(View.VISIBLE);
                    objective_view3.setVisibility(View.GONE);
                }
                else if(objective_view4.getVisibility()==View.VISIBLE){
                    objective_view5.setVisibility(View.VISIBLE);
                    objective_view4.setVisibility(View.GONE);
                }
            else{
                Toast.makeText(Edtobjective.this,"click send or view more to see all fields",Toast.LENGTH_SHORT).show();
                }
            }


        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           String names=name.getText().toString();
           String perfomance_Objective1=perfomance_I1.getText().toString();
           String agreed_Perfomance1=Agreed_per_I1.getText().toString();
           String perfomance_proof1=proof1.getText().toString();
           String percentage_Targets1=Agreed_target1.getText().toString();
           String self_Awardmark1=selfmark1.getText().toString();

                String perfomance_Objective2=perfomance_I2.getText().toString();
                String agreed_Perfomance2=Agreed_per_I2.getText().toString();
                String perfomance_proof2=proof2.getText().toString();
                String percentage_Targets2=Agreed_target2.getText().toString();
                String self_Awardmark2=selfmark2.getText().toString();

                String perfomance_Objective3=perfomance_I3.getText().toString();
                String agreed_Perfomance3=Agreed_per_I3.getText().toString();
                String perfomance_proof3=proof3.getText().toString();
                String percentage_Targets3=Agreed_target3.getText().toString();
                String self_Awardmark3=selfmark3.getText().toString();

                String perfomance_Objective4=perfomance_I4.getText().toString();
                String agreed_Perfomance4=Agreed_per_I4.getText().toString();
                String perfomance_proof4=proof4.getText().toString();
                String percentage_Targets4=Agreed_target4.getText().toString();
                String self_Awardmark4=selfmark4.getText().toString();

                String perfomance_Objective5=perfomance_I5.getText().toString();
                String agreed_Perfomance5=Agreed_per_I5.getText().toString();
                String perfomance_proof5=proof5.getText().toString();
                String percentage_Targets5=Agreed_target5.getText().toString();
                String self_Awardmark5=selfmark5.getText().toString();
                    objectiveOwnerId=names;
                Objective_Modal objective_modal=new Objective_Modal(perfomance_Objective1, names, agreed_Perfomance1,
                        perfomance_proof1, percentage_Targets1,
                        self_Awardmark1, perfomance_Objective2, agreed_Perfomance2, perfomance_proof2,
                        percentage_Targets2, self_Awardmark2, perfomance_Objective3, agreed_Perfomance3,
                        perfomance_proof3, percentage_Targets3, self_Awardmark3, perfomance_Objective4,
                        agreed_Perfomance4, perfomance_proof4, percentage_Targets4, self_Awardmark4,
                        perfomance_Objective5, agreed_Perfomance5, perfomance_proof5, percentage_Targets5,
                        self_Awardmark5);

                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dr.child("objectiveOwnerId").setValue(objective_modal);
                        Toast.makeText(Edtobjective.this,"Data captured",Toast.LENGTH_SHORT).show();
                        Intent o=new Intent(Edtobjective.this,MainActivity.class);
                        startActivity(o);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Edtobjective.this,"Error"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id=item.getItemId();
       if(id==R.id.home){
           Intent h=new Intent(Edtobjective.this,MainActivity.class);
           startActivity(h);
       }
        if(id==R.id.account){
            Intent a=new Intent(Edtobjective.this,Register.class);
            startActivity(a);
        }
        if(id==R.id.logout){
            Intent l=new Intent(Edtobjective.this,MainActivity.class);
            startActivity(l);
        }
        return true;
    }

}