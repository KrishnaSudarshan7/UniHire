package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class shortlistCandidatesClosedJobs extends AppCompatActivity {

    EditText NumOfCandidatesEditText;
    CheckBox Name,Email,PhoneNum,Location,ResumeURL;
    String SelectedFormat;
    int numberOfCandidates;
    boolean nameChecked,emailChecked,phoneNumChecked,resumeURLChecked;
    RadioButton csv,xlsx;
    RadioGroup rg;
    Button shorlist;
    FirebaseAuth fAuth;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        setContentView(R.layout.activity_shortlist_candidates_closed_jobs);
        String JOBID = getIntent().getStringExtra("JOBID");

        ref=FirebaseDatabase.getInstance().getReference();
        fAuth=FirebaseAuth.getInstance();

        NumOfCandidatesEditText=(EditText) findViewById(R.id.NumOfCand);
        Name=findViewById(R.id.checkBoxNameShorlist);
        Email=findViewById(R.id.checkBoxEmailShorlist);
        PhoneNum=findViewById(R.id.checkBoxPhoneShorlist);
        ResumeURL=findViewById(R.id.checkBoxResumeShorlist);
        rg=findViewById(R.id.radioGroupShorlist);
        shorlist=findViewById(R.id.shortlistBtn);
        csv=findViewById(R.id.csvDownload);
        xlsx=findViewById(R.id.xlsxDownload);
        csv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedFormat= (String) csv.getText();
                //Toast.makeText(shortlistCandidatesClosedJobs.this, SelectedFormat, Toast.LENGTH_SHORT).show();
            }
        });

        xlsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedFormat=(String) xlsx.getText();
                //Toast.makeText(shortlistCandidatesClosedJobs.this, SelectedFormat, Toast.LENGTH_SHORT).show();
            }
        });

        shorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ActivityCompat.requestPermissions(shortlistCandidatesClosedJobs.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
                numberOfCandidates=Integer.parseInt(NumOfCandidatesEditText.getText().toString());
                nameChecked=Name.isChecked();
                emailChecked=Email.isChecked();
                phoneNumChecked=PhoneNum.isChecked();
                resumeURLChecked=ResumeURL.isChecked();
                shorlistCandidates(JOBID,numberOfCandidates,nameChecked,emailChecked,phoneNumChecked,resumeURLChecked,SelectedFormat);
            }
        });


    }
    public void shorlistCandidates(String JOBID,int numberOfCandidates,boolean nameChecked,boolean emailChecked, boolean phoneNumChecked, boolean resumeURLChecked,String SelectedFormat)  {


        ArrayList<String> sortedShortList=new ArrayList<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
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
                HashMap<Integer,String> hmID=new HashMap<Integer,String>();
                int hmCount=1;
                for(DataSnapshot dataSnapshot : snapshot.child("Application").getChildren()){
                    hmID.put(hmCount,dataSnapshot.child("ApplicantID").getValue().toString());
                    //Toast.makeText(shortlistCandidatesClosedJobs.this, hmID.get(hmCount), Toast.LENGTH_SHORT).show();
                    hmCount++;
                }


                try {
                    ArrayList<Integer> p1Marks = calculate(snapshot, p1, JOBID);
                    ArrayList<Integer> p2Marks= calculate(snapshot, p2, JOBID);
                    ArrayList<Integer> p3Marks= calculate(snapshot, p3, JOBID);
                    ArrayList<Float> finalMarksOutta100 = new ArrayList<>();
                    int n=p1Marks.size();
                    for(int i=0;i<n;i++){//Iterate through each persons mark for a field
                        int p1m=p1Marks.get(i);
                        int p2m=p2Marks.get(i);
                        int p3m=p3Marks.get(i);
                        float finalmark = (float) ((float)(p1m*w1)/100 + (float)(p2m*w2)/100 + (float)(p3m*w3)/100 );
                        finalMarksOutta100.add(finalmark);
                        //Toast.makeText(shortlistCandidatesClosedJobs.this, String.valueOf(finalmark), Toast.LENGTH_SHORT).show();
                    }

                    float arr[][]=new float[n][2];
                    for(int i=0;i<n;i++){
                        arr[i][0]=finalMarksOutta100.get(i);
                        arr[i][1]=i+1;
                    }
                    Arrays.sort(arr,(a,b)->(int)(-a[0]+b[0]));

                    for(int i=0;i<n;i++){
                        String appid=hmID.get((int)arr[i][1]);
                        sortedShortList.add(appid);
                    }

                    for(int i=0;i<numberOfCandidates;i++)
                        Toast.makeText(shortlistCandidatesClosedJobs.this, sortedShortList.get(i), Toast.LENGTH_SHORT).show();

                    //----------------------------------------------------------
                    //String JOBID,
                    // int numberOfCandidates,boolean nameChecked,boolean emailChecked,
                    // boolean phoneNumChecked, boolean locationChecked,boolean resumeURLChecked,
                    // String SelectedFormat
                     int columns=0;
                     ArrayList<String>columnsName=new ArrayList<>();
                    if(nameChecked){
                        columns++;
                        columnsName.add("Name");
                    }
                     if(emailChecked){
                        columns++;
                        columnsName.add("Email");
                     }
                     if(phoneNumChecked){
                         columns++;
                         columnsName.add("PhoneNum");
                     }
                     if(resumeURLChecked){
                        columns++;
                        columnsName.add("ResumeURL");
                     }
                     String table[][]=new String[numberOfCandidates][columns];

                    int curcol=0;

                    if(nameChecked){
                        for(int i=0;i<numberOfCandidates;i++){
                            String applicantID=sortedShortList.get(i);
                            table[i][curcol]=snapshot.child("Applicant").child(applicantID).child("Name").getValue().toString();
                        }
                        curcol++;
                    }
                    if(emailChecked){
                        for(int i=0;i<numberOfCandidates;i++){
                            String applicantID=sortedShortList.get(i);
                            table[i][curcol]=snapshot.child("Applicant").child(applicantID).child("Email").getValue().toString();
                        }
                        curcol++;
                    }
                    if(phoneNumChecked){
                        for(int i=0;i<numberOfCandidates;i++){
                            String applicantID=sortedShortList.get(i);
                            table[i][curcol]=snapshot.child("Applicant").child(applicantID).child("PhoneNum").getValue().toString();
                        }
                        curcol++;
                    }
                    if(resumeURLChecked){
                        for(int i=0;i<numberOfCandidates;i++){
                            String applicantID=sortedShortList.get(i);
                            //You have JOBID, Applicant ID find ResumeURL
                            String applicationID=applicantID+JOBID;
                            table[i][curcol]=snapshot.child("Application").child(applicationID).child("ResumeURL").getValue().toString();
                        }
                        curcol++;
                    }


                    //---------------------------------------------------------


                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    String date123=date.toString().substring(0,10).replaceAll("/","-");
                    File filepath=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/Shortlisted Candidates "+date123+SelectedFormat);
                    HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
                    HSSFSheet hssfSheet=hssfWorkbook.createSheet("CustomSheet");
                    HSSFRow hssfRow=hssfSheet.createRow(0);
                    int tempcol=0;
                    if(nameChecked){
                        HSSFCell cell=hssfRow.createCell(tempcol);
                        cell.setCellValue("Name");
                        tempcol++;
                    }
                    if(emailChecked){
                        HSSFCell cell=hssfRow.createCell(tempcol);
                        cell.setCellValue("Email");
                        tempcol++;
                    }
                    if(phoneNumChecked){
                        HSSFCell cell=hssfRow.createCell(tempcol);
                        cell.setCellValue("Phone Number");
                        tempcol++;
                    }
                    if(resumeURLChecked){
                        HSSFCell cell=hssfRow.createCell(tempcol);
                        cell.setCellValue("Resume Link");
                        tempcol++;
                    }

                    for(int i=0;i<numberOfCandidates;i++){
                        HSSFRow row=hssfSheet.createRow(i+1);
                        for(int j=0;j<columns;j++){
                            HSSFCell cell=row.createCell(j);
                            cell.setCellValue(table[i][j]);
                        }
                    }
                    try {
                        if(!filepath.exists()){
                            filepath.createNewFile();
                        }
                        Toast.makeText(shortlistCandidatesClosedJobs.this, "Downloaded "+SelectedFormat+" File, Check your downloads", Toast.LENGTH_SHORT).show();

                        FileOutputStream fileOutputStream=new FileOutputStream(filepath);
                        hssfWorkbook.write(fileOutputStream);
                        if(fileOutputStream!=null){
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }

                    }
                    catch (IOException e) {
                        Toast.makeText(shortlistCandidatesClosedJobs.this, e.toString(), Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }



                } catch (ParseException e) {
                    Toast.makeText(shortlistCandidatesClosedJobs.this, "Catch2", Toast.LENGTH_SHORT).show();

                    e.printStackTrace();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Integer> calculate(DataSnapshot snapshot, String p, String JOBID) throws ParseException {
        //Toast.makeText(this, "Ulla Vanchu"+snapshot.child("Job").child(JOBID).child("jobID").getValue().toString(), Toast.LENGTH_SHORT).show();
        ArrayList<Integer> pMarks=new ArrayList<>();
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
                    //Toast.makeText(this, "score : "+String.valueOf(score) , Toast.LENGTH_SHORT).show();
                    pMarks.add(score);
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
                pMarks.add((int)(((float)WorkExDays.get(i)/(float)maxWorkEx)*100));
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
                pMarks.add((int)(a*100));
                //Toast.makeText(this, String.valueOf(pMarks.get(i)), Toast.LENGTH_SHORT).show();
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
                pMarks.add((int)(a*100));
                //Toast.makeText(this, String.valueOf(pMarks.get(i)), Toast.LENGTH_SHORT).show();
            }

        }

        //-------------------------------------------------------------------------------------------------------

        else if(p.equals("Research Grants")){
            ArrayList<Integer> rgMarks=new ArrayList<>();
            int maxMoney=Integer.MIN_VALUE;
            for(DataSnapshot dataSnapshot : snapshot.child("Application").getChildren()) {
                if (dataSnapshot.child("JobID").getValue().toString().equals(JOBID)) {
                    int totalMoney=0;
                    for(DataSnapshot rgKey : dataSnapshot.child("Research Grants").getChildren()){
                        int ThisRGMoney=Integer.parseInt(rgKey.child("Amount").getValue().toString());
                        totalMoney+=ThisRGMoney;
                    }
                    rgMarks.add(totalMoney);
                    if(totalMoney>maxMoney)
                        maxMoney=totalMoney;
                    //Toast.makeText(this, String.valueOf(totalMoney), Toast.LENGTH_SHORT).show();
                }
            }
            for(int i=0;i<rgMarks.size();i++){
                float a= (float) rgMarks.get(i) / (float) maxMoney;
                pMarks.add((int)(a*100));
                //Toast.makeText(this, String.valueOf(pMarks.get(i)), Toast.LENGTH_SHORT).show();
            }
        }

        //----------------------------------------------------------------------------------------------------

        return pMarks;
    }

}