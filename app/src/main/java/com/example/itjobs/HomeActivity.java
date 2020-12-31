package com.example.itjobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button btn_FindJob;
    private Button btn_PostJob;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //getSupportActionBar().hide();

        mAuth=FirebaseAuth.getInstance();


        btn_FindJob=findViewById(R.id.btn_FindJob);
        btn_PostJob=findViewById(R.id.btn_PostJob);





        btn_PostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PostJobAcitvity.class));

            }
        });
        btn_FindJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FindJobActivity.class));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.menu:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}