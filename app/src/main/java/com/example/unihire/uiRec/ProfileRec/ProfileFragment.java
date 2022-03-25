package com.example.unihire.uiRec.ProfileRec;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.unihire.Edit_Profile_Rec;
import com.example.unihire.R;
import com.example.unihire.Applicant_Edit_Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    TextView nameTxtView, aboutTxtView,name1TxtView,name4TxtView,name3TxtView,name2TxtView;
    TextView mailTxtView, phoneTxtView, univUGCIDTxtView ,collegeURL,gmapsURL,AboutText;
    Button edit;
    FirebaseDatabase database;
    private static final String USERS = "University";
    private static final String ahc = "UniversityAddress";
    FirebaseAuth fAuth;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_profile_rec, container, false);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("University");
        DatabaseReference broRef = FirebaseDatabase.getInstance().getReference("UniversityAddress");

        nameTxtView = (TextView) view.findViewById(R.id.editTextTextPersonName6);
        aboutTxtView = (TextView) view.findViewById(R.id.editTextTextPersonName15);
        mailTxtView = (TextView) view.findViewById(R.id.editTextTextEmailAddress2);
        phoneTxtView = (TextView) view.findViewById(R.id.editTextPhone);
        univUGCIDTxtView = (TextView) view.findViewById(R.id.editTextTextMultiLine);
        name1TxtView = (TextView) view.findViewById(R.id.editTextTextPersonName11);
        name2TxtView = (TextView) view.findViewById(R.id.editTextTextPersonName12);
        name3TxtView = (TextView) view.findViewById(R.id.editTextTextPersonName13);
        name4TxtView = (TextView) view.findViewById(R.id.editTextTextPersonName5);
        gmapsURL=(TextView) view.findViewById(R.id.GmapsText);
        AboutText=(TextView) view.findViewById(R.id.editTextTextPersonName15);
        collegeURL=(TextView) view.findViewById(R.id.collegeURLText);
        edit = (Button) view.findViewById(R.id.editRecProfBtn);

        fAuth=FirebaseAuth.getInstance();
        // Read from the database
        rootRef.addValueEventListener(new ValueEventListener() {
            String name, mail, about, phone,univUGCID,gmaps,website,about1;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String uId=FirebaseAuth.getInstance().getUid();
                name = dataSnapshot.child(uId).child("univName").getValue().toString();
                mail = dataSnapshot.child(uId).child("Email").getValue().toString();
                about= dataSnapshot.child(uId).child("about").getValue().toString();
                phone = dataSnapshot.child(uId).child("univNum").getValue().toString();
                univUGCID= dataSnapshot.child(uId).child("univUGCID").getValue().toString();
                gmaps = dataSnapshot.child(uId).child("gMapsLink").getValue().toString();
                website = dataSnapshot.child(uId).child("univURL").getValue().toString();
                about1=dataSnapshot.child(uId).child("about").getValue().toString();

                nameTxtView.setText(name);
                mailTxtView.setText(mail);
                phoneTxtView.setText(phone);
                aboutTxtView.setText(about);
                univUGCIDTxtView.setText(univUGCID);
                collegeURL.setText(website);
                gmapsURL.setText(gmaps);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        broRef.addValueEventListener(new ValueEventListener() {
            String name1,name2,name3,name4;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                String aId=FirebaseAuth.getInstance().getUid();
                name1 = dataSnapshot1.child(aId).child("AddressL1").getValue().toString();
                name2= dataSnapshot1.child(aId).child("City").getValue().toString();
                name3 = dataSnapshot1.child(aId).child("State").getValue().toString();
                name4 = dataSnapshot1.child(aId).child("Country").getValue().toString();


                name1TxtView.setText(name1);
                name2TxtView.setText(name2);
                name3TxtView.setText(name3);
                name4TxtView.setText(name4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        edit=(Button) view.findViewById(R.id.editRecProfBtn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Edit_Profile_Rec.class);
                startActivity(intent);
            }
        });
        return view;
    }


}