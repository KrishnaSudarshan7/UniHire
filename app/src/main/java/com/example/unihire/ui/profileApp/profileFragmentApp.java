package com.example.unihire.ui.profileApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.unihire.Applicant_Edit_Profile;
import com.example.unihire.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class profileFragmentApp extends Fragment {

    Button edit;
    TextView nameTxtView, workTxtView;
    TextView emailTxtView, phoneTxtView, videoTxtView;
    FirebaseDatabase database;
    private static final String USERS = "Applicant";
    FirebaseAuth fAuth;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_profile_app, container, false);
        nameTxtView=(TextView) view.findViewById(R.id.editTextTextPersonName);
        workTxtView=(TextView) view.findViewById(R.id.editTextTextPersonName2);
        emailTxtView=(TextView) view.findViewById(R.id.editTextTextEmailAddress);
        phoneTxtView=(TextView) view.findViewById(R.id.editTextPhone);
        videoTxtView=(TextView) view.findViewById(R.id.editTextTextMultiLine);
        //This is how you from xml
        fAuth=FirebaseAuth.getInstance();
        // Read from the database
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Applicant");
        rootRef.addValueEventListener(new ValueEventListener() {
            String fname, mail, Headline, About, phone,gmaps,website;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String uId=FirebaseAuth.getInstance().getUid();
                fname = dataSnapshot.child(uId).child("Name").getValue().toString();
                Headline = dataSnapshot.child(uId).child("Headline").getValue().toString();
                mail = dataSnapshot.child(uId).child("Email").getValue().toString();
                About = dataSnapshot.child(uId).child("About").getValue().toString();
                phone = dataSnapshot.child(uId).child("PhoneNum").getValue().toString();


                nameTxtView.setText(fname);
                emailTxtView.setText(mail);
                workTxtView.setText(Headline);
                phoneTxtView.setText(phone);
                videoTxtView.setText(About);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
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