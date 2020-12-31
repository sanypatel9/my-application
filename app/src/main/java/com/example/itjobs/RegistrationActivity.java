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

public class RegistrationActivity extends AppCompatActivity {
    private EditText EmailReg;
    private EditText PasswordReg;
    private EditText ReEnterPass;

    private Button BtnReg;
    private Button BtnLogin;

    private ProgressDialog mDialog;

    //Firebase Auth

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();


        mDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        RegisterFuction();
    }

    private void RegisterFuction() {

        EmailReg = findViewById(R.id.email_registration);
        PasswordReg = findViewById(R.id.password_registration);
        ReEnterPass=findViewById(R.id.re_password_registration);

        BtnReg = findViewById(R.id.btn_registration);
        BtnLogin = findViewById(R.id.btn_login);

        BtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EmailReg.getText().toString().trim();
                String password = PasswordReg.getText().toString().trim();
                String Re_password=ReEnterPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    EmailReg.setError(" Reuqired Field....");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    PasswordReg.setError(" Reuqired Field....");
                    return;
                }


                if (!PasswordReg.getText().toString().equals(ReEnterPass.getText().toString())) {

                    Toast.makeText(RegistrationActivity.this, "Confirm password is not correct", Toast.LENGTH_SHORT).show();

                }

                mDialog.setMessage("Processing...");
                mDialog.show();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(RegistrationActivity.this, "Succesful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            mDialog.dismiss();

                        } else {
                            Toast.makeText(RegistrationActivity.this, "Registration Failde..", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }

                    }
                });
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
    }

}