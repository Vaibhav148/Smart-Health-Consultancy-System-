package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class addDoc extends AppCompatActivity {
    EditText dname;
    EditText ph;
    EditText em;
    EditText addr;
    EditText spec;
    EditText qual;
    EditText bio;
    FirebaseAuth auth;

    public void addDocSubmit(View view) {

        int x = ph.getText().toString().length();
        if (x != 10) {
            Toast.makeText(this, "Ph no. should be of length 10", Toast.LENGTH_SHORT).show();

        } else {
            database db = new database(this);
            doctor doc = new doctor(dname.getText().toString(), em.getText().toString(), ph.getText().toString(), addr.getText().toString(), spec.getText().toString(), qual.getText().toString(), bio.getText().toString());
            db.addDoc(doc);
            auth.createUserWithEmailAndPassword(em.getText().toString(), "123456")
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {
                                Log.i("status", "success");
                                Toast.makeText(getApplicationContext(), "Doctor Added Successfully", Toast.LENGTH_SHORT).show();


                            } else {
                                //display some message here
                                Toast.makeText(getApplicationContext(), "Registration Error", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doc);
        auth=FirebaseAuth.getInstance();
        dname=findViewById(R.id.doc_profile_name1);
        ph=findViewById(R.id.doc_profile_ph);
        em=findViewById(R.id.doc_profile_email);

        addr =findViewById(R.id.doc_profile_addr);

        spec=findViewById(R.id.doc_profile_spec);

        qual=findViewById(R.id.doc_profile_qual);

        bio=findViewById(R.id.doc_profile_bio);
        ph.setInputType(InputType.TYPE_CLASS_NUMBER);
    }
}
