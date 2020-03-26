package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class patAppointments extends AppCompatActivity {
    List<appointment> list;

    SharedPreferences ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pat_appoint_recycle);
        getSupportActionBar().setTitle("My Appoinments");


        ref=getSharedPreferences("com.example.myapplication",MODE_PRIVATE);
        int id=ref.getInt("pid",0);

        database db=new database(this);
        list=db.getPatAppoint(String.valueOf(id));

        RecyclerView lvItems = (RecyclerView) findViewById(R.id.pat_appoint_recycler);


        //doclist.add(doc);


        lvItems.setHasFixedSize(true);
        patAppointAdapter doct=new patAppointAdapter(this,list);
        lvItems.setAdapter(doct);
        lvItems.setLayoutManager(new LinearLayoutManager(this));






    }
}
