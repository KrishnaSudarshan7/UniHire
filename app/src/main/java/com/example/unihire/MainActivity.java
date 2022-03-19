package com.example.unihire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Button goToLoginRec=findViewById(R.id.goToLoginBtnRec);
        Button goToLoginApp=findViewById(R.id.goToLoginBtnApp);
        TextView goToRegApp=findViewById(R.id.goToRegApp);
        TextView goToRegRec=findViewById(R.id.goToRegRec);
        goToRegApp.setPaintFlags(goToRegApp.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        goToRegRec.setPaintFlags(goToRegRec.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        //Recruiters
        goToRegRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RecruiterRegistration.class);
                startActivity(intent);
            }
        });
        goToLoginRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RecruiterLogin.class);
                startActivity(intent);
            }
        });

        goToRegApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ApplicantRegistration.class);
                startActivity(intent);
            }
        });
        goToLoginApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ApplicantLogin.class);
                startActivity(intent);
            }
        });

    }
}