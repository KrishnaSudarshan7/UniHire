package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class shortlistCandidatesClosedJobs extends AppCompatActivity {

    EditText NumOfCandidatesEditText;
    CheckBox Name,Email,PhoneNum,Location,ResumeURL;
    String SelectedFormat;
    int numberOfCandidates;
    boolean nameChecked,emailChecked,phoneNumChecked,locationChecked,resumeURLChecked;
    RadioButton csv,xlsx;
    RadioGroup rg;
    Button shorlist;
    FirebaseAuth fAuth;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortlist_candidates_closed_jobs);
        String JOBID = getIntent().getStringExtra("JOBID");

        ref=FirebaseDatabase.getInstance().getReference();
        fAuth=FirebaseAuth.getInstance();

        NumOfCandidatesEditText=findViewById(R.id.NumOfCand);
        Name=findViewById(R.id.checkBoxNameShorlist);
        Email=findViewById(R.id.checkBoxEmailShorlist);
        PhoneNum=findViewById(R.id.checkBoxPhoneShorlist);
        Location=findViewById(R.id.checkBoxLocationShorlist);
        ResumeURL=findViewById(R.id.checkBoxResumeShorlist);
        rg=findViewById(R.id.radioGroupShorlist);
        shorlist=findViewById(R.id.shortlistBtn);
        csv=findViewById(R.id.csvDownload);
        xlsx=findViewById(R.id.xlsxDownload);
        csv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedFormat= (String) csv.getText();
                Toast.makeText(shortlistCandidatesClosedJobs.this, SelectedFormat, Toast.LENGTH_SHORT).show();
            }
        });

        xlsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedFormat=(String) xlsx.getText();
                Toast.makeText(shortlistCandidatesClosedJobs.this, SelectedFormat, Toast.LENGTH_SHORT).show();
            }
        });

        shorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfCandidates=Integer.parseInt(NumOfCandidatesEditText.getText().toString());
                nameChecked=Name.isChecked();
                emailChecked=Email.isChecked();
                phoneNumChecked=PhoneNum.isChecked();
                locationChecked=Location.isChecked();
                resumeURLChecked=ResumeURL.isChecked();
                shorlistCandidates(JOBID);
            }
        });


    }
    public void shorlistCandidates(String JOBID){
        ref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String p1=snapshot.child("Job").child(JOBID).child("Priority1").getValue().toString();
                String p2=snapshot.child("Job").child(JOBID).child("Priority2").getValue().toString();
                String p3=snapshot.child("Job").child(JOBID).child("Priority3").getValue().toString();
                int w1=Integer.parseInt(snapshot.child("Job").child(JOBID).child("Weightage1").getValue().toString());
                int w2=Integer.parseInt(snapshot.child("Job").child(JOBID).child("Weightage2").getValue().toString());
                int w3=Integer.parseInt(snapshot.child("Job").child(JOBID).child("Weightage3").getValue().toString());

                if(p1.equals("Awards/Honors")) p1="Awards Honors";
                if(p2.equals("Awards/Honors")) p2="Awards Honors";
                if(p3.equals("Awards/Honors")) p3="Awards Honors";

                try {
                    calculate(snapshot, "Awards Honors", JOBID);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calculate(DataSnapshot snapshot, String p, String JOBID) throws ParseException {
        //Toast.makeText(this, "Ulla Vanchu"+snapshot.child("Job").child(JOBID).child("jobID").getValue().toString(), Toast.LENGTH_SHORT).show();
        ArrayList<Integer> p1Marks=new ArrayList<>();
        ArrayList<Integer> p2Marks=new ArrayList<>();
        ArrayList<Integer> p3Marks=new ArrayList<>();
        ArrayList<Integer> p4Marks=new ArrayList<>();
        ArrayList<Integer> p5Marks=new ArrayList<>();
        if(p.equals("Education")){
            for(DataSnapshot dataSnapshot : snapshot.child("Application").getChildren()){
                if(dataSnapshot.child("JobID").getValue().toString().equals(JOBID)){
                    ArrayList<Integer> allMarks = new ArrayList<>();
                    int maxYearEdu=Integer.MIN_VALUE;
                    int maxYearIndexEdu=0;
                    int count=0;
                    for(DataSnapshot educationKey : dataSnapshot.child("Education").getChildren()){
                        allMarks.add(Integer.parseInt(educationKey.child("Marks").getValue().toString()));
                        if(Integer.parseInt(educationKey.child("ToYear").getValue().toString()) > maxYearEdu){
                            maxYearEdu=Integer.parseInt(educationKey.child("ToYear").getValue().toString());
                            maxYearIndexEdu=count;
                        }
                        count++;
                    }
                    int score=0;
                    int restOfMarks=0,recentMarks=0;
                    for(int i=0;i<allMarks.size();i++){
                        if(i!=maxYearIndexEdu)
                            restOfMarks+=allMarks.get(i);
                        else
                            recentMarks+=allMarks.get(i);
                    }
                    score=(recentMarks/2) + ((restOfMarks/(allMarks.size()-1)))/2;
                    Toast.makeText(this, "score : "+String.valueOf(score) , Toast.LENGTH_SHORT).show();
                    p1Marks.add(score);
                }
            }
        }

        //-------------------------------------------------------

        else if(p.equals("Work Experience")){
            ArrayList<Long> WorkExDays=new ArrayList<>();
            long maxWorkEx=Integer.MIN_VALUE;
            for(DataSnapshot dataSnapshot : snapshot.child("Application").getChildren()){
                if(dataSnapshot.child("JobID").getValue().toString().equals(JOBID)){
                    int count=0;
                    long totalWorkEx=0;
                    for(DataSnapshot workExKey : dataSnapshot.child("Work Experience").getChildren()){
                        String from = workExKey.child("FromDate").getValue().toString().substring(0,10).replaceAll("/","-");
                        //26-03-2023
                        from=from.substring(6,10)+"-"+from.substring(3,5)+"-"+from.substring(0,2);
                        String to=workExKey.child("ToDate").getValue().toString().substring(0,10).replaceAll("/","-");
                        to=to.substring(6,10)+"-"+to.substring(3,5)+"-"+to.substring(0,2);
                        long daysBetween = ChronoUnit.MONTHS.between(LocalDate.parse(from),LocalDate.parse(to));
                        totalWorkEx+=daysBetween;
                    }
                    //Toast.makeText(this, "totalWorkEx :"+totalWorkEx, Toast.LENGTH_SHORT).show();
                    WorkExDays.add(totalWorkEx);
                    if(totalWorkEx>maxWorkEx){
                        maxWorkEx=totalWorkEx;
                    }
                }
            }
            for(int i=0;i<WorkExDays.size();i++){
                p2Marks.add((int)(((float)WorkExDays.get(i)/(float)maxWorkEx)*100));
                //Toast.makeText(this, String.valueOf(p2Marks.get(i)), Toast.LENGTH_SHORT).show();
            }

        }

        //-----------------------------------------------------------------------------------

        else if(p.equals("Publication")){
            ArrayList <Integer> markList=new ArrayList<>();
            int maxMark=Integer.MIN_VALUE;
            for(DataSnapshot dataSnapshot : snapshot.child("Application").getChildren()){
                if(dataSnapshot.child("JobID").getValue().toString().equals(JOBID)){
                    int paperCount=0;
                    int totalCitations=0;
                    for(DataSnapshot publicationKey : dataSnapshot.child("Publication").getChildren()){
                        int citation=Integer.parseInt(publicationKey.child("Citations").getValue().toString());
                        //Toast.makeText(this, String.valueOf(citation), Toast.LENGTH_SHORT).show();
                        //String si=publicationKey.child("ScopusIndex").getValue().toString();
                        paperCount++;
                        totalCitations+=citation;
                        //if(!si.equals("NA")){
                            paperCount++;
                            totalCitations+=citation;
                        //}
                    }
                    int mark=totalCitations+(paperCount*10);
                    if(mark>maxMark)
                        maxMark=mark;
                    markList.add(mark);
                }
            }
            for(int i=0;i<markList.size();i++){
                float a=(float) markList.get(i) / (float) maxMark;
                p3Marks.add((int)(a*100));
                Toast.makeText(this, String.valueOf(p3Marks.get(i)), Toast.LENGTH_SHORT).show();
            }

        }

        //------------------------------------------------------------------------------------------------------

        else if(p.equals("Awards Honors")){
            int maxCount=Integer.MIN_VALUE;
            ArrayList<Integer> marksAll=new ArrayList<>();
            for(DataSnapshot dataSnapshot : snapshot.child("Application").getChildren()){
                if(dataSnapshot.child("JobID").getValue().toString().equals(JOBID)){
                    int count=0;
                    for(DataSnapshot awardHonorKey : dataSnapshot.child("Awards Honors").getChildren()){
                        count++;
                    }
                    marksAll.add(count);
                    if(count>maxCount)
                        maxCount=count;
                }
            }
            for(int i=0;i<marksAll.size();i++){
                float a=(float) marksAll.get(i)/(float) maxCount;
                p4Marks.add((int)(a*100));
                //Toast.makeText(this, String.valueOf(p4Marks.get(i)), Toast.LENGTH_SHORT).show();
            }

        }

        //-------------------------------------------------------------------------------------------------------

    }

}