package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Edit_Profile_Rec extends AppCompatActivity {

    TextView name,email,ugcid;
    Button submit;
    EditText PhoneNumber,DP_Link,GMapsLink, Website, Headline, About;
    FirebaseAuth fAuth;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_rec);

        name=findViewById(R.id.aeName);
        email=findViewById(R.id.aeEmail);
        ugcid=findViewById(R.id.aeUGC);
        PhoneNumber=findViewById(R.id.aePhoneNum);
        DP_Link=findViewById(R.id.aeDP);
        GMapsLink=findViewById(R.id.aeGMAPS);
        Website=findViewById(R.id.aeURL);
        About=findViewById(R.id.aeAbout);
        submit=findViewById(R.id.editProfileBtnRec);
        reff= FirebaseDatabase.getInstance().getReference("University");
        fAuth=FirebaseAuth.getInstance();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child(fAuth.getUid()).child("univName").getValue().toString());
                email.setText(snapshot.child(fAuth.getUid()).child("Email").getValue().toString());
                ugcid.setText(snapshot.child(fAuth.getUid()).child("univUGCID").getValue().toString());
                PhoneNumber.setText(snapshot.child(fAuth.getUid()).child("univNum").getValue().toString());
                DP_Link.setText(snapshot.child(fAuth.getUid()).child("DP_Link").getValue().toString());
                GMapsLink.setText(snapshot.child(fAuth.getUid()).child("gMapsLink").getValue().toString());
                Website.setText(snapshot.child(fAuth.getUid()).child("univURL").getValue().toString());
                About.setText(snapshot.child(fAuth.getUid()).child("about").getValue().toString());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String e=email.getText().toString();
                String u=ugcid.getText().toString();
                String p=PhoneNumber.getText().toString();
                String d=DP_Link.getText().toString();
                String g=GMapsLink.getText().toString();
                String w=Website.getText().toString();
                String a=About.getText().toString();

                reff.child(fAuth.getUid()).child("univName").setValue(n);
                reff.child(fAuth.getUid()).child("Email").setValue(e);
                reff.child(fAuth.getUid()).child("univUGCID").setValue(u);
                reff.child(fAuth.getUid()).child("univNum").setValue(p);
                reff.child(fAuth.getUid()).child("DP_Link").setValue(d);
                reff.child(fAuth.getUid()).child("gMapsLink").setValue(g);
                reff.child(fAuth.getUid()).child("univURL").setValue(w);
                reff.child(fAuth.getUid()).child("about").setValue(a);

                Intent intent=new Intent(Edit_Profile_Rec.this,RecruiterHomePage.class);
                startActivity(intent);

            }
        });

    }
}