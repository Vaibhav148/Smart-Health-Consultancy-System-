package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class patAppointAdapter extends RecyclerView.Adapter<patAppointAdapter.ViewHolder> {
    private Context context;
    private List<appointment> appoint;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView doc_name;
        public TextView date;



        public ViewHolder(View view){
            super(view);
            context=view.getContext();
            doc_name=view.findViewById(R.id.pat_appoint_doc);
            date=view.findViewById(R.id.pat_appoint_date);



        }
    }
    public patAppointAdapter(Context context,List<appointment> appointments){
        this.context=context;
        this.appoint=appointments;
    }

    @NonNull
    @Override
    public patAppointAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(viewType, parent, false);

        // Return a new holder instance
        patAppointAdapter.ViewHolder viewHolder = new patAppointAdapter.ViewHolder(contactView);
        return viewHolder;




    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.activity_pat_appointments;
    }


    @Override
    public void onBindViewHolder(@NonNull patAppointAdapter.ViewHolder holder, int position) {
        appointment doc=appoint.get(position);

        TextView name=holder.doc_name;

        TextView spec=holder.date;
        database db=new database(context);

        int did=doc.getDdid();
        doctor d=db.getDocId(String.valueOf(did));
        //Toast.makeText(context, String.valueOf(doc.getAppid()), Toast.LENGTH_SHORT).show();


        String namegg = "<b>" + "Doctor name : " + "</b> " + "Dr. "+d.getDocName();
        name.setText(Html.fromHtml(namegg));
        //name.setText("Dr. "+d.getDocName());
        String dategg = "<b>" + "Appointment Date : " + "</b> " +doc.getDate();

        spec.setText(Html.fromHtml(dategg));



    }






    @Override
    public int getItemCount() {
        return appoint.size();
    }







}
