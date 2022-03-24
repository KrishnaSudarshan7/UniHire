package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Applicant_Edit_Profile extends AppCompatActivity {

    TextView name,email;
    EditText PhoneNumber,About,Headline,DP_Link;
    DatabaseReference ref;
    FirebaseAuth fAuth;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_edit_profile);

        fAuth=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference("Applicant");

        name=findViewById(R.id.reName);
        email=findViewById(R.id.reEmail);
        PhoneNumber=findViewById(R.id.rePhoneNum);
        About=findViewById(R.id.reAbout);
        Headline=findViewById(R.id.reHeadline);
        DP_Link=findViewById(R.id.reDP);
        submit=findViewById(R.id.editProfileBtnRec);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String n= (String) snapshot.child(fAuth.getUid()).child("Name").getValue();
                String e= (String) snapshot.child(fAuth.getUid()).child("Email").getValue();
                String p=(String) snapshot.child(fAuth.getUid()).child("PhoneNum").getValue();
                String h=(String) snapshot.child(fAuth.getUid()).child("Headline").getValue();
                String a=(String) snapshot.child(fAuth.getUid()).child("About").getValue();
                String d=(String) snapshot.child(fAuth.getUid()).child("DP_Link").getValue();

                name.setText(n);
                email.setText(e);
                if(!p.equals(""))
                PhoneNumber.setText(p);
                About.setText(a);
                Headline.setText(h);
                DP_Link.setText(d);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name123=name.getText().toString();
                String email123=email.getText().toString();
                String PhoneNumber123=PhoneNumber.getText().toString();
                String About123=About.getText().toString();
                String Headline123=Headline.getText().toString();
                String DPLink=DP_Link.getText().toString();
                ref.child(fAuth.getUid()).child("Name").setValue(name123);
                ref.child(fAuth.getUid()).child("Email").setValue(email123);
                ref.child(fAuth.getUid()).child("PhoneNum").setValue(PhoneNumber123);
                ref.child(fAuth.getUid()).child("Headline").setValue(Headline123);
                ref.child(fAuth.getUid()).child("About").setValue(About123);
                ref.child(fAuth.getUid()).child("DP_Link").setValue(DPLink);
                Intent intent=new Intent(Applicant_Edit_Profile.this,ApplicantHomePage.class);
                startActivity(intent);

            }
        });


    }
}