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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        String JOBID = getIntent().getStringExtra("JOBID");

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
        //Toast.makeText(this, JOBID, Toast.LENGTH_SHORT).show();
        if(!JOBID.equals("NULL")){
            reffJob.child(JOBID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Job thisJob=snapshot.getValue(Job.class);
                    jobTitle.setText(thisJob.JobTitle);
                    dept.setText(thisJob.Department);
                    spec.setText(thisJob.Specialization);
                    jobDesc.setText(thisJob.JobDescription);

                    weightage1.setText(String.valueOf(thisJob.Weightage1));
                    if(thisJob.Weightage1==-1)
                        weightage1.setText("0");
                    weightage2.setText(String.valueOf(thisJob.Weightage2));
                    if(thisJob.Weightage2==-1)
                        weightage2.setText("0");
                    weightage3.setText(String.valueOf(thisJob.Weightage3));
                    if(thisJob.Weightage3==-1)
                        weightage3.setText("0");
                    int spinner1pos=adapterState1.getPosition(thisJob.Priority1);
                    p1Spinner.setSelection(spinner1pos);
                    int spinner2pos=adapterState2.getPosition(thisJob.Priority2);
                    p2Spinner.setSelection(spinner2pos);
                    int spinner3pos=adapterState3.getPosition(thisJob.Priority3);
                    p3Spinner.setSelection(spinner3pos);
                    WorkExpInput.setChecked(thisJob.WorkExpInput);
                    EducationInput.setChecked(thisJob.EducationInput);
                    PublicationInput.setChecked(thisJob.PublicationInput);
                    AwardInput.setChecked(thisJob.AwardInput);
                    ResearchInput.setChecked(thisJob.ResearchInput);
                    ResumeInput.setChecked(thisJob.ResumeInput);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        PostJobFinalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=fAuth.getUid();
                String job_title=" ";
                job_title=jobTitle.getText().toString();
                String department=" ";
                department=dept.getText().toString();
                String specialization=" ";
                specialization=spec.getText().toString();
                String jd=" ";
                jd=jobDesc.getText().toString();
                int w1=0,w2=0,w3=0;
                if(weightage1.length()==0) w1=-1;
                if(weightage1.length()==0) w2=-1;
                if(weightage1.length()==0) w3=-1;
                if(w1==0) w1=Integer.parseInt(weightage1.getText().toString());
                if(w2==0) w2=Integer.parseInt(weightage2.getText().toString());
                if(w3==0) w3=Integer.parseInt(weightage3.getText().toString());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String dateTime=formatter.format(date);
                String p1=" ";
                p1=p1Spinner.getSelectedItem().toString();
                String p2=" ";
                p2=p2Spinner.getSelectedItem().toString();
                String p3=" ";
                p3=p3Spinner.getSelectedItem().toString();
                boolean workexpInp=WorkExpInput.isChecked();
                boolean educationInp=EducationInput.isChecked();
                boolean publicationInp=PublicationInput.isChecked();
                boolean awardInp=AwardInput.isChecked();
                boolean researchInp=ResearchInput.isChecked();
                boolean resumeInp=ResearchInput.isChecked();
                boolean canInsert= validateJobForm(job_title,department, specialization, jd, w1,w2,w3,p1,p2,p3,
                workexpInp, educationInp, publicationInp, awardInp, researchInp);
                String jobId;
                if(JOBID.equals("NULL"))
                    jobId=fAuth.getUid()+String.valueOf(getRandomNumber(0,999999));
                else
                    jobId=JOBID;
                if(canInsert){
                    Job job=new Job(
                            uid,dateTime,job_title,department,specialization,jd,p1,p2,p3,jobId,w1,
                            w2,w3,workexpInp,educationInp,publicationInp,awardInp,researchInp,resumeInp
                            ,false
                    );

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

            }
        });

        SaveDraftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=fAuth.getUid();
                String job_title=" ";
                job_title=jobTitle.getText().toString();
                String department=" ";
                department=dept.getText().toString();
                String specialization=" ";
                specialization=spec.getText().toString();
                String jd=" ";
                jd=jobDesc.getText().toString();
                int w1=0,w2=0,w3=0;
                if(weightage1.length()==0) w1=-1;
                if(weightage1.length()==0) w2=-1;
                if(weightage1.length()==0) w3=-1;
                if(w1==0) w1=Integer.parseInt(weightage1.getText().toString());
                if(w2==0) w2=Integer.parseInt(weightage2.getText().toString());
                if(w3==0) w3=Integer.parseInt(weightage3.getText().toString());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String dateTime=formatter.format(date);
                String p1=" ";
                p1=p1Spinner.getSelectedItem().toString();
                String p2=" ";
                p2=p2Spinner.getSelectedItem().toString();
                String p3=" ";
                p3=p3Spinner.getSelectedItem().toString();
                boolean workexpInp=WorkExpInput.isChecked();
                boolean educationInp=EducationInput.isChecked();
                boolean publicationInp=PublicationInput.isChecked();
                boolean awardInp=AwardInput.isChecked();
                boolean researchInp=ResearchInput.isChecked();
                boolean resumeInp=ResumeInput.isChecked();
                String jobId;
                if(JOBID.equals("NULL"))
                    jobId=fAuth.getUid()+String.valueOf(getRandomNumber(0,999999));
                else
                    jobId=JOBID;
                Job job=new Job(
                        uid,dateTime,job_title,department,specialization,jd,p1,p2,p3,jobId,w1,
                        w2,w3,workexpInp,educationInp,publicationInp,awardInp,researchInp,resumeInp
                        ,true
                );

                reffJob.child(jobId).setValue(job).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(PostJobForm.this, "Job Saved As Draft Successfuly Sucessfully", Toast.LENGTH_SHORT).show();
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

    // boolean canInsert= validateJobForm(job_title,department, specialization, jd, w1,w2,w3,p1,p2,p3);
    //workexpInp, educationInp, publicationInp, awardInp, researchInp,resumeInp);
    public boolean validateJobForm(String job_title,String department, String specialization, String jd, int w1, int w2,
                                   int w3, String p1,String p2, String p3,boolean workexpInp,
                                   boolean educationInp, boolean publicationInp, boolean awardInp, boolean researchInp)
    {
        if(job_title.equals("") || job_title.length()<=2){
            Toast.makeText(this, "Job Title Not Long Enough", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(department.equals("")){
            Toast.makeText(this,"Department Field cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(specialization.equals("")){
            Toast.makeText(this,"Specialization Field cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(jd.length()<=30){
            Toast.makeText(this,"Job Description needs atleast 30 words", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(w1==0 || w2==0 || w3==0){
            Toast.makeText(this,"Weightage Percent cannot be zero", Toast.LENGTH_SHORT).show();
            return false;
        }
        if((w1+w2+w3) != 100){
            Toast.makeText(this,"Weightage Percent Should sum up to 100", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(p1.equals(p2) || p2.equals(p3) || p3.equals(p1)){
            Toast.makeText(this,"Cannot Select Same Fields in Priority", Toast.LENGTH_SHORT).show();
            return false;
        }
        //workexpInp, educationInp, publicationInp, awardInp, researchInp,resumeInp);
        ArrayList<String>notSelected=new ArrayList<String>();
        if(!workexpInp) notSelected.add("Work Experience");
        if(!educationInp) notSelected.add("Education");
        if(!publicationInp) notSelected.add("Publication");
        if(!awardInp) notSelected.add("Awards/Honors");
        if(!researchInp) notSelected.add("Research Grants");

        if(matchPriority(p1,notSelected)) {
            Toast.makeText(this,p1+" not selected in checkbox", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(matchPriority(p2,notSelected)) {
            Toast.makeText(this,p2+" not selected in checkbox", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(matchPriority(p3,notSelected)) {
            Toast.makeText(this,p3+" not selected in checkbox", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }
    public boolean matchPriority(String s, ArrayList<String> notSelected){
        for(int i=0;i<notSelected.size();i++)
            if(notSelected.get(i).equals(s))
                return true;
        return false;

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}