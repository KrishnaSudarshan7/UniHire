package com.example.unihire.ui.profileApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unihire.Applicant_Edit_Profile;
import com.example.unihire.R;


public class profileFragmentApp extends Fragment {

    Button edit;
    TextView text;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_profile_app, container, false);

        text=(TextView) view.findViewById(R.id.profileText123); //This is how you from xml
        edit=(Button)view.findViewById(R.id.editAppProfBtn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Applicant_Edit_Profile.class);
                startActivity(intent);
            }
        });
        return view;
    }


}