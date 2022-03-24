package com.example.unihire.ui.dashboardApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.unihire.R;


public class DashboardFragmentApp extends Fragment {


    TextView text;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_dashboard_app, container, false);

        text=(TextView) view.findViewById(R.id.dashboardAppText1); //This is how you from xml

        return view;
    }


}