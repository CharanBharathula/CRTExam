package com.example.crtexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    Button createaccount,login,reference,testdetails;
    Intent i;
    FirebaseAuth mAuth;
    DatabaseReference StudentRef;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createaccount=(Button)findViewById(R.id.createacc);
        login=(Button)findViewById(R.id.login);
        reference=(Button)findViewById(R.id.reference);
        testdetails=(Button)findViewById(R.id.testdetails);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        StudentRef = FirebaseDatabase.getInstance().getReference().child("Student");

        createaccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i=new Intent(MainActivity.this,CreateAccount.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i=new Intent(MainActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        reference.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i=new Intent(MainActivity.this,Reference.class);
                startActivity(i);
            }
        });

        testdetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i=new Intent(MainActivity.this,TestDetails.class);
                startActivity(i);
            }
        });

    }


    @Override
    protected void onStart()
    {
        super.onStart();

        if (currentUser == null)
        {

        }
        else
        {
            i=new Intent(MainActivity.this,StudentHomepage.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

}
