package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecruiterRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText univName,univURL,univAddressL1,univAddressL2,univCity,univPincode,univUGCID,univNum,univEmail,univPw,univReEnterPw;
    String SelectedCountry,SelectedState;
    Button regBtn;
    ProgressBar progressbar;
    FirebaseAuth mAuth;
    DatabaseReference refUniv;
    DatabaseReference refUnivAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_registration);
        refUniv=FirebaseDatabase.getInstance().getReference("University");
        refUnivAddress=FirebaseDatabase.getInstance().getReference("UniversityAddress");
        mAuth=FirebaseAuth.getInstance();
        Spinner CountryRecReg = findViewById(R.id.CountryRecReg);
        Spinner StateRecReg = findViewById(R.id.StateRecReg);
        ArrayAdapter<CharSequence> adapterCountry = ArrayAdapter.createFromResource(this,R.array.countries_array, android.R.layout.simple_spinner_item);
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_item);
        CountryRecReg.setAdapter(adapterCountry);
        CountryRecReg.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterState = ArrayAdapter.createFromResource(this,R.array.state_array, android.R.layout.simple_spinner_item);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_item);
        StateRecReg.setAdapter(adapterState);
        StateRecReg.setOnItemSelectedListener(this);
        univName=findViewById(R.id.univName);
        univURL=findViewById(R.id.univURL);
        univAddressL1=findViewById(R.id.univAddressL1);
        univAddressL2=findViewById(R.id.univAddressL2);
        univCity=findViewById(R.id.univCity);
        univPincode=findViewById(R.id.univPincode);
        univUGCID=findViewById(R.id.univUGCID);
        univNum = findViewById(R.id.univNum);
        univEmail=findViewById(R.id.univEmail);
        univPw=findViewById(R.id.univPw);
        univReEnterPw=findViewById(R.id.univReEnterPw);
        SelectedCountry="";
        SelectedState="";
        regBtn=(Button) findViewById(R.id.univRegisterBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(
                !univName.getText().toString().isEmpty() || !univURL.getText().toString().isEmpty() || !univAddressL1.getText().toString().isEmpty() ||
                !univCity.getText().toString().isEmpty() || !univPincode.getText().toString().isEmpty() || !univUGCID.getText().toString().isEmpty() ||
                !univNum.getText().toString().isEmpty() || !univEmail.getText().toString().isEmpty() || !univPw.getText().toString().isEmpty() ||
                !univReEnterPw.getText().toString().isEmpty()
                ){
                    if(noNumber(univName.getText().toString())){
                        if(noNumber(univCity.getText().toString())){
                            if(isInteger(univPincode.getText().toString())){
                                if(isInteger(univUGCID.getText().toString())){
                                    if(isInteger(univNum.getText().toString())){
                                        if(Patterns.EMAIL_ADDRESS.matcher(univEmail.getText().toString()).matches()){
                                            if(isValidPassword(univPw.getText().toString())){
                                                if(univReEnterPw.getText().toString().equals(univPw.getText().toString())){
                                                    registerUniv();
                                                }else
                                                    Toast.makeText(RecruiterRegistration.this, "Confirm Password is not same", Toast.LENGTH_SHORT).show();
                                            }else
                                                Toast.makeText(RecruiterRegistration.this, "Password must contain 8 letters(One capital letter), a number and a special character", Toast.LENGTH_SHORT).show();

                                            }else
                                                Toast.makeText(RecruiterRegistration.this, "Enter proper email", Toast.LENGTH_SHORT).show();
                                    }else
                                        Toast.makeText(RecruiterRegistration.this, "University number should not contain characters", Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(RecruiterRegistration.this, "UGCID should not contain characters", Toast.LENGTH_SHORT).show();
                            }else
                                Toast.makeText(RecruiterRegistration.this, "Pincode should not contain characters", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(RecruiterRegistration.this, "City should not contain numbers", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(RecruiterRegistration.this, "University name should not contain numbers", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(RecruiterRegistration.this, "Fill empty fields", Toast.LENGTH_SHORT).show();

            }
        });

        progressbar=(ProgressBar) findViewById(R.id.progressbar);
        progressbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(parent.getId()== R.id.CountryRecReg)
                SelectedCountry=parent.getItemAtPosition(position).toString();
            else
                SelectedState=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
    public boolean isInteger(String s){
        int flag=0;
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(Character.isDigit(c)){
                flag++;
            }
        }
        if(flag==s.length())
            return true;
        else
            return false;
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
    public void registerUniv(){
        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(univEmail.getText().toString(),univPw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Recruiter recruiter=new Recruiter(
                            univName.getText().toString(),univURL.getText().toString(),
                            univUGCID.getText().toString(),univNum.getText().toString(),univEmail.getText().toString());
                    AddressClass univAddress=new AddressClass(univAddressL1.getText().toString().trim(),
                            univAddressL2.getText().toString().trim(), SelectedCountry, SelectedState,
                            univCity.getText().toString().trim(),Integer.parseInt(univPincode.getText().toString().trim())
                            );
                    String uid=FirebaseAuth.getInstance().getUid();
                    refUniv.child(uid).setValue(recruiter)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RecruiterRegistration.this,"Registration Successful", Toast.LENGTH_SHORT).show();
                                        progressbar.setVisibility(View.INVISIBLE);
                                        refUnivAddress.child(uid).setValue(univAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task1) {
                                                if(task1.isSuccessful()){
                                                    Intent intent=new Intent(RecruiterRegistration.this,RecruiterLogin.class);
                                                    startActivity(intent);
                                                }
                                                else{
                                                    Toast.makeText(RecruiterRegistration.this,"Registration UnSuccessful", Toast.LENGTH_SHORT).show();
                                                    progressbar.setVisibility(View.INVISIBLE);
                                                }
                                            }
                                        });

                                    }
                                    else{
                                        Toast.makeText(RecruiterRegistration.this,"Registration UnSuccessful", Toast.LENGTH_SHORT).show();
                                        progressbar.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });

                }
                else{
                    Toast.makeText(RecruiterRegistration.this,"LOL", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.INVISIBLE);

                }
            }
        });

    }
}