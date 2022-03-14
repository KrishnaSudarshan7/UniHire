package com.example.unihire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;

public class RecruiterRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText univName,univURL,univAddressL1,univAddressL2,univCity,univPincode,univUGCID,univNum,univEmail,univPw,univReEnterPw;
    String SelectedCountry,SelectedState;
    Button regBtn;
    ProgressBar progressbar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_registration);

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

                registerUniv();

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

    public void registerUniv(){
        //Validation
        //then

        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(univEmail.getText().toString(),univPw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Recruiter recruiter=new Recruiter(
                            univName.getText().toString(),univURL.getText().toString(),univAddressL1.getText().toString(),
                            univAddressL2.getText().toString(),univCity.getText().toString(),univPincode.getText().toString()
                            ,univUGCID.getText().toString(),univNum.getText().toString(),univEmail.getText().toString(),
                            univPw.getText().toString(),univReEnterPw.getText().toString(),SelectedCountry,SelectedState

                    );
                    FirebaseDatabase.getInstance().getReference("University").child(FirebaseAuth.getInstance().getUid()).setValue(recruiter)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RecruiterRegistration.this,"Registration Successful", Toast.LENGTH_SHORT).show();
                                        progressbar.setVisibility(View.INVISIBLE);
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
                    Toast.makeText(RecruiterRegistration.this,"LOL", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.INVISIBLE);

                }
            }
        });

    }
}