package com.example.app4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_main extends AppCompatActivity {

    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginaccount;
    private TextView registeraccount;
    private TextView forgotpassword;

    private FirebaseAuth auth;

    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        auth = FirebaseAuth.getInstance();

        loginEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        loginPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        loginaccount = (Button) findViewById(R.id.btnLogin);
        registeraccount = (TextView) findViewById(R.id.txtSignUpPage);
        forgotpassword = (TextView) findViewById(R.id.textViewForgotPassword);


        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_main.this, activity_forgotpassword.class);
                startActivity(intent);
            }
        });

        registeraccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_main.this, signup_main.class));
            }
        });


        loginaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                final String password = loginPassword.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                Toast.makeText(login_main.this, "Cannot be less than 6", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(login_main.this,"Please Try Again", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            startActivity(new Intent(login_main.this, MainActivity.class));
                            finish();
                        }
                        }
                    });
                }
            });
        }
    }