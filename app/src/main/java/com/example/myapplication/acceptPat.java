package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kd.dynamic.calendar.generator.ImageGenerator;

import java.util.Calendar;

public class acceptPat extends AppCompatActivity {
    TextView datetext;
    ImageGenerator mimagegenerator;
    ImageView b;
    Bitmap mbitmap;
    EditText time;
    //EditText time;
    Calendar calendar = Calendar.getInstance();
    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
    int currentMinute = calendar.get(Calendar.MINUTE);

    Calendar mCalenderdate;


    public void acceptpat(View view){
        Intent i=getIntent();
        int x=i.getIntExtra("appointid",0);
        //Toast.makeText(this, String.valueOf(x), Toast.LENGTH_SHORT).show();

        database db=new database(this);
      // appointment app=db.getAppointment(x);
        //int p=app.getPpid();
        String sched=datetext.getText().toString()+" "+time.getText().toString();
        db.addDate(x,sched);
        Toast.makeText(this, "Patient Schedule at "+ datetext.getText().toString()+" "+time.getText().toString()+" confirmed Successfully", Toast.LENGTH_LONG).show();

    }
    public void dateselect(View view){

        mimagegenerator=new ImageGenerator(this);
        mimagegenerator.setIconSize(50, 50);
        mimagegenerator.setDateSize(30);
        mimagegenerator.setMonthSize(10);
        mimagegenerator.setDatePosition(42);
        mimagegenerator.setMonthPosition(14);
        mimagegenerator.setDateColor(Color.parseColor("#3c6eaf"));
        mimagegenerator.setMonthColor(Color.WHITE);

        mCalenderdate= Calendar.getInstance();
        int year=mCalenderdate.get(Calendar.YEAR);
        int months=mCalenderdate.get(Calendar.MONTH);
        int day=mCalenderdate.get(Calendar.DATE);

        DatePickerDialog mDatePicker =new DatePickerDialog(acceptPat.this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
            {
                i1++;
                mCalenderdate.set(i,i1,i2);
                datetext.setText(i2+"/"+i1+"/"+i);
                mbitmap=mimagegenerator.generateDateImage(mCalenderdate,R.drawable.empty_calendar);
                b.setImageBitmap(mbitmap);
            }
        }, year, months, day);
        mDatePicker.show();
        //Toast.makeText(this, time.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_pat);
       datetext =findViewById(R.id.dateText);
       b=findViewById(R.id.BtnGetDate);
       //time=findViewById(R.id.timeText);
        time =findViewById(R.id.timeEdit);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(acceptPat.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });
    }
}
