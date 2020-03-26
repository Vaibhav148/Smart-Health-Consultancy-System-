package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class viewFeed extends AppCompatActivity {
    ListView docList;
    ArrayAdapter adap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feed);
        docList=findViewById(R.id.viewFeedList);
        database db=new database(this);
        List<String> list=db.getFeed();
        List<String> l=new ArrayList<>();
        for(int i=0;i<list.size();i++){

            l.add((i+1)+") "+list.get(i));


        }
        adap=new ArrayAdapter(this,android.R.layout.simple_list_item_1,l);
        docList.setAdapter(adap);
    }
}
