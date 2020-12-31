package com.example.itjobs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;

    private Button Login;
    private Button Registration;


    //Progress Diolog
    private ProgressDialog mDialog;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //Firebase Auth//
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){

          startActivity(new Intent(getApplicationContext(),HomeActivity.class) );

        }

        //progress Diolog
        mDialog = new ProgressDialog(this);

        LoginFunction();


    }

    private void LoginFunction() {

        Email=findViewById(R.id.email_login);
        Password=findViewById(R.id.email_password);


        Login = findViewById(R.id.btn_login);
        Registration = findViewById(R.id.btn_registration);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = Email.getText().toString().trim();
                String mPassword = Password.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail)) {
                    Email.setError(" Reuqired Field....");
                    return;
                }
                if (TextUtils.isEmpty(mPassword)) {
                    Password.setError(" Reuqired Field....");
                    return;
                }

                mDialog.setMessage("Processing...");
                mDialog.show();

                mAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Sucessfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            mDialog.dismiss();
                        } else {

                            Toast.makeText(MainActivity.this, "Failed To Login", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }

                    }
                });

            }
        });
        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));

            }
        });


    }


}