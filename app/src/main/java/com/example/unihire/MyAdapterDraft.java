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

public class MyAdapterDraft extends RecyclerView.Adapter<MyAdapterDraft.MyViewHolder>{

    Context context;
    static public ArrayList<Job> list;


    public MyAdapterDraft(Context context, ArrayList<Job> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.draft_item_with_delete,parent,false);
        return new MyViewHolder(v,context);
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

        Context context;
        TextView job_title,dept,spec,datetime;
        Button deleteDraftBtn;
        public MyViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            job_title=itemView.findViewById(R.id.job_title_rv);
            dept=itemView.findViewById(R.id.dept_rv);
            spec=itemView.findViewById(R.id.spec_rv);
            datetime=itemView.findViewById(R.id.date_time_rv);
            deleteDraftBtn=itemView.findViewById(R.id.deleteDraftBtn);
            this.context=context;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("RecyclerView", "onClick：" + getAdapterPosition());
                    String jobID=list.get(getAdapterPosition()).jobID;
                    Intent intent=new Intent(context, PostJobForm.class);
                    intent.putExtra("JOBID", jobID);
                    context.startActivity(intent);
                }
            });
            deleteDraftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jobID=list.get(getAdapterPosition()).jobID;
                    //Toast.makeText(context, jobID, Toast.LENGTH_SHORT).show();
                    DatabaseReference reff= FirebaseDatabase.getInstance().getReference("Job");
                    reff.child(jobID).removeValue();
                    if(context.equals(DraftJobsList.class)){
                        Intent intent=new Intent(context, DraftJobsList.class);
                        context.startActivity(intent);
                    }
                    else{
                        Intent intent=new Intent(context, RecruiterHomePage.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
