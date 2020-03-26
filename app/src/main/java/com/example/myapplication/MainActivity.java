package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public void patient(View view) {
        Button p = findViewById(R.id.b1);


        p.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), patientActivity.class);
                startActivity(intent);

            }

        });

    }

    public void doctor(View view) {
        Button d = findViewById(R.id.b2);

        d.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), doctorActivity.class);
                startActivity(intent);

            }

        });
    }


    public void admin(View view) {
        Button a = findViewById(R.id.b3);

        a.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), adminActivity.class);
                startActivity(intent);

            }

        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       database db=new database(this);
     // patient p=new patient("Vaibhav","vaibhavathani2740@gmail.com","123456","gg");
       // db.addPat(p);

        //feedback f=new feedback("hello");
        //db.addFeed(f);
        //List<String> arr;
        //arr=db.getFeed();
        //Toast.makeText(this, arr.toString(), Toast.LENGTH_SHORT).show();


       //admin ad=new admin("yash","yashtjainhbh@gmail.com");
        //db.addadm(ad);
       // medical_rec med=db.getmed(1);
        //Toast.makeText(this, med.getSym(), Toast.LENGTH_SHORT).show();




        //p.setPatPuid("1");
        //p.setPatName("lul");
       //db.updatePatient(p);


        //patient x=db.getPatient("8");


       // Toast.makeText(this, x.getPatPpno(), Toast.LENGTH_SHORT).show();

        //db.deletePatient("1");
       //Toast.makeText(this, x.getPatName(), Toast.LENGTH_SHORT).show();
      //doctor doc=new doctor("Nithish","nayaknithu@gmail.com","7259459779","R V College,Bangalore","Dermatology","MD","Specialist in Skin related diseases");
       //doctor doc2=new doctor("rohit","rgmail","1234","Delhi","Sports Vision","MD","lul");
      //db.addDoc(doc);
       // db.addDoc(doc2);
       // db.addMed();

        //doctor d=db.getDocByemail("athanivaibhav@gmail.com");
        //Toast.makeText(this, d.getDocName(), Toast.LENGTH_SHORT).show();






      //  db.addAppoint(1,1);
        //appointment app=db.getAppointment(1);
        //Toast.makeText(this,String.valueOf(app.getPpid()), Toast.LENGTH_SHORT).show();
        //int id=db.getPatId("gandu");
        //Toast.makeText(this,String.valueOf(id), Toast.LENGTH_SHORT).show();













        //SQLiteDatabase database=this.openOrCreateDatabase("shacs",MODE_PRIVATE,null);
        //database.execSQL("CREATE TABLE IF NOT EXISTS patient(pid INT PRIMARY KEY AUTOINCREMENT NOT NULL,pname VARCHAR(20) NOT NULL,pmob INT(10) NOT NULL,email VARCHAR(30) NOT NULL,address VARCHAR(50) NOT NULL,dob DATE NOT NULL)" );
    }


    }

