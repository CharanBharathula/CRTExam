package com.example.crtexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class StudentHomepage extends AppCompatActivity {

    Button changenumber,startexam,myaccount,logout;
    Intent i;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_homepage);
        changenumber=(Button)findViewById(R.id.changenumber);
        startexam=(Button)findViewById(R.id.startexam);
        myaccount=(Button)findViewById(R.id.myaccount);
        logout=(Button)findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();

        startexam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i=new Intent(StudentHomepage.this,QuestionsList.class);
                startActivity(i);
            }
        });

        myaccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i=new Intent(StudentHomepage.this,MyAccount.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mAuth.signOut();
                Intent i=new Intent(StudentHomepage.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        changenumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i=new Intent(StudentHomepage.this,ChangeMobile.class);
                startActivity(i);
            }
        });

    }
}
