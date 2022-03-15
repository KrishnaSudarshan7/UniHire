package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    EditText email;
    Button resetPwBtn,backBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        String USER = getIntent().getStringExtra("USER");

        email=findViewById(R.id.forgotPwEmail);
        resetPwBtn=findViewById(R.id.resetPwBtn);
        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.resetPwPB);
        progressBar.setVisibility(View.INVISIBLE);
        backBtn=findViewById(R.id.ForgotPwBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(USER.equals("APPLICANT")){
                    Intent intent=new Intent(ForgotPassword.this, ApplicantLogin.class);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(ForgotPassword.this, RecruiterLogin.class);
                    startActivity(intent);
                }
            }
        });

        resetPwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                resetPassword();
            }
        });
    }
    void resetPassword(){
        String emailstr=email.getText().toString().trim();
        fAuth.sendPasswordResetEmail(emailstr).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check Email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else{
                    Toast.makeText(ForgotPassword.this, "Try Again", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}