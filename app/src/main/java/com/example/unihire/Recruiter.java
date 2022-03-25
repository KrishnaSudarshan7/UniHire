package com.example.unihire;

import android.os.Debug;
import android.widget.Button;
import android.widget.Toast;

import java.io.Console;

public class Recruiter {
    public String univName,univURL,univUGCID,univNum,Email,about,gMapsLink,DP_Link;
    public Recruiter(){

    }
    public Recruiter(String univName,String univURL,String univUGCID,String univNum,String Email){

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
