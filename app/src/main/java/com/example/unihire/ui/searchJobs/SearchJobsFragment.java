package com.example.unihire.ui.searchJobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unihire.FilterInput;
import com.example.unihire.Job;
import com.example.unihire.MyAdapterAllJobs;
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


public class SearchJobsFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference ref;
    MyAdapterAllJobs myAdapterAll;
    ArrayList<Job> list;
    ArrayList<Job>list1;
    FirebaseAuth fAuth;
    Button searchBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_search_job, container, false);

         //This is how you from xml
        searchBtn=(Button) view.findViewById(R.id.searchBtn);
        recyclerView=view.findViewById(R.id.recycler_view_all);
        ref= FirebaseDatabase.getInstance().getReference();
        ref= FirebaseDatabase.getInstance().getReference("Job");
        fAuth=FirebaseAuth.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list=new ArrayList<>();
        list1=new ArrayList<>();
        myAdapterAll=new MyAdapterAllJobs(getActivity(),list1);
        recyclerView.setAdapter(myAdapterAll);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Job job = dataSnapshot.getValue(Job.class);
                    if(job.jobStatus.equals("active"))
                        list.add(job);
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

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FilterInput.class);
                startActivity(intent);
            }
        });
        return view;
    }


}