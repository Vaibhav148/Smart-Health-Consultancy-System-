package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class patDash extends AppCompatActivity {
    private FirebaseAuth auth;
    private SharedPreferences pref;
    public void symCheck(View view){

        String packageName = "com.example.codex_pc.mob_dco";
        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
        if(intent != null) {
            startActivity(intent);
        }




    }
    public void feed(View view)
    {
        Intent intent=new Intent(getApplicationContext(),docFeed.class);
        startActivity(intent);
    }

    public void patAppointments(View view){
        Intent i=new Intent(getApplicationContext(),patAppointments.class);
        startActivity(i);







    }
    public void patMedRec(View view){
        Intent i=new Intent(getApplicationContext(),pat_med_recycle.class);
        startActivity(i);




    }




    public void patUpdateProfile(View view)
    {
        Intent intent=new Intent(getApplicationContext(),patUpdateProfile.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.logout)
        {
            auth.signOut();
            finish();
            //starting login activity
            startActivity(new Intent(getApplicationContext(), patientActivity.class));


        }



        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_dash);
        auth=FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("My Dashboard");
pref=getSharedPreferences("com.example.myapplication",MODE_PRIVATE);
        Toast.makeText(this, pref.getString("pname","gg"), Toast.LENGTH_SHORT).show();

        //MenuItem logout=findViewById(R.id.logout);
        /*logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                auth.signOut();
                finish();
                //starting login activity
                startActivity(new Intent(getApplicationContext(), patientActivity.class));
                return true;
            }
        });*/

        LinearLayout search=findViewById(R.id.search_doc);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),search_doc_main.class);
                startActivity(intent);


            }
        });




    }
}
