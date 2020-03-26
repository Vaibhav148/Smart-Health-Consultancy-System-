package com.example.myapplication;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class patMedAdapter extends RecyclerView.Adapter<patMedAdapter.ViewHolder> {
    private Context context;
    private List<medical_rec> med;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView doc_name;
        public TextView sym;
        public TextView dis;
        public TextView pres;



        public ViewHolder(View view){
            super(view);
            context=view.getContext();
            doc_name=view.findViewById(R.id.pat_med_doc_name);
            sym=view.findViewById(R.id.pat_med_sym);
            dis=view.findViewById(R.id.pat_med_dis);
            pres=view.findViewById(R.id.pat_med_pres);



        }
    }
    public patMedAdapter(Context context,List<medical_rec> med){
        this.context=context;
        this.med =med;
    }

    @NonNull
    @Override
    public patMedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(viewType, parent, false);

        // Return a new holder instance
        patMedAdapter.ViewHolder viewHolder = new patMedAdapter.ViewHolder(contactView);
        return viewHolder;




    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.pat_med_recycle_item;
    }


    @Override
    public void onBindViewHolder(@NonNull patMedAdapter.ViewHolder holder, int position) {
        medical_rec doc=med.get(position);

        TextView name=holder.doc_name;

        TextView sym=holder.sym;
        TextView dis=holder.dis;
        TextView pres=holder.pres;
        database db=new database(context);

        int did=doc.getMdid();
        doctor d=db.getDocId(String.valueOf(did));

        String namegg = "<b>" + "Doctor name : " + "</b> " + "Dr. "+d.getDocName();
        name.setText(Html.fromHtml(namegg));
        String symgg = "<b>" + "Symptoms : " + "</b> " + doc.getSym();
        String disgg = "<b>" + "Disease : " + "</b> " +doc.getDis();
        String presgg = "<b>" + "Prescription : " + "</b> " +doc.getPres();




        //name.setText("Doctor name "+"Dr. "+d.getDocName());
        sym.setText(Html.fromHtml(symgg));
        dis.setText(Html.fromHtml(disgg));
        pres.setText(Html.fromHtml(presgg));



    }






    @Override
    public int getItemCount() {
        return med.size();
    }







}
