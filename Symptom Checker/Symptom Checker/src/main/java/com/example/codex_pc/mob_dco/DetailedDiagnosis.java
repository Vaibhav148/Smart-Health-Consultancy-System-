package com.example.codex_pc.mob_dco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DetailedDiagnosis extends AppCompatActivity {
    IssueInfo issueInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_diagnosis);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        issueInfo = getIntent().getBundleExtra("des").getParcelable("Info");
        getSupportActionBar().setTitle(issueInfo.getName());


        TextView name = findViewById(R.id.dName);
        TextView profname = findViewById(R.id.profName);
        TextView short_des = findViewById(R.id.short_des);
        TextView descr = findViewById(R.id.description);
        TextView our_sym = findViewById(R.id.oucr_sym);
        TextView treatment =findViewById(R.id.treatment);
        TextView possible_syms = findViewById(R.id.possible_syms);

        name.setText(issueInfo.getName());
        profname.setText("Professional Name ("+issueInfo.getProfName()+")");
        short_des.setText(issueInfo.getDescriptionShort());
        descr.setText(issueInfo.getDescription());
        our_sym.setText(issueInfo.getMedicalCondition());
        treatment.setText(issueInfo.getTreatmentDescription());
        possible_syms.setText(issueInfo.getPossibleSymptoms());
    }
}
