package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RecruiterLogin extends AppCompatActivity {

    EditText email,pw;
    Button loginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView forgotPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_login);

        fAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.AppLoginEmail);
        pw=findViewById(R.id.AppLoginPw);
        loginBtn=findViewById(R.id.AppLoginBtn);
        progressBar=findViewById(R.id.AppLoginPB);
        progressBar.setVisibility(View.INVISIBLE);
        forgotPw=findViewById(R.id.forgotPwLink);
        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecruiterLogin.this, ForgotPassword.class);
                intent.putExtra("USER", "RECRUITER");
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPw.setPaintFlags(forgotPw.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                forgotPw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                progressBar.setVisibility(View.VISIBLE);
                String emailstr=email.getText().toString().trim();
                String password=pw.getText().toString().trim();
                if(!emailstr.isEmpty()){
                    if(!password.isEmpty()){
                        fAuth.signInWithEmailAndPassword(emailstr,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //Toast.makeText(RecruiterLogin.this, "Login sucessful", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Intent intent=new Intent(RecruiterLogin.this,RecruiterHomePage.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(RecruiterLogin.this, "Login Unsucessfull", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            }
                        });
                    }else
                        Toast.makeText(RecruiterLogin.this, "Password is empty!Please enter password", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(RecruiterLogin.this, "Email is empty!Please enter Email", Toast.LENGTH_SHORT).show();
            }
        });
    }
}