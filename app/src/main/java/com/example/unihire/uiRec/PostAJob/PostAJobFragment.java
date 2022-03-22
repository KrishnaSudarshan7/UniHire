package com.example.unihire.uiRec.PostAJob;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.unihire.PostJobForm;
import com.example.unihire.R;

public class PostAJobFragment extends Fragment {



    Button postBtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_post_a_job, container, false);

        postBtn=(Button) view.findViewById(R.id.postJobBtn);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PostJobForm.class);
                startActivity(intent);
            }
        });

        return view;
    }


}