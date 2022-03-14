package com.example.unihire;

import android.os.Debug;
import android.widget.Button;
import android.widget.Toast;

import java.io.Console;

public class Recruiter {
    public String univName,univURL,univAddressL1,univAddressL2,univCity,univPincode,univUGCID,univNum,univEmail,univPw,univReEnterPw;
    public String SelectedCountry,SelectedState;
    public Recruiter(
            String univName,String univURL, String univAddressL1,String univAddressL2,String univCity,String univPincode,
            String univUGCID,String univNum,String univEmail,String univPw,String univReEnterPw,String SelectedCountry,String SelectedState
    ){

        this.univName=univName;
        this.univURL=univURL;
        this.univAddressL1=univAddressL1;
        this.univAddressL2=univAddressL2;
        this.univCity=univCity;
        this.univPincode=univPincode;
        this.univUGCID=univUGCID;
        this.univNum=univNum;
        this.univEmail=univEmail;
        this.univPw=univPw;
        this.univReEnterPw=univReEnterPw;
        this.SelectedCountry=SelectedCountry;
        this.SelectedState=SelectedState;
    }
}
