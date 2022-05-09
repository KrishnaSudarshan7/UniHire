package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewJobInfoPage extends AppCompatActivity {

    Button applyBtn;
    TextView jobTitle,UnivName,Dept,Spec,jd;
    FirebaseAuth fAuth;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_info_page);

        String JOBID = getIntent().getStringExtra("JOBID");
        applyBtn=findViewById(R.id.applyJobBtn);
        jobTitle=findViewById(R.id.jobTitleDisp);
        UnivName=findViewById(R.id.univDisp);
        Dept=findViewById(R.id.deptDisp);
        Spec=findViewById(R.id.specDisp);
        jd=findViewById(R.id.jdTextBox);

        fAuth=FirebaseAuth.getInstance();
        reff= FirebaseDatabase.getInstance().getReference();
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobTitle.setText(snapshot.child("Job").child(JOBID).child("JobTitle").getValue().toString());
                String UnivId= snapshot.child("Job").child(JOBID).child("UnivId").getValue().toString();
                UnivName.setText(snapshot.child("University").child(UnivId).child("univName").getValue().toString());
                Dept.setText(snapshot.child("Job").child(JOBID).child("Department").getValue().toString());
                Spec.setText(snapshot.child("Job").child(JOBID).child("Specialization").getValue().toString());
                jd.setText(snapshot.child("Job").child(JOBID).child("JobDescription").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewJobInfoPage.this, ApplyJobFormPage.class);
                intent.putExtra("JOBID", JOBID);
                startActivity(intent);
            }
        });



    }
}