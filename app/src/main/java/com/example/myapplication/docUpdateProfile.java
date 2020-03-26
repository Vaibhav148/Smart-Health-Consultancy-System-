package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class docUpdateProfile extends AppCompatActivity {
    EditText dname;
    EditText ph;
    EditText em;
    EditText addr;
    EditText spec;
    EditText qual;
    EditText bio;
    SharedPreferences pref;


    public void docsubmit(View view){
        pref=getSharedPreferences("com.example.myapplication",MODE_PRIVATE);
        int did=pref.getInt("did",0);

        int x=ph.getText().toString().length();
        if(x!=10){
            Toast.makeText(this, "Ph no. should be of length 10", Toast.LENGTH_SHORT).show();

        }else {


            database db = new database(this);
            doctor doc = new doctor(dname.getText().toString(), em.getText().toString(), ph.getText().toString(), addr.getText().toString(), spec.getText().toString(), qual.getText().toString(), bio.getText().toString());
            db.updateDoc(did, doc);
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_update_profile);
        getSupportActionBar().setTitle("Update Profile");

        dname=findViewById(R.id.doc_profile_name1);
        ph=findViewById(R.id.doc_profile_ph);
        em=findViewById(R.id.doc_profile_email);

        addr =findViewById(R.id.doc_profile_addr);

        spec=findViewById(R.id.doc_profile_spec);

        qual=findViewById(R.id.doc_profile_qual);

        bio=findViewById(R.id.doc_profile_bio);
        ph.setInputType(InputType.TYPE_CLASS_NUMBER);
        database db=new database(this);
        pref=getSharedPreferences("com.example.myapplication",MODE_PRIVATE);
        int did=pref.getInt("did",0);


        doctor doc=db.getDoctor(String.valueOf(did));
        dname.setText(doc.getDocName());
        ph.setText(doc.getDocdpno());
        em.setText(doc.getDocEmail());
        addr.setText(doc.getDocAddr());
        spec.setText(doc.getDocSpec());
        qual.setText(doc.getDocQual());
        bio.setText(doc.getBio());






    }
}
