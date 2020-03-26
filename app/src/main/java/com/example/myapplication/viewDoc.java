package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class viewDoc extends AppCompatActivity {
ListView docList;
    ArrayAdapter adap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doc);
        docList=findViewById(R.id.viewDocList);
        database db=new database(this);
        List<doctor> list=db.getAllDoc();
        List<String> l=new ArrayList<>();
        for(int i=0;i<list.size();i++){

            l.add((i+1)+") "+list.get(i).dname);

        }
         adap=new ArrayAdapter(this,android.R.layout.simple_list_item_1,l);
        docList.setAdapter(adap);

    }
}
