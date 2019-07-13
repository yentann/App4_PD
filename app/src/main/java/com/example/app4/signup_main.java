package com.example.app4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup_main extends AppCompatActivity {

    private EditText EmailText, PasswordText;
    private Button btnRegister;
    private TextView clickalreadyregistered;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_signup);

        //firebase auto instance
        auth = FirebaseAuth.getInstance();


        EmailText = (EditText) findViewById(R.id.editTextEmail);
        PasswordText = (EditText) findViewById(R.id.editTextPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        clickalreadyregistered = (TextView) findViewById(R.id.textViewReadyRegister);



        clickalreadyregistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup_main.this, login_main.class);
                startActivity(intent);
            }
        });

        //Register Button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailText.getText().toString().trim();
                String password = PasswordText.getText().toString().trim();


                //TextUtils == String
                if (TextUtils.isEmpty(email)) {
                    //if email is empty
                    Toast.makeText(getApplicationContext(), "Please Enter your Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please Enter your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //check for auth (Create a new account by passing the new user's email address and password to createUserWithEmailAndPassword)
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(signup_main.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(signup_main.this, "Authentication Failed",Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(signup_main.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
