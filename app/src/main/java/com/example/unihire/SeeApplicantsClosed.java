package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SeeApplicantsClosed extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference ref;
    MyAdapterClosedApplicantList myAdapterApplicants;
    ArrayList<Applicant> list;
    FirebaseAuth fAuth;
    Button shortlistBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_applicants_closed);
        String JOBID = getIntent().getStringExtra("JOBID");
        recyclerView=findViewById(R.id.AppListClosed_rv);
        ref= FirebaseDatabase.getInstance().getReference();
        fAuth= FirebaseAuth.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Applicant>();
        myAdapterApplicants=new MyAdapterClosedApplicantList(this,list);
        recyclerView.setAdapter(myAdapterApplicants);
        shortlistBtn=findViewById(R.id.shortlistclosed);
        shortlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeeApplicantsClosed.this,shortlistCandidatesClosedJobs.class);
                intent.putExtra("JOBID",JOBID);
                startActivity(intent);
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.child("Application").getChildren()){
                    if(dataSnapshot.child("JobID").getValue().toString().equals(JOBID) &&
                            snapshot.child("Job").child(JOBID).child("jobStatus").getValue().toString().equals("closed")){
                        String app_id= (String) dataSnapshot.child("ApplicantID").getValue();
                        String name=snapshot.child("Applicant").child(app_id).child("Name").getValue().toString();
                        String email=snapshot.child("Applicant").child(app_id).child("Email").getValue().toString();
                        //Toast.makeText(SeeApplicantsClosed.this, name+" "+email, Toast.LENGTH_SHORT).show();
                        Applicant app=new Applicant(name,email);
                        list.add(app);
                        myAdapterApplicants.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}