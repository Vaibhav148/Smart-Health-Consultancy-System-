package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class docMedRec extends AppCompatActivity {

    EditText name;
    EditText sym;
    EditText dis;
    EditText presc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_med_rec);
        name=findViewById(R.id.docmednameedit);

        sym=findViewById(R.id.docmedsymedit);

        dis=findViewById(R.id.docmeddiseaseedit);

        presc=findViewById(R.id.docmedprescedit);
        Intent i=getIntent();
        final int medid=i.getIntExtra("medrecid",0);
        database db=new database(this);
        medical_rec med=db.getmed(medid);
        patient p=db.getPatient(String.valueOf(med.getMpid()));
        final String pname=p.getPatName();
        name.setText(pname);
        Toast.makeText(this, med.getSym(), Toast.LENGTH_SHORT).show();
        sym.setText(med.getSym());


        Button b=findViewById(R.id.updatemedrec);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database db=new database(getApplicationContext());
                db.updateMed(medid,dis.getText().toString(),presc.getText().toString());
                Toast.makeText(docMedRec.this, "Medical Record of "+pname+" Updated Successfully ", Toast.LENGTH_SHORT).show();





            }
        });




    }
}
