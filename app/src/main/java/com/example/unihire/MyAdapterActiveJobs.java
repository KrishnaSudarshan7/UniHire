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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapterActiveJobs extends RecyclerView.Adapter<MyAdapterActiveJobs.MyViewHolder1>
        {
    Context context;
    static public ArrayList<Job> list;


    public MyAdapterActiveJobs(Context context, ArrayList<Job> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapterActiveJobs.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.job_item_active,parent,false);
        return new MyAdapterActiveJobs.MyViewHolder1(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterActiveJobs.MyViewHolder1 holder, int position) {
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
        Button deleteDraftBtn;
        public MyViewHolder1(@NonNull View itemView,Context context) {
            super(itemView);
            job_title=itemView.findViewById(R.id.job_title_aj);
            dept=itemView.findViewById(R.id.dept_aj);
            spec=itemView.findViewById(R.id.spec_aj);
            datetime=itemView.findViewById(R.id.date_time_aj);

            this.context=context;

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
