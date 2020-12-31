package com.example.itjobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itjobs.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostJobAcitvity extends AppCompatActivity {
    private FloatingActionButton fabButton;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private DatabaseReference mJobpostDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job_acitvity);
        getSupportActionBar().hide();
        mAuth=FirebaseAuth.getInstance();

        FirebaseUser mUser=mAuth.getCurrentUser();
        String uId=mUser.getUid();


        mJobpostDatabase= FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        recyclerView=findViewById(R.id.recycle_job_post);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);




        fabButton = findViewById(R.id.btn_Post);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),insertJobPostActivity.class));

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Data> firebaseRecyclerOptions =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(mJobpostDatabase, Data.class)
                        .build();


        FirebaseRecyclerAdapter<Data,MyViewHolder>adapter=new FirebaseRecyclerAdapter<Data, MyViewHolder>(firebaseRecyclerOptions)
        {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Data model) {

                holder.setJobTitel(model.getTitle());
                holder.setJobDate(model.getData());
                holder.setJobDescraption(model.getDes());
                holder.setJobSkill(model.getSkill());
                holder.setJobSalary(model.getSalary());

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_post_item,parent,false);
                return new MyViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        View myview;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
        }
        public void setJobTitel(String titel){

            TextView mTitel =myview.findViewById(R.id.job_title);
            mTitel.setText(titel);


        }
        public void setJobDate(String Date){

            TextView mDate =myview.findViewById(R.id.job_date);
            mDate.setText(Date);


        }
        public void setJobDescraption (String Des){

            TextView mDes =myview.findViewById(R.id.job_des);
            mDes.setText(Des);


        }
        public void setJobSkill(String Skill){

            TextView mSkill =myview.findViewById(R.id.job_skill);
            mSkill.setText(Skill);


        }
        public void setJobSalary(String Salary){

            TextView mSalary =myview.findViewById(R.id.job_salary);
            mSalary.setText(Salary);


        }
    }
}