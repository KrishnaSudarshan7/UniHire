package com.example.unihire.uiRec.ProfileRec;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.unihire.R;
import com.example.unihire.Applicant_Edit_Profile;

public class ProfileFragment extends Fragment {


    Button edit;
    TextView text;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_profile_rec, container, false);

        text=(TextView) view.findViewById(R.id.profileText); //This is how you from xml

        return view;
    }


}