package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class DocProfile extends AppCompatActivity implements OnMapReadyCallback {

    TextView name;
    TextView bio;
    TextView spec;
    SharedPreferences pref;
    private GoogleMap mMap;
    ImageButton call;
   // private boolean isMapReady = false;
    //private boolean isLocationReady = false;

    public void call(View view){
        database db=new database(this);
        Intent i=getIntent();
        String  did=(i.getStringExtra("id"));

        doctor d=db.getDoctor(did);
        String no=d.getDocdpno();
        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9113516628"));
            startActivity(intent);
        }catch (SecurityException e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void book_appointment(View view){
        pref=getSharedPreferences("com.example.myapplication",MODE_PRIVATE);
        int pid=pref.getInt("pid",0);
        Intent i=getIntent();
        int  did=Integer.valueOf(i.getStringExtra("id"));
        Toast.makeText(this, String.valueOf(did), Toast.LENGTH_SHORT).show();
        database db=new database(this);
        db.addAppoint(pid,did);
        String sym=pref.getString("sym","gg");
        Toast.makeText(this, sym, Toast.LENGTH_SHORT).show();
        medical_rec med=new medical_rec(pid,did,"",sym,"");
        db.addMed(med);
        Toast.makeText(this, "Appointment requested Successfully", Toast.LENGTH_SHORT).show();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);
        getSupportActionBar().setTitle("Doctor Profile");

        call=findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database db=new database(getApplicationContext());
                Intent i=getIntent();
                String  did=(i.getStringExtra("id"));

                doctor d=db.getDoctor(did);
                String no=d.getDocdpno();

                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + no));
                    startActivity(intent);
                }catch (SecurityException e)
                {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);


        initProfile();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        initMap();

    }
    private void initProfile() {
        setViews();


    }
    public void setViews(){
        name=findViewById(R.id.tv_name);
        spec=findViewById(R.id.tv_specialty_second);
        bio=findViewById(R.id.tv_bio);
        Intent i=getIntent();
        String id=i.getStringExtra("id");
        database db=new database(this);
        doctor d=db.getDocId(id);
        String a=d.getDocAddr();
        name.setText("Dr."+d.getDocName());
        spec.setText(d.getDocSpec());
        bio.setText(d.getBio());

    }

    private void initMap() {
        Intent i=getIntent();
        String id=i.getStringExtra("id");
        database db=new database(this);
        doctor d=db.getDocId(id);
        String location=d.getDocAddr();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(location));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        }







    }

   public void startMapActivity(View view) {


       Intent i=getIntent();
       String id=i.getStringExtra("id");
       database db=new database(this);
       doctor d=db.getDocId(id);
       String name=d.getDocName();
       String location=d.getDocAddr();

       Intent x=new Intent(getApplicationContext(),mapsActivity2.class);
       x.putExtra("location",location);
       x.putExtra("name",name);
       startActivity(x);

    //   Intent i=getIntent();






/*



     Intent i=new Intent(getApplicationContext(),mapsActivity2.class);
       String id=i.getStringExtra("id");
       database db=new database(this);
       doctor d=db.getDocId(id);
       String a=d.getDocAddr();
     startActivity(i);

       String location = a;
       List<Address> addressList = null;

       if (location != null || !location.equals("")) {
           Geocoder geocoder = new Geocoder(this);
           try {
               addressList = geocoder.getFromLocationName(location, 1);

           } catch (IOException e) {
               e.printStackTrace();
           }
           Address address = addressList.get(0);
           LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
           mMap.addMarker(new MarkerOptions().position(latLng).title(location));
           mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
           Toast.makeText(getApplicationContext(),address.getLatitude()+" "+address.getLongitude(),Toast.LENGTH_LONG).show();
       }

       */


   }


}
