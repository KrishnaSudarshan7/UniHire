package com.example.unihire.uiRec.dashboardRec;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.unihire.R;


public class DashboardFragment extends Fragment {


    TextView text;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_dashboard_rec, container, false);

        text=(TextView) view.findViewById(R.id.dashBoardText); //This is how you from xml

        return view;
    }


}