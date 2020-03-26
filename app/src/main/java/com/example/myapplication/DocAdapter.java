package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DocAdapter extends RecyclerView.Adapter <DocAdapter.ViewHolder>{
    private Context context;
    private List<doctor> doclist;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView dname;
        public TextView dspec;
        public TextView daddr;


        public ViewHolder(View view){
            super(view);
            context=view.getContext();
            dname=view.findViewById(R.id.tv_doctor_name_card);
            dspec=view.findViewById(R.id.tv_doctor_specialty_card);
            daddr=view.findViewById(R.id.tv_doctor_address_card);


        }
    }
    public DocAdapter(Context context,List<doctor> doclist){
        this.context=context;
        this.doclist=doclist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(viewType, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;




    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.doc_item;
    }
    @Override
    public void onBindViewHolder(@NonNull DocAdapter.ViewHolder holder, int position) {
 doctor doc=doclist.get(position);
 final String m=doc.getDocAddr();
 TextView name=holder.dname;

        TextView spec=holder.dspec;

        TextView addr=holder.daddr;
                name.setText("Dr. "+doc.getDocName());
        spec.setText(doc.getDocSpec());
        addr.setText(doc.getDocAddr());


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DocProfile.class);
                database db=new database(context);
                doctor d=db.getDocAddr(m);
                Toast.makeText(context, d.getDocDuid(), Toast.LENGTH_SHORT).show();
                intent.putExtra("id",d.getDocDuid());



                context.startActivity(intent);
            }
        });


    }




    @Override
    public int getItemCount() {
        return doclist.size();
    }







}

