package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;



public class search_doc_main extends AppCompatActivity {
    private SpinnerDialog spinnerDialog;
    private String mSpecialtyUid = "";
    private String mQuery = "";
    MaterialEditText text;
    private String specname = "";


    private ArrayList<String> specialitiesUidList;
    private ArrayList<String> specialitiesNameList;
    Button mSpecialtyButton;
    SharedPreferences ref;

    public void search(View view) {

        //setSearchQuery();
        startSearchResultsActivity();



    }
    private void startSearchResultsActivity() {
        ref=getSharedPreferences("com.example.myapplication",MODE_PRIVATE);

        Intent intent = new Intent(this, Search_Doc.class);
      //  intent.putExtra(Constants.QUERY, mQuery);

        intent.putExtra("spec",specname );
        String s=text.getText().toString();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor=ref.edit();
        editor.putString("sym",s);
        editor.apply();



        startActivity(intent);
    }

    private void setSearchQuery() {
        mQuery =text.getText().toString();
    }


    public void selectSpecialty(View view) {

        if (specialitiesNameList.size() == 0) {
            getSpecialtiesList();
        } else {
            setSpinnerDialog();
            spinnerDialog.showSpinerDialog();
        }

    }
    private void setSpinnerDialog() {

        spinnerDialog = new SpinnerDialog(
                this,
                specialitiesNameList,
                "Select or Search Specialty",
                R.style.DialogAnimations_SmileWindow,
                "Close");

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                mSpecialtyUid = specialitiesUidList.get(i);
                mSpecialtyButton.setText(s);
                specname=s;
            }
        });
    }

    private void getSpecialtiesList() {

        try {
            InputStream is = getAssets().open("specialties.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("element");

            for (int i = 0; i < nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    specialitiesUidList.add(getValue("uid", element2));
                    specialitiesNameList.add(getValue("name", element2));
                }
            }

            setSpinnerDialog();
            spinnerDialog.showSpinerDialog();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doc_main);
        getSupportActionBar().setTitle("Search for Doctor...");


        //ButterKnife.bind(this);
       text =findViewById(R.id.et_search_query);

        mSpecialtyButton =findViewById(R.id.bu_specialty);
        specialitiesUidList = new ArrayList<>();
        specialitiesNameList = new ArrayList<>();





    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
