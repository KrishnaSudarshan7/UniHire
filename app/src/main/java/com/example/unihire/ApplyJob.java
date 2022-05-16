package com.example.unihire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ApplyJob extends AppCompatActivity {

    EditText Name,edu1_univname, edu1_degree, edu1_spec, edu1_fromdate, edu1_todate,gender,edu2_univname, edu2_degree, edu2_spec, edu2_fromdate, edu2_todate;
    EditText edu3_univname, edu3_degree, edu3_spec, edu3_fromdate, edu3_todate,pub1_title,pub1_date, pub1_jcname, pub1_citations;
    RadioGroup radioGroup, radioGroup2;
    RadioButton radioButton, pub1_jc;
    FirebaseAuth mAuth;
    DatabaseReference refUniv;
    DatabaseReference refUnivAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_job);
        Name=findViewById(R.id.name);
        radioGroup=findViewById(R.id.radioGroup);
        int radioID= radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioID);
        gender= (EditText) radioButton.getText();
        edu1_univname=findViewById(R.id.edu1_univname);
        edu1_degree=findViewById(R.id.edu1_degree);
        edu1_spec=findViewById(R.id.edu1_spec);
        edu1_fromdate=findViewById(R.id.edu1_fromdate);
        edu1_todate=findViewById(R.id.edu1_todate);
        edu2_univname=findViewById(R.id.edu2_univname);
        edu2_degree=findViewById(R.id.edu2_degree);
        edu2_spec=findViewById(R.id.edu2_spec);
        edu2_fromdate=findViewById(R.id.edu2_fromdate);
        edu2_todate=findViewById(R.id.edu2_todate);
        edu3_univname=findViewById(R.id.edu3_univname);
        edu3_degree=findViewById(R.id.edu3_degree);
        edu3_spec=findViewById(R.id.edu3_spec);
        edu3_fromdate=findViewById(R.id.edu3_fromdate);
        edu3_todate=findViewById(R.id.edu3_todate);
        pub1_title=findViewById(R.id.pub1_title);
        pub1_date=findViewById(R.id.pub1_date);
        pub1_jcname=findViewById(R.id.pub1_jcname);

    }
}