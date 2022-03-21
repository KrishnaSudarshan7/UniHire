package com.example.unihire.uiRec.PostAJob;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

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

    Button postBtn;
    Button moreBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_post_a_job, container, false);
        moreBtn = (Button) view.findViewById(R.id.moreBtn);
        postBtn = (Button) view.findViewById(R.id.postJobBtn);
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