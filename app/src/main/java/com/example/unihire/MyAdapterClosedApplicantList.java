package com.example.unihire;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterClosedApplicantList extends RecyclerView.Adapter<MyAdapterClosedApplicantList.MyViewHolder4> {

    Context context;
    static public ArrayList<Applicant> list;

    public MyAdapterClosedApplicantList(Context context, ArrayList<Applicant> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyAdapterClosedApplicantList.MyViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.applicant_card, parent, false);
        return new MyAdapterClosedApplicantList.MyViewHolder4(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterClosedApplicantList.MyViewHolder4 holder, int position) {
        Applicant app=list.get(position);
        holder.name.setText(app.Name);
        holder.headline.setText(app.Headline);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder4 extends RecyclerView.ViewHolder{
        TextView name,headline;
        Context context;
        public MyViewHolder4(@NonNull View itemView, Context context) {
            super(itemView);

            name=itemView.findViewById(R.id.applicant_name_card);
            headline=itemView.findViewById(R.id.applicant_headline_card);
            this.context = context;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email=list.get(getAdapterPosition()).Email;
                    Intent intent=new Intent(context, ViewApplicantDetails.class);
                    intent.putExtra("APPEMAIL", email);
                    context.startActivity(intent);
                }
            });
        }
    }
}
