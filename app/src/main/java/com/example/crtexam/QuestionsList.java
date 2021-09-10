package com.example.crtexam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class QuestionsList extends AppCompatActivity{

    Button opA,opB,opC,opD;
    TextView test,countDown,text_ques;
    ListView ques_list;
    ArrayAdapter<String> adapter;
    ArrayList list_of_ques;
    ActionBar ab;
    Button b1,b2,b3,b4;
    int testno=0,position=0,correct=0,wrong=0,index,total=0;
    CountDownTimer countDownTimer;
    FirebaseAuth mAuth;
    DatabaseReference ref;
    ArrayList list=new ArrayList();
    Questions que;
    String currentUserId;
    String q,oa,ob,oc,od,an;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_list);
        InitializeFields();
        retriveQuestions();

        test.setText("Test"+testno);
        countDownTimer= new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                countDown.setText((millisUntilFinished / 1000)/60+" : " + millisUntilFinished / 1000);
            }

            public void onFinish()
            {
                countDown.setText("done!");
                gotoResultPage();
                countDownTimer.cancel();
            }
        }.start();
    }



    private void InitializeFields() {

        mAuth=FirebaseAuth.getInstance();
        currentUserId=mAuth.getCurrentUser().getUid();
        test=findViewById(R.id.test);
        countDown=(TextView) findViewById(R.id.countdown);
        b1=findViewById(R.id.op_a);
        b2=findViewById(R.id.op_b);
        b3=findViewById(R.id.op_c);
        b4=findViewById(R.id.op_d);
        text_ques=findViewById(R.id.text_ques);
    }

    private void retriveQuestions() {

        total++;
        if(total>10)
        {
            gotoResultPage();
            countDownTimer.cancel();
        }
        else
        {
                    ref=FirebaseDatabase.getInstance().getReference("Questions");
                    ref.child(String.valueOf(total)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final ArrayList<String> list=new ArrayList<>();
                        list.clear();

                        list.add(dataSnapshot.child("question").getValue().toString());
                        list.add(dataSnapshot.child("option_a").getValue().toString());
                        list.add(dataSnapshot.child("option_b").getValue().toString());
                        list.add(dataSnapshot.child("option_c").getValue().toString());
                        list.add(dataSnapshot.child("option_d").getValue().toString());
                        list.add(dataSnapshot.child("correct_answer").getValue().toString());

                        text_ques.setText(list.get(0));
                        b1.setText(list.get(1));
                        b2.setText(list.get(2));
                        b3.setText(list.get(3));
                        b4.setText(list.get(4));
                        b1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                if(b1.getText().equals(list.get(5)))
                                {
                                    correct++;
                                    retriveQuestions();
                                }
                                else
                                {
                                    wrong++;
                                    retriveQuestions();
                                }
                            }
                        });
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                if(b2.getText().equals(list.get(5)))
                                {
                                    correct++;
                                    retriveQuestions();
                                }
                                else
                                {
                                    wrong++;
                                    retriveQuestions();
                                }
                            }
                        });
                        b3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                if(b3.getText().equals(list.get(5)))
                                {
                                    correct++;
                                    retriveQuestions();
                                }
                                else
                                {
                                    wrong++;
                                    retriveQuestions();
                                }
                            }
                        });
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                if(b4.getText().equals(list.get(5)))
                                {
                                    correct++;
                                    retriveQuestions();
                                }
                                else
                                {
                                    wrong++;
                                    retriveQuestions();
                                }
                            }
                        });
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


    //This is the app that i am developing sir


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit Prompt");
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alertD = builder.create();
        alertD.show();

        countDownTimer.cancel();
    }

    public void gotoResultPage()
    {
        Intent i=new Intent(QuestionsList.this,ResultActivity.class);
        i.putExtra("correct",String.valueOf(correct));
        i.putExtra("wrong",String.valueOf(wrong));
        i.putExtra("total",String.valueOf(total-1));
        i.putExtra("test_no",String.valueOf(testno));
        startActivity(i);
    }
}
