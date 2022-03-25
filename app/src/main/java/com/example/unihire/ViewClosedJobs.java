package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

public class ViewClosedJobs extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference ref;
    MyAdapterClosedJobs myAdapterDraft;
    ArrayList<Job> list;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_closed_jobs);

        recyclerView=findViewById(R.id.recyclerViewClosed);
        ref= FirebaseDatabase.getInstance().getReference("Job");
        fAuth= FirebaseAuth.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        myAdapterDraft=new MyAdapterClosedJobs(this,list);
        recyclerView.setAdapter(myAdapterDraft);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Job job = dataSnapshot.getValue(Job.class);
                    if(job.jobStatus.equals("closed") && job.UnivId.equals(fAuth.getUid()))
                        list.add(job);
                }
                Collections.sort(list, new Comparator<Job>() {
                    @Override
                    public int compare(Job o1, Job o2) {
                        SimpleDateFormat one=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        return -(o1.PostedDateTime.compareTo(o2.PostedDateTime));
                    }
                });
                myAdapterDraft.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}