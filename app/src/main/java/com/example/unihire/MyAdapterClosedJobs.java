package com.example.unihire;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterClosedJobs extends RecyclerView.Adapter<MyAdapterClosedJobs.MyViewHolder2> {
    Context context;
    static public ArrayList<Job> list;


    public MyAdapterClosedJobs(Context context, ArrayList<Job> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapterClosedJobs.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.job_item_closed, parent, false);
        return new MyAdapterClosedJobs.MyViewHolder2(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterClosedJobs.MyViewHolder2 holder, int position) {
        Job job = list.get(position);
        holder.job_title.setText(job.JobTitle);
        holder.dept.setText(job.Department);
        holder.spec.setText(job.Specialization);
        holder.datetime.setText(job.closedDateTime);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder {

        Context context;
        TextView job_title, dept, spec, datetime;

        public MyViewHolder2(@NonNull View itemView, Context context) {
            super(itemView);
            job_title = itemView.findViewById(R.id.job_title_cj);
            dept = itemView.findViewById(R.id.dept_cj);
            spec = itemView.findViewById(R.id.spec_cj);
            datetime = itemView.findViewById(R.id.date_time_cj);

            this.context = context;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jobID=list.get(getAdapterPosition()).jobID;
                    Intent intent=new Intent(context, SeeApplicantsClosed.class);
                    intent.putExtra("JOBID", jobID);
                    context.startActivity(intent);
                }
            });
        }
    }
}
