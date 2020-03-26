package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class viewPat extends AppCompatActivity {
    ListView docList;
    ArrayAdapter adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pat);
        docList=findViewById(R.id.viewPatList);
        database db=new database(this);
        List<patient> list=db.getAllPatients();
        List<String> l=new ArrayList<>();
        for(int i=0;i<list.size();i++){

            l.add((i+1)+") "+list.get(i).pname);


        }
        adap=new ArrayAdapter(this,android.R.layout.simple_list_item_1,l);
        docList.setAdapter(adap);

    }
}
