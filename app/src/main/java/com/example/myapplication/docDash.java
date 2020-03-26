package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class docDash extends AppCompatActivity {

    public void docUpdateProfile(View view)
    {
        Intent intent=new Intent(getApplicationContext(),docUpdateProfile.class);
        startActivity(intent);

    }
    public void feed(View view)
    {
        Intent intent=new Intent(getApplicationContext(),docFeed.class);
        startActivity(intent);
    }
    public void viewAppoint(View view){
        Intent i=new Intent(getApplicationContext(),docViewAppoint.class);
        startActivity(i);




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_dash);
        getSupportActionBar().setTitle("Doctor Dashboard");

    }


}
