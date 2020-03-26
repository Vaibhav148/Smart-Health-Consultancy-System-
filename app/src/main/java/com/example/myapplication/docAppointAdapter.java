package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class docAppointAdapter extends RecyclerView.Adapter<docAppointAdapter.ViewHolder> {
    private Context context;
    private List<appointment> appoint;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView pat;
        public Button accept;



        public ViewHolder(View view){
            super(view);
            context=view.getContext();
            pat=view.findViewById(R.id.docAppointText);
            accept=view.findViewById(R.id.docAppointAccept);



        }
    }
    public docAppointAdapter(Context context,List<appointment> appointments){
        this.context=context;
        this.appoint=appointments;
    }

    @NonNull
    @Override
    public docAppointAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(viewType, parent, false);

        // Return a new holder instance
        docAppointAdapter.ViewHolder viewHolder = new docAppointAdapter.ViewHolder(contactView);
        return viewHolder;




    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.doc_appoint_item;
    }


    @Override
    public void onBindViewHolder(@NonNull docAppointAdapter.ViewHolder holder, int position) {
        appointment doc=appoint.get(position);

        TextView name=holder.pat;

        final Button accept=holder.accept;
        database db=new database(context);
        final int appid=doc.getAppid();
        //Toast.makeText(context, doc.getDate(), Toast.LENGTH_SHORT).show();

        int pid=doc.getPpid();
        patient d=db.getPatByid(String.valueOf(pid));



        name.setText(d.getPatName());
        if(doc.getDate().equals("")) {
            accept.setText("Accept");

        }else{
            accept.setText("Medical Record");


        }

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(accept.getText().toString().equals("Accept")) {
                    Intent i = new Intent(context, acceptPat.class);
                    i.putExtra("appointid", appid);
                    // Toast.makeText(context, String.valueOf(appid), Toast.LENGTH_SHORT).show();
                    context.startActivity(i);
                }
                else{
                    Intent i=new Intent(context,docMedRec.class);
                    i.putExtra("medrecid", appid);
                    context.startActivity(i);


                }


            }
        });



    }






    @Override
    public int getItemCount() {
        return appoint.size();
    }







}
