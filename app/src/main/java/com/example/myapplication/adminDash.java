package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class adminDash extends AppCompatActivity {
    public void addDoc(View view)
    {
        Intent intent=new Intent(getApplicationContext(),addDoc.class);
        startActivity(intent);
    }
    public void ViewDoc(View view)
    {
        Intent intent=new Intent(getApplicationContext(),viewDoc.class);
        startActivity(intent);
    }
    public void viewPat(View view)
    {
        Intent intent=new Intent(getApplicationContext(),viewPat.class);
        startActivity(intent);
    }
    public void viewFeed(View view)
    {
        Intent intent=new Intent(getApplicationContext(),viewFeed.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);
        getSupportActionBar().setTitle("Admin Dashboard");
    }
}
