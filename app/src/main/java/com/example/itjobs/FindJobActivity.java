package com.example.itjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.itjobs.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FindJobActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mFindJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_job);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.all_job_post);


        mFindJob = FirebaseDatabase.getInstance().getReference().child("Public Databse");
        mFindJob.keepSynced(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Data> firebaseRecyclerOptions =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(mFindJob, Data.class)
                        .build();


         FirebaseRecyclerAdapter<Data,JobPostViewHolder>adapter=new FirebaseRecyclerAdapter<Data, JobPostViewHolder>(firebaseRecyclerOptions) {
             @Override
             protected void onBindViewHolder(@NonNull JobPostViewHolder holder, int position, @NonNull Data model) {

                 holder.setJobTitel(model.getTitle());
                 holder.setJobDate(model.getData());
                 holder.setJobDescraption(model.getDes());
                 holder.setJobSkill(model.getSkill());
                 holder.setJobSalary(model.getSalary());

                 holder.myview.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent intent=new Intent(getApplicationContext(),JobDetailsActivity.class);

                         intent.putExtra("title",model.getTitle());
                         intent.putExtra("Date",model.getData());
                         intent.putExtra("Descripation",model.getDes());
                         intent.putExtra("Skill",model.getSkill());
                         intent.putExtra("Salary",model.getSalary());
                         startActivity(intent);

                     }
                 });



             }

             @NonNull
             @Override
             public JobPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_post_item,parent,false);
                 return new JobPostViewHolder(view);

             }
         };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    public static class JobPostViewHolder extends RecyclerView.ViewHolder {

        View myview;


        public JobPostViewHolder(@NonNull View itemView) {
            super(itemView);
            myview = itemView;
        }

        public void setJobTitel(String titel) {

            TextView mTitel = myview.findViewById(R.id.job_title);
            mTitel.setText(titel);
        }

        public void setJobDate(String Date) {

            TextView mDate = myview.findViewById(R.id.job_date);
            mDate.setText(Date);


        }

        public void setJobDescraption(String Des) {

            TextView mDes = myview.findViewById(R.id.job_des);
            mDes.setText(Des);


        }

        public void setJobSkill(String Skill) {

            TextView mSkill = myview.findViewById(R.id.job_skill);
            mSkill.setText(Skill);


        }

        public void setJobSalary(String Salary) {

            TextView mSalary = myview.findViewById(R.id.job_salary);
            mSalary.setText(Salary);
        }
    }
}