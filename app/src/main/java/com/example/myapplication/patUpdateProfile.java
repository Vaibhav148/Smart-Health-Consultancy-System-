package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class patUpdateProfile extends AppCompatActivity {
    EditText dname;
    EditText ph;
    EditText em;
    EditText addr;


    SharedPreferences pref;
    public void patSubmit(View view){
        pref=getSharedPreferences("com.example.myapplication",MODE_PRIVATE);
        int did=pref.getInt("pid",0);


int x=ph.getText().toString().length();
if(x!=10){
    Toast.makeText(this, "Ph no. should be of length 10", Toast.LENGTH_SHORT).show();
}else {
    database db = new database(this);
    patient doc = new patient(dname.getText().toString(), em.getText().toString(), ph.getText().toString(), addr.getText().toString());
    db.updatePat(did, doc);
    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
}
    }

    public void back(View view){
        startActivity(new Intent(getApplicationContext(),patDash.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_update_profile);
        getSupportActionBar().setTitle("Update Profile");
        dname=findViewById(R.id.pat_profile_name1);
        ph=findViewById(R.id.pat_profile_ph);
        em=findViewById(R.id.pat_profile_email);

        addr =findViewById(R.id.pat_profile_addr);


        database db=new database(this);
        pref=getSharedPreferences("com.example.myapplication",MODE_PRIVATE);
        int did=pref.getInt("pid",0);


        patient doc=db.getPatient(String.valueOf(did));
        dname.setText(doc.getPatName());
        ph.setText(doc.getPatPpno());
        em.setText(doc.getPatEmail());
        addr.setText(doc.getPatPaddr());
        ph.setInputType(InputType.TYPE_CLASS_NUMBER);


    }
}
