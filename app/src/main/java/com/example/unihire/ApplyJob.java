package com.example.unihire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApplyJob extends AppCompatActivity {


    /*EditText Name,edu1_univname, edu1_degree, edu1_spec, edu1_fromdate, edu1_todate,gender,edu2_univname, edu2_degree, edu2_spec, edu2_fromdate, edu2_todate;
    EditText edu3_univname, edu3_degree, edu3_spec, edu3_fromdate, edu3_todate,pub1_title,pub1_type,pub1_date, pub1_jcname, pub1_citations,award1_name,research1_name, research1_amount;
    EditText edu1_marks,edu2_marks,edu3_marks,resume_link;
    RadioGroup radioGroup, radioGroup2;
    RadioButton radioButton, pub1_jc;
    FirebaseAuth fAuth;
    DatabaseReference ref;
    Button applyFinalBtn;*/

    FirebaseAuth fAuth;
    DatabaseReference ref;

    EditText name,email,gender,phoneNum, address, resumeURL;
    EditText edu1_univname,edu1_degree,edu1_spec,edu1_fromyear, edu1_toyear, edu1_marks;
    EditText edu2_univname,edu2_degree,edu2_spec,edu2_fromyear, edu2_toyear, edu2_marks;
    EditText edu3_univname,edu3_degree,edu3_spec,edu3_fromyear, edu3_toyear, edu3_marks;
    EditText empName, JobTitle, jobAbout, jobFromDate, jobToDate;
    EditText pub_title, pub_about, pub_citations, pub_jc, pub_jcname;
    EditText ah_name,ah_about;
    EditText rg_title,rg_amount,rg_agency,rg_about;
    Button finalBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String JOBID = getIntent().getStringExtra("JOBID");
        setContentView(R.layout.activity_apply_job);

        fAuth=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference();

        name=findViewById(R.id.name_app);
        email=findViewById(R.id.email_app);
        gender=findViewById(R.id.gender_app);
        phoneNum=findViewById(R.id.phone_app);
        address=findViewById(R.id.address_app);
        resumeURL=findViewById(R.id.resume_url_app);

        edu1_univname=findViewById(R.id.edu1_univname);
        edu1_degree=findViewById(R.id.edu1_degree);
        edu1_spec=findViewById(R.id.edu1_spec);
        edu1_fromyear=findViewById(R.id.edu1_fromyear);
        edu1_toyear=findViewById(R.id.edu1_toyear);
        edu1_marks=findViewById(R.id.edu1_marks);

        edu2_univname=findViewById(R.id.edu2_univname);
        edu2_degree=findViewById(R.id.edu2_degree);
        edu2_spec=findViewById(R.id.edu2_spec);
        edu2_fromyear=findViewById(R.id.edu2_fromyear);
        edu2_toyear=findViewById(R.id.edu2_toyear);
        edu2_marks=findViewById(R.id.edu2_marks);

        edu3_univname=findViewById(R.id.edu3_univname);
        edu3_degree=findViewById(R.id.edu3_degree);
        edu3_spec=findViewById(R.id.edu3_spec);
        edu3_fromyear=findViewById(R.id.edu3_fromyear);
        edu3_toyear=findViewById(R.id.edu3_toyear);
        edu3_marks=findViewById(R.id.edu3_marks);

        empName=findViewById(R.id.we_empname);
        JobTitle=findViewById(R.id.we_jobtitle);
        jobAbout=findViewById(R.id.we_about);
        jobFromDate=findViewById(R.id.we_fromdate);
        jobToDate=findViewById(R.id.we_todate);

        pub_title= findViewById(R.id.pub_title);
        pub_about=findViewById(R.id.pub_about);
        pub_citations= findViewById(R.id.pub_citations);
        pub_jc= findViewById(R.id.pub_jc);
        pub_jcname=findViewById(R.id.pub_jcname);

        ah_name=findViewById(R.id.ah_name);
        ah_about=findViewById(R.id.ah_about);

        finalBtn=findViewById(R.id.applyBtnFinal);

        rg_title=findViewById(R.id.rg_title);
        rg_amount=findViewById(R.id.rg_amount);
        rg_agency=findViewById(R.id.rg_agency);
        rg_about=findViewById(R.id.rg_about);

        finalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //try{
                String root=fAuth.getUid()+JOBID;
                ref.child("Application").child(root).child("ApplicantID").setValue(fAuth.getUid());


                    ref.child("Application").child(root).child("Education").child("Education1").child("Degree").setValue(edu1_degree.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education1").child("FromYear").setValue(edu1_fromyear.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education1").child("Marks").setValue(edu1_marks.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education1").child("Spec").setValue(edu1_spec.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education1").child("ToYear").setValue(edu1_toyear.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education1").child("UnivName").setValue(edu1_univname.getText().toString());


                ref.child("Application").child(root).child("Awards Honors").child("AwardsHonors1").child("About").setValue(ah_about.getText().toString());
                ref.child("Application").child(root).child("Awards Honors").child("AwardsHonors1").child("AwardName").setValue(ah_name.getText().toString());
                ref.child("Application").child(root).child("Awards Honors").child("AwardsHonors1").child("AwardedDate").setValue("");



                if(!edu2_univname.getText().toString().equals("")){
                    ref.child("Application").child(root).child("Education").child("Education2").child("Degree").setValue(edu2_degree.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education2").child("FromYear").setValue(edu2_fromyear.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education2").child("Marks").setValue(edu2_marks.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education2").child("Spec").setValue(edu2_spec.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education2").child("ToYear").setValue(edu2_toyear.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education2").child("UnivName").setValue(edu2_univname.getText().toString());
                }
                if(!edu3_univname.getText().toString().equals("")){
                    ref.child("Application").child(root).child("Education").child("Education3").child("Degree").setValue(edu3_degree.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education3").child("FromYear").setValue(edu3_fromyear.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education3").child("Marks").setValue(edu3_marks.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education3").child("Spec").setValue(edu3_spec.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education3").child("ToYear").setValue(edu3_toyear.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education3").child("UnivName").setValue(edu3_univname.getText().toString());
                }
                ref.child("Application").child(root).child("End_DateTime").setValue("null");
                ref.child("Application").child(root).child("Gender").setValue(gender.getText().toString());
                ref.child("Application").child(root).child("JobID").setValue(JOBID);

                if(!pub_title.getText().toString().equals("")){
                    ref.child("Application").child(root).child("Publication").child("publication1").child("Citations").setValue(pub_citations.getText().toString());
                    ref.child("Application").child(root).child("Publication").child("publication1").child("DateOfPublication").setValue("null");
                    ref.child("Application").child(root).child("Publication").child("publication1").child("IssueNumber").setValue("69");
                    ref.child("Application").child(root).child("Publication").child("publication1").child("JournalOrConference").setValue(pub_jc.getText().toString());
                    ref.child("Application").child(root).child("Publication").child("publication1").child("JournalOrConferenceName").setValue(pub_jcname.getText().toString());
                    ref.child("Application").child(root).child("Publication").child("publication1").child("PaperTitle").setValue(pub_title.getText().toString());
                    ref.child("Application").child(root).child("Publication").child("publication1").child("ScopusIndex").setValue("ASciuasfBS");
                    ref.child("Application").child(root).child("Publication").child("publication1").child("VolumeNumber").setValue("69");
                }
                if(!rg_title.getText().toString().equals("")){
                    ref.child("Application").child(root).child("Research Grants").child("Grant1").child("About").setValue(rg_about.getText().toString());
                    ref.child("Application").child(root).child("Research Grants").child("Grant1").child("Agency").setValue(rg_agency.getText().toString());
                    ref.child("Application").child(root).child("Research Grants").child("Grant1").child("Amount").setValue(rg_amount.getText().toString());
                    ref.child("Application").child(root).child("Research Grants").child("Grant1").child("FromYear").setValue("null");
                    ref.child("Application").child(root).child("Research Grants").child("Grant1").child("ToYear").setValue("null");
                    ref.child("Application").child(root).child("Research Grants").child("Grant1").child("GrantTitle").setValue(rg_title.getText().toString());
                    ref.child("Application").child(root).child("Research Grants").child("Grant1").child("Status").setValue("null");

                }

                ref.child("Application").child(root).child("ResumeURL").setValue(resumeURL.getText().toString());
                Intent intent=new Intent(ApplyJob.this,ViewJobInfoPage.class);
                intent.putExtra("JOBID",JOBID);
                startActivity(intent);
            //}

           // catch(Exception e){
                //Toast.makeText(ApplyJob.this, e.toString(), Toast.LENGTH_SHORT).show();
            //}
            }
        });

        /*fAuth=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference();
        Name=findViewById(R.id.name);
        radioGroup=findViewById(R.id.radioGroup);
        radioGroup2=findViewById(R.id.radioGroup2);
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
        pub1_citations=findViewById(R.id.pub1_citations);
        award1_name=findViewById(R.id.award1_name);
        research1_name=findViewById(R.id.research1_name);
        research1_amount=findViewById(R.id.research1_amount);
        applyFinalBtn=findViewById(R.id.applyJobBtn);
        edu1_marks=findViewById(R.id.edu1_marks);
        edu2_marks=findViewById(R.id.edu2_marks);
        edu3_marks=findViewById(R.id.edu3_marks);
        resume_link=findViewById(R.id.resume_link);
        applyFinalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioID= radioGroup.getCheckedRadioButtonId();
                int radioID2=radioGroup2.getCheckedRadioButtonId();
                radioButton=findViewById(radioID);
                pub1_jc=findViewById(radioID2);
                gender= (EditText) radioButton.getText().toString();
                pub1_type= (EditText) pub1_jc.getText().toString();

                String root=fAuth.getUid()+JOBID;
                ref.child("Application").child(root).child("ApplicantID").setValue(fAuth.getUid());
                ref.child("Application").child(root).child("Awards Honors").child("AwardsHonors1").child("About").setValue("");
                ref.child("Application").child(root).child("Awards Honors").child("AwardsHonors1").child("AwardName").setValue(award1_name.getText().toString());
                ref.child("Application").child(root).child("Awards Honors").child("AwardsHonors1").child("AwardedDate").setValue("");

                ref.child("Application").child(root).child("Education").child("Education1").child("Degree").setValue(edu1_degree.getText().toString());
                ref.child("Application").child(root).child("Education").child("Education1").child("FromYear").setValue(edu1_fromdate.getText().toString());
                ref.child("Application").child(root).child("Education").child("Education1").child("Marks").setValue(edu1_marks.getText().toString());
                ref.child("Application").child(root).child("Education").child("Education1").child("Spec").setValue(edu1_spec.getText().toString());
                ref.child("Application").child(root).child("Education").child("Education1").child("ToYear").setValue(edu1_todate.getText().toString());
                ref.child("Application").child(root).child("Education").child("Education1").child("UnivName").setValue(edu1_univname);

                if(!edu2_univname.getText().toString().equals(".")){
                    ref.child("Application").child(root).child("Education").child("Education2").child("Degree").setValue(edu2_degree.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education2").child("FromYear").setValue(edu2_fromdate.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education2").child("Marks").setValue(edu2_marks.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education2").child("Spec").setValue(edu2_spec.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education2").child("ToYear").setValue(edu2_todate.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education2").child("UnivName").setValue(edu2_univname);
                }
                if(!edu3_univname.getText().toString().equals(".")){
                    ref.child("Application").child(root).child("Education").child("Education3").child("Degree").setValue(edu3_degree.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education3").child("FromYear").setValue(edu3_fromdate.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education3").child("Marks").setValue(edu3_marks.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education3").child("Spec").setValue(edu3_spec.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education3").child("ToYear").setValue(edu3_todate.getText().toString());
                    ref.child("Application").child(root).child("Education").child("Education3").child("UnivName").setValue(edu3_univname);
                }
                ref.child("Application").child(root).child("End_DateTime").setValue("null");
                ref.child("Application").child(root).child("Gender").setValue(gender.getText().toString());
                ref.child("Application").child(root).child("JobID").setValue(JOBID);

                if(!pub1_title.getText().toString().equals(".")){
                    ref.child("Application").child(root).child("Publication").child("publication1").child("Citations").setValue(pub1_citations.getText().toString());
                    ref.child("Application").child(root).child("Publication").child("publication1").child("DateOfPublication").setValue(pub1_date.getText().toString());
                    ref.child("Application").child(root).child("Publication").child("publication1").child("IssueNumber").setValue("69");
                    ref.child("Application").child(root).child("Publication").child("publication1").child("JournalOrConference").setValue(pub1_jc);
                    ref.child("Application").child(root).child("Publication").child("publication1").child("JournalOrConferenceName").setValue(pub1_jcname);
                    ref.child("Application").child(root).child("Publication").child("publication1").child("PaperTitle").setValue(pub1_title);
                    ref.child("Application").child(root).child("Publication").child("publication1").child("ScopusIndex").setValue("ASciuBS");
                    ref.child("Application").child(root).child("Publication").child("publication1").child("VolumeNumber").setValue("69");
                }
                if(!research1_name.getText().toString().equals(".")){
                    ref.child("Application").child("Research Grants").child("Grant1").child("About").setValue("null");
                    ref.child("Application").child("Research Grants").child("Grant1").child("Agency").setValue("null");
                    ref.child("Application").child("Research Grants").child("Grant1").child("Amount").setValue(research1_amount);
                    ref.child("Application").child("Research Grants").child("Grant1").child("FromYear").setValue("null");
                    ref.child("Application").child("Research Grants").child("Grant1").child("ToYear").setValue("null");
                    ref.child("Application").child("Research Grants").child("Grant1").child("GrantTitle").setValue("null");
                    ref.child("Application").child("Research Grants").child("Grant1").child("Status").setValue("null");

                }

                ref.child("Application").child(root).child("ResumeURL").setValue(resume_link.getText().toString());




            }
        });





*/




    }
}