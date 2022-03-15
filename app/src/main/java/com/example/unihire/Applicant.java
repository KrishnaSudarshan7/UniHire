package com.example.unihire;

public class Applicant {
    public String Email,PhoneNum,Name,About,Headline,DP_Link;
    Applicant(String Name, String Email){
        this.Email=Email;
        this.Name=Name;
        this.PhoneNum="";
        this.About="";
        this.Headline="Hey There! I'm using Unihire";
        this.DP_Link="";
    }
}
