package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewApplicantDetails extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseAuth fAuth;
    TextView name,email,phoneNum,headline,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicant_details);

        String APPEMAIL = getIntent().getStringExtra("APPEMAIL");

        ref=FirebaseDatabase.getInstance().getReference();
        fAuth=FirebaseAuth.getInstance();

        name=findViewById(R.id.appNameDisp);
        email=findViewById(R.id.appEmailDispl);
        phoneNum = findViewById(R.id.appPhNumDisp);
        headline = findViewById(R.id.appHeadlineDisp);
        about=findViewById(R.id.appAboutDisp);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.child("Applicant").getChildren()){
                    if(dataSnapshot.child("Email").getValue().toString().equals(APPEMAIL)){
                        name.setText(dataSnapshot.child("Name").getValue().toString());
                        email.setText(dataSnapshot.child("Email").getValue().toString());
                        headline.setText(dataSnapshot.child("Headline").getValue().toString());
                        about.setText(dataSnapshot.child("About").getValue().toString());
                        break;


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}