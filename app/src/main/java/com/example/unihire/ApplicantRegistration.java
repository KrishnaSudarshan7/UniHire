package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicantRegistration extends AppCompatActivity {
    EditText name,email,pw,pw2;
    Button regBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    DatabaseReference refApplicant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_registration);

        name=findViewById(R.id.ApplicantName);
        email=findViewById(R.id.ApplicantEmail);
        pw=findViewById(R.id.ApplicantRegPw);
        pw2=findViewById(R.id.ApplicantRegPw2);
        regBtn=findViewById(R.id.ApplicantRegBtn);
        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.ApplicantRegPB);
        progressBar.setVisibility(View.INVISIBLE);
        refApplicant=FirebaseDatabase.getInstance().getReference("Applicant");

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(
                    name.getText().toString()!="" || email.getText().toString()!="" || pw.getText().toString()!="" ||
                    pw2.getText().toString()!=""
                ){
                    if(noNumber(name.getText().toString())){
                        if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                            if(isValidPassword(pw.getText().toString())){
                                if(pw2.getText().toString().equals(pw.getText().toString())){
                                    RegisterApplicant();
                                }else
                                    Toast.makeText(ApplicantRegistration.this, "Confirm Password is not same", Toast.LENGTH_SHORT).show();
                            }else
                                Toast.makeText(ApplicantRegistration.this, "Password must contain 8 letters(One capital letter), a number and a special character", Toast.LENGTH_SHORT).show();

                        }else
                            Toast.makeText(ApplicantRegistration.this, "Enter proper email", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(ApplicantRegistration.this, "Applicant name should not contain numbers", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(ApplicantRegistration.this, "Please fill empty inputs", Toast.LENGTH_SHORT).show();

            }
        });

    }
    public boolean noNumber(String s){
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
    public static boolean isValidPassword(String password)
    {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }
    public void RegisterApplicant(){
        progressBar.setVisibility(View.VISIBLE);
        fAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), pw.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String uid=fAuth.getUid();
                    Applicant applicant=new Applicant(
                            name.getText().toString().trim(),
                            email.getText().toString().trim()
                    );
                    refApplicant.child(uid).setValue(applicant).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task1) {
                            if(task1.isSuccessful()){
                                Toast.makeText(ApplicantRegistration.this, "Registration Sucessfull!", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(ApplicantRegistration.this, ApplicantLogin.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(ApplicantRegistration.this, "Registration Unsuccesfull", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(ApplicantRegistration.this, "!!!!!!!Registration Unsuccesfull", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}