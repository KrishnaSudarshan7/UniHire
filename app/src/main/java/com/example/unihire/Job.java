package com.example.unihire;

public class Job {

    public Job() {
        UnivId = " ";
        JobTitle = " ";
        Department = " ";
        Specialization = " ";
        JobDescription = " ";
        Priority1 = " ";
        Priority2 = " ";
        Priority3 = " ";
        PostedDateTime = " ";
        Weightage1 = 0;
        Weightage2 = 0;
        Weightage3 = 0;
        NameInput = true;
        GenderInput = true;
        PhoneInput = true;
        EmailInput = true;
        AddressInput = true;
        WorkExpInput = false;
        EducationInput = false;
        PublicationInput = false;
        AwardInput = false;
        ResearchInput = false;
        ResumeInput = false;
        isDraft = true;
    }

    public String UnivId,JobTitle,Department,Specialization,JobDescription,Priority1,Priority2,Priority3,jobID;
    public String jobStatus;
    public String PostedDateTime,closedDateTime;
    public int Weightage1,Weightage2,Weightage3;
    public boolean NameInput,GenderInput,PhoneInput,EmailInput,AddressInput,WorkExpInput,EducationInput,PublicationInput,AwardInput,ResearchInput,ResumeInput;
    public boolean isDraft;
    public Job(
            String UnivId,String PostedDateTime,String closedDateTime,String JobStatus,
            String JobTitle, String Department, String Specialization, String JobDescription,
            String Priority1,String Priority2, String Priority3,String jobID, int Weightage1,int Weightage2,int Weightage3,
            boolean WorkExpInput, boolean EducationInput, boolean PublicationInput,boolean AwardInput,
            boolean ResearchInput,boolean ResumeInput, boolean isDraft

    ){
        this.UnivId=UnivId;
        this.PostedDateTime=PostedDateTime;
        this.JobTitle=JobTitle;
        this.Department=Department;
        this.Specialization=Specialization;
        this.JobDescription=JobDescription;
        this.Priority1=Priority1;
        this.Priority2=Priority2;
        this.Priority3=Priority3;
        this.Weightage1=Weightage1;
        this.Weightage2=Weightage2;
        this.Weightage3=Weightage3;
        this.NameInput=true;
        this.GenderInput=true;
        this.PhoneInput=true;
        this.EmailInput=true;
        this.AddressInput=true;
        this.WorkExpInput=WorkExpInput;
        this.EducationInput=EducationInput;
        this.PublicationInput=PublicationInput;
        this.AwardInput=AwardInput;
        this.ResearchInput=ResearchInput;
        this.ResumeInput=ResumeInput;
        this.isDraft=isDraft;
        this.jobID=jobID;
        this.jobStatus=JobStatus;
        this.closedDateTime=closedDateTime;
    }
}
