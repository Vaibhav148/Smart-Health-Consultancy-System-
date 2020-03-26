package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class docFeed extends AppCompatActivity {

EditText feed;
    public void submit(View view){
        String x=feed.getText().toString();
        database db=new database(this);
        feedback fed=new feedback(x);
        db.addFeed(fed);

        Toast.makeText(this, "Feedback Submitted Successfully", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_feed);
        feed=findViewById(R.id.feed);
    }
}
