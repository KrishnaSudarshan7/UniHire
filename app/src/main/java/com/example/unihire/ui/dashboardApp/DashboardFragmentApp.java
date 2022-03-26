package com.example.unihire.ui.dashboardApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.unihire.MainActivity;
import com.example.unihire.R;
import com.google.firebase.auth.FirebaseAuth;


public class DashboardFragmentApp extends Fragment {

    Button SignOut;
    TextView text;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_dashboard_app, container, false);

        text=(TextView) view.findViewById(R.id.dashboardAppText1); //This is how you from xml
        SignOut=view.findViewById(R.id.signOutBtnApp);

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