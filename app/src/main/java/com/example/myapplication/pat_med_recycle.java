package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;

public class pat_med_recycle extends AppCompatActivity {

    List<medical_rec> list;

    SharedPreferences ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_med_recycle);
        getSupportActionBar().setTitle("My Medical Records");



        ref=getSharedPreferences("com.example.myapplication",MODE_PRIVATE);
        int id=ref.getInt("pid",0);

        database db=new database(this);
        list=db.getPatMed(String.valueOf(id));

        RecyclerView lvItems = (RecyclerView) findViewById(R.id.pat_med_recycler);


        //doclist.add(doc);


        lvItems.setHasFixedSize(true);
        patMedAdapter doct=new patMedAdapter(this,list);
        lvItems.setAdapter(doct);
        lvItems.setLayoutManager(new LinearLayoutManager(this));






    }
}
