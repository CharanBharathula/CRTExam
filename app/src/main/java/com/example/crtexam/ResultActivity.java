package com.example.crtexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    TextView tn,ca,wa,ta;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent i2=getIntent();

        ca=findViewById(R.id.correct);
        wa=findViewById(R.id.wrong);
        tn=findViewById(R.id.testno);
        ta=findViewById(R.id.total);

        String c=i2.getStringExtra("correct");
        String w=i2.getStringExtra("wrong");
        String t_n=i2.getStringExtra("test_no");
        String t_t=i2.getStringExtra("total");
        ca.setText(c);
        wa.setText(w);
        tn.setText(t_n);
        ta.setText(t_t);

        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=new Intent(ResultActivity.this,CorrectAnswers.class);
                startActivity(i);
            }
        });
        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=new Intent(ResultActivity.this,WrongAnswers.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(ResultActivity.this,StudentHomepage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
