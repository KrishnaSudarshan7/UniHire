package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class MultiSearch extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference ref;
    MyMultiSearchAdapter myAdaptermulti;
    ArrayList<Job> list;
    FirebaseAuth fAuth;
    EditText title, department;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_search);

        Intent intent=getIntent();
        String text_title= intent.getStringExtra(FilterInput.EXTRA_TITLE);
        String text_dept= intent.getStringExtra(FilterInput.EXTRA_DEPT);
        String text_specialization= intent.getStringExtra(FilterInput.EXTRA_SPECIALIZATION);
        String text_univname = intent.getStringExtra(FilterInput.EXTRA_UNIVNAME);

        String uId=FirebaseAuth.getInstance().getUid();

        recyclerView=findViewById(R.id.recycler_multi_search);
        ref= FirebaseDatabase.getInstance().getReference();
        fAuth=FirebaseAuth.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        myAdaptermulti=new MyMultiSearchAdapter(this,list);
        recyclerView.setAdapter(myAdaptermulti);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.child("Job").getChildren()){
                    Job job = dataSnapshot.getValue(Job.class);
                    String univIDStr= job.UnivId;
                    String uname= snapshot.child("University").child(univIDStr).child("univName").getValue().toString();
                    if(job.jobStatus.equals("active")) {
                        if(job.JobTitle.toLowerCase().contains(text_title.toLowerCase().trim()) && job.Department.toLowerCase().contains(text_dept.toLowerCase().trim()) &&
                         job.Specialization.toLowerCase().contains(text_specialization.toLowerCase().trim())&& uname.toLowerCase().contains(text_univname.toLowerCase().trim())){
                            list.add(job);
                        }
                        else
                            continue;
                    }
                }
                Collections.sort(list, new Comparator<Job>() {
                    @Override
                    public int compare(Job o1, Job o2) {
                        SimpleDateFormat one=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        return -(o1.PostedDateTime.compareTo(o2.PostedDateTime));
                    }
                });
                myAdaptermulti.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
