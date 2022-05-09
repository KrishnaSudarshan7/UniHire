package com.example.unihire.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.unihire.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewUniversityDetails extends AppCompatActivity {

    TextView univName,Location,website, email, phoneNum, about;
    DatabaseReference reff;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_university_details);

        String UNIVID = getIntent().getStringExtra("UNIVID");

        reff= FirebaseDatabase.getInstance().getReference();
        fAuth=FirebaseAuth.getInstance();

        univName=findViewById(R.id.univNameDisplay);
        Location=findViewById(R.id.univLocationDisp);
        website=findViewById(R.id.univWebsite);
        email=findViewById(R.id.univEmailDisp);
        phoneNum=findViewById(R.id.univPhNumDisplay);
        about=findViewById(R.id.unviAboutDisp);

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                univName.setText(snapshot.child("University").child(UNIVID).child("univName").getValue().toString());
                Location.setText(
                        snapshot.child("UniversityAddress").child(UNIVID).child("City").getValue().toString() +", "+
                                snapshot.child("UniversityAddress").child(UNIVID).child("Country").getValue().toString()
                );
                website.setText(snapshot.child("University").child(UNIVID).child("univURL").getValue().toString());
                email.setText(snapshot.child("University").child(UNIVID).child("Email").getValue().toString());
                phoneNum.setText(snapshot.child("University").child(UNIVID).child("univNum").getValue().toString());
                about.setText(snapshot.child("University").child(UNIVID).child("about").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}