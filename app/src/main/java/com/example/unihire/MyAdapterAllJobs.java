package com.example.unihire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterAllJobs extends RecyclerView.Adapter<MyAdapterAllJobs.MyViewHolder1> {
    Context context;
    public ArrayList<Job> list;

    public MyAdapterAllJobs(Context context, ArrayList<Job> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public MyAdapterAllJobs.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.job_item_active,parent,false);
        return new MyAdapterAllJobs.MyViewHolder1(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterAllJobs.MyViewHolder1 holder, int position) {
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

    public static class MyViewHolder1 extends RecyclerView.ViewHolder{

        Context context;
        TextView job_title,dept,spec,datetime;
        public MyViewHolder1(@NonNull View itemView,Context context) {
            super(itemView);
            job_title=itemView.findViewById(R.id.job_title_aj);
            dept=itemView.findViewById(R.id.dept_aj);
            spec=itemView.findViewById(R.id.spec_aj);
            datetime=itemView.findViewById(R.id.date_time_aj);

            this.context=context;


        }
    }
}
