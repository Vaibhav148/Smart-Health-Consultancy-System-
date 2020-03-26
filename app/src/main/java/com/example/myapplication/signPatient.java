package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class signPatient extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    EditText name;
    EditText email;
    LinearLayout reg_reg;
    LinearLayout reg_log;
    FirebaseDatabase fd= FirebaseDatabase.getInstance();
    DatabaseReference df=fd.getReference("Patients");
ProgressDialog progressDialog;
    EditText password;
    private void registerUser(){
        final database db=new database(this);

        //getting email and password from edit texts
        final String em = email.getText().toString().trim();
        String pass  = password.getText().toString().trim();
        final String nam=name.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(em)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new patient
        mAuth.createUserWithEmailAndPassword(em, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            finish();
                            Log.i("status","success");
                            String uid=df.push().getKey();
                            patient pat=new patient(nam,em,uid);
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            df.child(user.getUid()).setValue(pat);


                            patient p=new patient(name.getText().toString(),em,"","");
                            db.addPat(p);

                            startActivity(new Intent(getApplicationContext(), patientActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(getApplicationContext(),"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
public void onClick(View view){
    if(view == reg_reg){
        registerUser();
    }
    else if(view==reg_log){
        startActivity(new Intent(getApplicationContext(),patientActivity.class));
    }

}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_patient);
        getSupportActionBar().setTitle("Patient Register");
        name=findViewById(R.id.editTextName);
        email=findViewById(R.id.editTextEmail);
        password=findViewById(R.id.editTextPassword);



      reg_reg=findViewById(R.id.reg_reg);
        reg_reg.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        reg_log =findViewById(R.id.reg_log);
        reg_log.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);


    }



}
