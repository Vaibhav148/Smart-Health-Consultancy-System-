package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class docViewAppoint extends AppCompatActivity {
    List<appointment> app;
    SharedPreferences ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_view_appoint);

        ref=getSharedPreferences("com.example.myapplication",MODE_PRIVATE);
        int id=ref.getInt("did",0);
        //Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();

        database db=new database(this);
        app=db.getDocAppoint(String.valueOf(id));
        //Toast.makeText(this,app.toString(), Toast.LENGTH_SHORT).show();

        RecyclerView lvItems = (RecyclerView) findViewById(R.id.doc_appoint_recycler);


        //doclist.add(doc);


        lvItems.setHasFixedSize(true);
        docAppointAdapter doct=new docAppointAdapter(this,app);
        lvItems.setAdapter(doct);
        lvItems.setLayoutManager(new LinearLayoutManager(this));






    }
}
