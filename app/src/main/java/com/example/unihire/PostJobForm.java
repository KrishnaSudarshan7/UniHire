package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class PostJobForm extends AppCompatActivity {

    TextView jobTitle,dept,spec,jobDesc,weightage1,weightage2,weightage3;
    Spinner p1Spinner,p2Spinner,p3Spinner;
    CheckBox NameInput,GenderInput,PhoneInput,EmailInput,AddressInput,WorkExpInput,EducationInput,PublicationInput,AwardInput,ResearchInput,ResumeInput;
    Button SaveDraftBtn, PostJobFinalBtn;
    FirebaseAuth fAuth;
    DatabaseReference reffJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job_form);
        fAuth=FirebaseAuth.getInstance();
        reffJob= FirebaseDatabase.getInstance().getReference("Job");
        jobTitle=findViewById(R.id.jobTitle);

        dept=findViewById(R.id.dept);

        spec=findViewById(R.id.spec);

        jobDesc=findViewById(R.id.jobDesc);

        weightage1=findViewById(R.id.weightage1);

        weightage2=findViewById(R.id.weightage2);

        weightage3=findViewById(R.id.weightage3);

        p1Spinner=findViewById(R.id.p1Spinner);
        ArrayAdapter<CharSequence> adapterState1 = ArrayAdapter.createFromResource(this,R.array.priority_array, android.R.layout.simple_spinner_item);
        adapterState1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        p1Spinner.setAdapter(adapterState1);

        p2Spinner=findViewById(R.id.p2Spinner);
        ArrayAdapter<CharSequence> adapterState2 = ArrayAdapter.createFromResource(this,R.array.priority_array, android.R.layout.simple_spinner_item);
        adapterState2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        p2Spinner.setAdapter(adapterState2);

        p3Spinner=findViewById(R.id.p3Spinner);
        ArrayAdapter<CharSequence> adapterState3 = ArrayAdapter.createFromResource(this,R.array.priority_array, android.R.layout.simple_spinner_item);
        adapterState3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        p3Spinner.setAdapter(adapterState3);


        /*@SuppressLint("ResourceType") ArrayAdapter adapter1=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,R.array.priority_array);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        p1Spinner.setAdapter(adapter1);*/
        /*p2Spinner=findViewById(R.id.p2Spinner);
        @SuppressLint("ResourceType") ArrayAdapter adapter2=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,R.array.priority_array);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        p2Spinner.setAdapter(adapter2);
        p3Spinner=findViewById(R.id.p3Spinner);
        @SuppressLint("ResourceType") ArrayAdapter adapter3=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,R.array.priority_array);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        p3Spinner.setAdapter(adapter3);*/
        NameInput=findViewById(R.id.NameInput);
        GenderInput=findViewById(R.id.GenderInput);
        PhoneInput=findViewById(R.id.PhoneInput);
        EmailInput=findViewById(R.id.EmailInput);
        AddressInput=findViewById(R.id.AddressInput);
        WorkExpInput=findViewById(R.id.WorkExpInput);
        EducationInput=findViewById(R.id.EducationInput);
        PublicationInput=findViewById(R.id.PublicationInput);
        AwardInput=findViewById(R.id.AwardInput);
        ResearchInput=findViewById(R.id.ResearchInput);
        ResumeInput=findViewById(R.id.ResumeInput);
        SaveDraftBtn=findViewById(R.id.SaveDraftBtn);
        PostJobFinalBtn=findViewById(R.id.PostJobFinalBtn);
        PostJobFinalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=fAuth.getUid();
                String job_title=jobTitle.getText().toString();
                String department=dept.getText().toString();
                String specialization=spec.getText().toString();
                String jd=jobDesc.getText().toString();
                int w1,w2,w3;
                if(weightage1.equals("")) w1=0;
                if(weightage2.equals("")) w2=0;
                if(weightage3.equals("")) w3=0;
                w1=Integer.parseInt(weightage1.getText().toString());
                w2=Integer.parseInt(weightage2.getText().toString());
                w3=Integer.parseInt(weightage3.getText().toString());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String dateTime=formatter.format(date);
                String p1=p1Spinner.getSelectedItem().toString();
                String p2=p2Spinner.getSelectedItem().toString();
                String p3=p3Spinner.getSelectedItem().toString();
                boolean workexpInp=WorkExpInput.isChecked();
                boolean educationInp=EducationInput.isChecked();
                boolean publicationInp=PublicationInput.isChecked();
                boolean awardInp=AwardInput.isChecked();
                boolean researchInp=ResearchInput.isChecked();
                boolean resumeInp=ResearchInput.isChecked();
                Job job=new Job(
                        uid,dateTime,job_title,department,specialization,jd,p1,p2,p3,w1,
                        w2,w3,workexpInp,educationInp,publicationInp,awardInp,researchInp,resumeInp
                        ,false
                );
                String jobId=fAuth.getUid()+String.valueOf(getRandomNumber(0,999999));
                reffJob.child(jobId).setValue(job).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(PostJobForm.this, "Job posted Sucessfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(PostJobForm.this,RecruiterHomePage.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(PostJobForm.this, "Something Wrong happened", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}