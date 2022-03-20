package com.example.unihire;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterDraft extends RecyclerView.Adapter<MyAdapterDraft.MyViewHolder>{

    Context context;
    ArrayList<Job> list;

    public MyAdapterDraft(Context context, ArrayList<Job> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.draft_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Job job=list.get(position);
        holder.job_title.setText(job.JobTitle);
        holder.dept.setText(job.Department);
        holder.spec.setText(job.Specialization);
        holder.datetime.setText(job.PostedDateTime);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView job_title,dept,spec,datetime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            job_title=itemView.findViewById(R.id.job_title_rv);
            dept=itemView.findViewById(R.id.dept_rv);
            spec=itemView.findViewById(R.id.spec_rv);
            datetime=itemView.findViewById(R.id.date_time_rv);
        }
    }
}
