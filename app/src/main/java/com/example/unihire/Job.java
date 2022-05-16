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

    public String getUnivId() {
        return UnivId;
    }

    public void setUnivId(String univId) {
        UnivId = univId;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String jobDescription) {
        JobDescription = jobDescription;
    }

    public String getPriority1() {
        return Priority1;
    }

    public void setPriority1(String priority1) {
        Priority1 = priority1;
    }

    public String getPriority2() {
        return Priority2;
    }

    public void setPriority2(String priority2) {
        Priority2 = priority2;
    }

    public String getPriority3() {
        return Priority3;
    }

    public void setPriority3(String priority3) {
        Priority3 = priority3;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getPostedDateTime() {
        return PostedDateTime;
    }

    public void setPostedDateTime(String postedDateTime) {
        PostedDateTime = postedDateTime;
    }

    public String getClosedDateTime() {
        return closedDateTime;
    }

    public void setClosedDateTime(String closedDateTime) {
        this.closedDateTime = closedDateTime;
    }

    public int getWeightage1() {
        return Weightage1;
    }

    public void setWeightage1(int weightage1) {
        Weightage1 = weightage1;
    }

    public int getWeightage2() {
        return Weightage2;
    }

    public void setWeightage2(int weightage2) {
        Weightage2 = weightage2;
    }

    public int getWeightage3() {
        return Weightage3;
    }

    public void setWeightage3(int weightage3) {
        Weightage3 = weightage3;
    }

    public boolean isNameInput() {
        return NameInput;
    }

    public void setNameInput(boolean nameInput) {
        NameInput = nameInput;
    }

    public boolean isGenderInput() {
        return GenderInput;
    }

    public void setGenderInput(boolean genderInput) {
        GenderInput = genderInput;
    }

    public boolean isPhoneInput() {
        return PhoneInput;
    }

    public void setPhoneInput(boolean phoneInput) {
        PhoneInput = phoneInput;
    }

    public boolean isEmailInput() {
        return EmailInput;
    }

    public void setEmailInput(boolean emailInput) {
        EmailInput = emailInput;
    }

    public boolean isAddressInput() {
        return AddressInput;
    }

    public void setAddressInput(boolean addressInput) {
        AddressInput = addressInput;
    }

    public boolean isWorkExpInput() {
        return WorkExpInput;
    }

    public void setWorkExpInput(boolean workExpInput) {
        WorkExpInput = workExpInput;
    }

    public boolean isEducationInput() {
        return EducationInput;
    }

    public void setEducationInput(boolean educationInput) {
        EducationInput = educationInput;
    }

    public boolean isPublicationInput() {
        return PublicationInput;
    }

    public void setPublicationInput(boolean publicationInput) {
        PublicationInput = publicationInput;
    }

    public boolean isAwardInput() {
        return AwardInput;
    }

    public void setAwardInput(boolean awardInput) {
        AwardInput = awardInput;
    }

    public boolean isResearchInput() {
        return ResearchInput;
    }

    public void setResearchInput(boolean researchInput) {
        ResearchInput = researchInput;
    }

    public boolean isResumeInput() {
        return ResumeInput;
    }

    public void setResumeInput(boolean resumeInput) {
        ResumeInput = resumeInput;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }

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
