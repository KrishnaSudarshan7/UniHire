package com.example.unihire.ui.dashboardApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unihire.Job;
import com.example.unihire.MainActivity;
import com.example.unihire.MyAdapterActiveJobs;
import com.example.unihire.MyAdapterAllJobs;
import com.example.unihire.MyAdapterDraft;
import com.example.unihire.R;
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


public class DashboardFragmentApp extends Fragment {

    Button SignOut;
    RecyclerView recyclerView;
    DatabaseReference ref;
    MyAdapterAllJobs myAdapterAll;
    ArrayList<Job> list;
    ArrayList<Job>list1;
    FirebaseAuth fAuth;
    TextView tt;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_dashboard_app, container, false);


        SignOut=view.findViewById(R.id.signOutBtnApp);
        recyclerView=view.findViewById(R.id.recent3_rv);
        ref= FirebaseDatabase.getInstance().getReference();
        fAuth=FirebaseAuth.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list=new ArrayList<>();
        list1=new ArrayList<>();
        myAdapterAll=new MyAdapterAllJobs(getActivity(),list1);
        recyclerView.setAdapter(myAdapterAll);
        tt=view.findViewById(R.id.dashboardAppText1);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.child("Application").getChildren()){
                    //tt.setText(dataSnapshot.child("ApplicantID").getValue().toString());
                    if(dataSnapshot.child("ApplicantID").getValue().toString().equals(fAuth.getUid())){
                        String jobId=dataSnapshot.child("JobID").getValue().toString();
                        //tt.setText(jobId);
                        Job job = snapshot.child("Job").child(jobId).getValue(Job.class);
                        list.add(job);
                    }
                }
                Collections.sort(list, new Comparator<Job>() {
                    @Override
                    public int compare(Job o1, Job o2) {
                        SimpleDateFormat one=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        return -(o1.PostedDateTime.compareTo(o2.PostedDateTime));
                    }
                });
                for(int i=0;i<list.size();i++){
                    list1.add(list.get(i));
                }


                myAdapterAll.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}