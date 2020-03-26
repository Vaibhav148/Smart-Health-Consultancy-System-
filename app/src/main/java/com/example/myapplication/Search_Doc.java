package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class Search_Doc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__doc);
        getSupportActionBar().setTitle("Available Doctors");



            //shimmerLayout.setVisibility(View.VISIBLE);
            //shimmerLayout.startShimmerAnimation();
        WaveSwipeRefreshLayout mWaveSwipeRefreshLayout=findViewById(R.id.main_swipe);

        mWaveSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        mWaveSwipeRefreshLayout.setWaveColor(getResources().getColor(R.color.colorPrimary));

        database db = new database(this);
// Get access to the underlying writeable database

// Query for items from the database and get a cursor back
       // String s="Sports Vision";
       // doctor d=db.getDoctor("Sports Vision");
        //Toast

        RecyclerView lvItems = (RecyclerView) findViewById(R.id.doctor_list_view);

        List<doctor> doclist;
        //doclist.add(doc);
        Intent intent= getIntent();
        String specname=intent.getStringExtra("spec");
        doclist=db.getDoc(specname);
        lvItems.setHasFixedSize(true);
        DocAdapter doct=new DocAdapter(this,doclist);
        lvItems.setAdapter(doct);
        lvItems.setLayoutManager(new LinearLayoutManager(this));

// Setup cursor adapter using cursor from last step
        //final DocAdapter todoAdapter = new DocAdapter(this, todoCursor);
// Attach cursor adapter to the ListView
      //  lvItems.setAdapter(todoAdapter);



    }
}
