package com.example.unihire;

import android.os.Debug;
import android.widget.Button;
import android.widget.Toast;

import java.io.Console;

public class Recruiter {
    public String univName,univURL,univUGCID,univNum,Email,about,gMapsLink,DP_Link;
    public Recruiter(){

    }
/*
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    public String getUnivURL() {
        return univURL;
    }

    public void setUnivURL(String univURL) {
        this.univURL = univURL;
    }

    public String getUnivUGCID() {
        return univUGCID;
    }

    public void setUnivUGCID(String univUGCID) {
        this.univUGCID = univUGCID;
    }

    public String getUnivNum() {
        return univNum;
    }

    public void setUnivNum(String univNum) {
        this.univNum = univNum;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getgMapsLink() {
        return gMapsLink;
    }

    public void setgMapsLink(String gMapsLink) {
        this.gMapsLink = gMapsLink;
    }

    public String getDP_Link() {
        return DP_Link;
    }

    public void setDP_Link(String DP_Link) {
        this.DP_Link = DP_Link;
    }*/

    public Recruiter(String univName, String univURL, String univUGCID, String univNum, String Email){

        this.univName=univName;
        this.univURL=univURL;
        this.univUGCID=univUGCID;
        this.univNum=univNum;
        this.Email=Email;
        about="";
        gMapsLink="";
        DP_Link="";
    }
}
