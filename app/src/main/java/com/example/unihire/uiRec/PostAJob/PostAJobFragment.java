package com.example.unihire.uiRec.PostAJob;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unihire.DraftJobsList;
import com.example.unihire.Job;
import com.example.unihire.MyAdapterDraft;
import com.example.unihire.PostJobForm;
import com.example.unihire.R;
import com.example.unihire.RecruiterHomePage;
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

public class PostAJobFragment extends Fragment {

    ImageView postBtn;
    Button moreBtn;

    RecyclerView recyclerView;
    DatabaseReference ref;
    MyAdapterDraft myAdapterDraft;
    ArrayList<Job>list;
    ArrayList<Job>list1;
    FirebaseAuth fAuth;
    ProgressBar pb;

    //TextView noJobText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_post_a_job, container, false);
        moreBtn = (Button) view.findViewById(R.id.moreBtn);
        postBtn = (ImageView) view.findViewById(R.id.postJobBtn);

        moreBtn.setVisibility(View.INVISIBLE);
        pb=view.findViewById(R.id.PJFragmentPB);
        pb.setVisibility(View.VISIBLE);
        recyclerView=view.findViewById(R.id.recent2_rv);
        ref=FirebaseDatabase.getInstance().getReference();
        ref= FirebaseDatabase.getInstance().getReference("Job");
        fAuth=FirebaseAuth.getInstance();
        //noJobText=(TextView) view.findViewById(R.id.noJobText);
        //noJobText.setVisibility(View.INVISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list=new ArrayList<>();
        list1=new ArrayList<>();
        myAdapterDraft=new MyAdapterDraft(getActivity(),list1);
        recyclerView.setAdapter(myAdapterDraft);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Job job = dataSnapshot.getValue(Job.class);
                    if(job.isDraft && job.UnivId.equals(fAuth.getUid()))
                        list.add(job);
                }
                Collections.sort(list, new Comparator<Job>() {
                    @Override
                    public int compare(Job o1, Job o2) {
                        SimpleDateFormat one=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        return -(o1.PostedDateTime.compareTo(o2.PostedDateTime));
                    }
                });
                for(int i=0;i<list.size() && i<2;i++){
                    list1.add(list.get(i));
                }
                pb.setVisibility(View.INVISIBLE);
                if(list.size()!=0){
                    moreBtn.setVisibility(View.VISIBLE);
                }else{
                    //noJobText.setVisibility(View.VISIBLE);
                }

                myAdapterDraft.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostJobForm.class);
                intent.putExtra("JOBID","NULL");
                startActivity(intent);
            }
        });
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DraftJobsList.class);
                startActivity(intent);
            }
        });
        return view;
    }

}