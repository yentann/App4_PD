package com.example.app4;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class activity_forgotpassword extends AppCompatActivity {

    EditText EditTextForgotPassword;
    Button ResetPassword;
    TextView BackHome;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        EditTextForgotPassword = findViewById(R.id.editTextForgotPassword);
        ResetPassword = findViewById(R.id.btnResetPassword);
        BackHome = findViewById(R.id.textViewBackHome);

        auth = FirebaseAuth.getInstance();


        //Back to Login Page
        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Reset Password Button
        ResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EditTextForgotPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Please Enter Your Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(activity_forgotpassword.this, "Check Your Email!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(activity_forgotpassword.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }

}