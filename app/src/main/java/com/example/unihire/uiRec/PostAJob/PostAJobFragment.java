package com.example.unihire.uiRec.PostAJob;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.unihire.DraftJobsList;
import com.example.unihire.PostJobForm;
import com.example.unihire.R;
import com.example.unihire.RecruiterHomePage;

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