package com.example.unihire;

public class Applicant {
    public String Email,PhoneNum,Name,About,Headline,DP_Link;

    public String getEmail() {
        return Email;
    }
    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getHeadline() {
        return Headline;
    }

    public void setHeadline(String headline) {
        Headline = headline;
    }

    public String getDP_Link() {
        return DP_Link;
    }

    public void setDP_Link(String DP_Link) {
        this.DP_Link = DP_Link;
    }

    public void setEmail(String email) {
        Email = email;
    }

    Applicant(String Name, String Email){
        this.Email=Email;
        this.Name=Name;
        this.PhoneNum="";
        this.About="";
        this.Headline="Hey There! I'm using Unihire";
        this.DP_Link="";
    }
}
