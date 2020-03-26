package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class doctorActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth auth;
    EditText email;
    EditText password;
    ProgressDialog progressDialog;
    LinearLayout doc_log_log;
    private String pname;
    private int  pid;
    //LinearLayout log_reg;
   // LinearLayout signUp;
    private SharedPreferences pref;
    private String file="com.example.myapplication";
    private void userLogin(){
        database db=new database(this);

        String em = email.getText().toString().trim();
        String pass  = password.getText().toString().trim();
        doctor p=db.getDocByemail(em);
        Toast.makeText(this, p.getDocName(), Toast.LENGTH_SHORT).show();
       pname=p.getDocName();
        pid=Integer.valueOf(p.getDocDuid());
        SharedPreferences.Editor preferencesEditor = pref.edit();
        preferencesEditor.putInt("did",pid );
        preferencesEditor.putString("dname",pname );

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

        progressDialog.setMessage("Registering Please Wait...");
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
                            startActivity(new Intent(getApplicationContext(), docDash.class));
                        }
                        else{
                            Toast.makeText(doctorActivity.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void onClick(View view) {
        if (view==doc_log_log) {
            userLogin();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        getSupportActionBar().setTitle("Doctor login");
        email=findViewById(R.id.docEmail);
        password=findViewById(R.id.docPass);
        auth=FirebaseAuth.getInstance();
        doc_log_log=findViewById(R.id.doc_go);
        progressDialog = new ProgressDialog(this);

        pref=getSharedPreferences(file,MODE_PRIVATE);







        //log_reg=findViewById(R.id.log_reg);
        doc_log_log.setOnClickListener(this);
        //log_reg.setOnClickListener(this);







    }
}
