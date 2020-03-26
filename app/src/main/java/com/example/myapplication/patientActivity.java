package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class patientActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;
    EditText email;
    EditText password;
    ProgressDialog progressDialog;
    LinearLayout log_log;
    private String pname;
    private int  pid;
    //LinearLayout log_reg;
    TextView signUp;
    private SharedPreferences pref;
    private String file="com.example.myapplication";
    private void userLogin(){
        database db=new database(this);

        String em = email.getText().toString().trim();
        String pass  = password.getText().toString().trim();
        patient p=db.getPatEmail(em);
        Toast.makeText(this, p.getPatName(), Toast.LENGTH_SHORT).show();
       pname=p.getPatName();
        pid=db.getPatId(em);
        SharedPreferences.Editor preferencesEditor = pref.edit();
        preferencesEditor.putInt("pid",pid );
        preferencesEditor.putString("pname",pname );

        preferencesEditor.apply();


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

        progressDialog.setMessage("Signing in Please Wait...");
        progressDialog.show();

        //logging in the patient
        auth.signInWithEmailAndPassword(em, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), patDash.class));
                        }
                        else{
                            Toast.makeText(patientActivity.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void onClick(View view) {
        if (view==log_log) {
            userLogin();
        }else if(view==signUp)
        {
            startActivity(new Intent(getApplicationContext(),signPatient.class));
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        getSupportActionBar().setTitle("Patient login");
        email=findViewById(R.id.patEmailLog);
       password=findViewById(R.id.patPassLog);
        auth=FirebaseAuth.getInstance();
        log_log=findViewById(R.id.log_log);
        progressDialog = new ProgressDialog(this);
        signUp=findViewById(R.id.login_signUp);  pref=getSharedPreferences(file,MODE_PRIVATE);
        email.setText("vaibhavathani2740@gmail.com");
        password.setText("123456");








        //log_reg=findViewById(R.id.log_reg);
     log_log.setOnClickListener(this);
     //log_reg.setOnClickListener(this);
        signUp.setOnClickListener(this);






    }
}
