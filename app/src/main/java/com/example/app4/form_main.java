package com.example.app4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class form_main extends AppCompatActivity {

    EditText Name, ContactNo, Email, Slot;
    Button insert;

    DatabaseReference ref;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);

        //Set title
        getSupportActionBar().setTitle("Form");


        Name = findViewById(R.id.editTextName);
        ContactNo = findViewById(R.id.editTextContact);
        Email = findViewById(R.id.editTextEmail);
        insert = findViewById(R.id.btnInsert);
        Slot = findViewById(R.id.editTextSlot);

        user = new User();

        //Connect to the database
        ref = FirebaseDatabase.getInstance().getReference().child("User");


        //insert button
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int contact = Integer.parseInt(ContactNo.getText().toString());

                user.setName(Name.getText().toString());
                user.setContactnumber(contact);
                user.setEmail(Email.getText().toString());
                user.setSlot(Slot.getText().toString());

                //push - Add to a list of data in the database (save data)
                ref.push().setValue(user);

                Toast.makeText(form_main.this, "Form Submitted", Toast.LENGTH_LONG).show();
            }
        });
    }
}
