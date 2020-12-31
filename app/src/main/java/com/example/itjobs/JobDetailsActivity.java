package com.example.itjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class JobDetailsActivity extends AppCompatActivity {

    private TextView titleTx,dateTx,Destx,skilltx,salarytx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        getSupportActionBar().hide();

        titleTx=findViewById(R.id.Job_details_title);
        dateTx=findViewById(R.id.Job_details_date);
        Destx=findViewById(R.id.Job_details_Descripation);
        skilltx=findViewById(R.id.Job_details_Skill);
        salarytx=findViewById(R.id.Job_details_Salary);

        //Recivie Data From PostJobActivity //

        Intent intent=getIntent();

        String title=intent.getStringExtra("title");
        String date=intent.getStringExtra("Date");
        String Des=intent.getStringExtra("Descripation");
        String Skill=intent.getStringExtra("Skill");
        String Salary=intent.getStringExtra("Salary");

        titleTx.setText(title);
        dateTx.setText(date);
        Destx.setText(Des);
        skilltx.setText(Skill);
        salarytx.setText(Salary);


    }
}