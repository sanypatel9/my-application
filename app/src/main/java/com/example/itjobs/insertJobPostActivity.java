package com.example.itjobs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.itjobs.Model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class insertJobPostActivity extends AppCompatActivity {

    private EditText edtJobtitle,edtJobDes,edtJobSkll,edtjobSalary;
    private  Button btnPostJob;

    //FireBase
    private FirebaseAuth mAuth;
    private DatabaseReference mJobPost;
    private DatabaseReference mPublicDatabse;





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job_post);

        getSupportActionBar().hide();

        mAuth=FirebaseAuth.getInstance();

        FirebaseUser mUser=mAuth.getCurrentUser();
        String uId=mUser.getUid();

        mJobPost= FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        mPublicDatabse=FirebaseDatabase.getInstance().getReference().child("Public Databse");




        InsertJob();



    }


    private void InsertJob() {

        edtJobtitle=findViewById(R.id.edt_Jobtitle);
        edtJobDes=findViewById(R.id.edt_Jobdes);
        edtJobSkll=findViewById(R.id.edt_Skill);
        edtjobSalary=findViewById(R.id.edt_Jobsalary);


        btnPostJob=findViewById(R.id.btn_jobs_post);

        btnPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Title=edtJobtitle.getText().toString().trim();
                String Des=edtJobDes.getText().toString().trim();
                String Skill=edtJobSkll.getText().toString().trim();
                String Salary=edtjobSalary.getText().toString().trim();

                if (TextUtils.isEmpty(Title)){
                   edtJobtitle.setError("Require Feild...");
                   return;

                }
                if (TextUtils.isEmpty(Des)){
                    edtJobDes.setError("Require Feild...");
                    return;

                }
                if (TextUtils.isEmpty(Skill)){
                    edtJobSkll.setError("Require Feild...");
                    return;

                }
                if (TextUtils.isEmpty(Salary)){
                    edtjobSalary.setError("Require Feild...");
                    return;

                }

                String id=mJobPost.push().getKey();

                String date= DateFormat.getDateInstance().format(new Date());

                Data data=new Data(Title,Des,Skill,Salary,id,date);

                mJobPost.child(id).setValue(data);

                mPublicDatabse.child(id).setValue(data);

                Toast.makeText(insertJobPostActivity.this, "Created Succesfully....", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),PostJobAcitvity.class));

            }
        });
    }
}