package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity implements TextToSpeech.OnInitListener {
    RadioGroup rg;
    TextToSpeech tts;
    Button button;
    RadioButton rbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rg = (RadioGroup) findViewById(R.id.rg);
        button = (Button) findViewById(R.id.button);
        tts=new TextToSpeech(this,this);



        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedId=rg.getCheckedRadioButtonId();
                rbtn  =(RadioButton)findViewById(selectedId);
                switch (selectedId)
                {
                    case R.id.pat:
                        Intent i1=new Intent(MainActivity2.this,patientActivity.class);
                        startActivity(i1);
                        tts.speak("this is the Patient login page", TextToSpeech.QUEUE_FLUSH,null,null);
                        break;

                    case R.id.doc:
                        Intent i2=new Intent(MainActivity2.this,doctorActivity.class);
                        startActivity(i2);
                        tts.speak("this is the Doctor login page", TextToSpeech.QUEUE_FLUSH,null,null);
                        break;
                    case R.id.adm:
                        Intent i3=new Intent(MainActivity2.this,adminActivity.class);
                        startActivity(i3);
                        tts.speak("this is the Admin login page", TextToSpeech.QUEUE_FLUSH,null,null);
                        break;

                    default:break;
                }
            }
        });

    }


    @Override
    public void onInit(int status) {
        int res=tts.setLanguage(Locale.ENGLISH);
        if(res== TextToSpeech.LANG_NOT_SUPPORTED||res== TextToSpeech.LANG_NOT_SUPPORTED)
        {
            Toast.makeText(this,"lang not supported", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts!=null)
        {
            tts.stop();
            tts.shutdown();
        }
    }
}




