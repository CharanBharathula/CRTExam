package com.example.crtexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccount extends AppCompatActivity {

    TextView name,userid,password,mobile,rollnumber,branch,email;
    private FirebaseAuth mAuth;
    private DatabaseReference StudentRef;
    private String currentUserID,currentStudentRollno,currentStudentName,currentStudentPassword,currentStudentMobile,currentStudentBranch,currentStudentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        StudentRef = FirebaseDatabase.getInstance().getReference().child("StudentRef");

        name=(TextView)findViewById(R.id.name);
        userid=(TextView)findViewById(R.id.userid);
        password=(TextView)findViewById(R.id.password_for_myacnt);
        mobile=(TextView)findViewById(R.id.mobile);
        rollnumber=(TextView)findViewById(R.id.rollnumber);
        branch=(TextView)findViewById(R.id.branch);
        email=(TextView)findViewById(R.id.email_for_myacnt);

        GetStudentInfo();

    }

    private void GetStudentInfo() {

        StudentRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    currentStudentName = dataSnapshot.child("name").getValue().toString();
                    currentStudentEmail = dataSnapshot.child("email").getValue().toString();
                    currentStudentMobile = dataSnapshot.child("mobile").getValue().toString();
                    currentStudentBranch = dataSnapshot.child("branch").getValue().toString();
                    currentStudentPassword = dataSnapshot.child("password").getValue().toString();
                    currentStudentRollno = dataSnapshot.child("rollno").getValue().toString();

                    name.setText(currentStudentName);
                    email.setText(currentStudentEmail);
                    password.setText(currentStudentPassword);
                    mobile.setText(currentStudentMobile);
                    rollnumber.setText(currentStudentRollno);
                    branch.setText(currentStudentBranch);
                    userid.setText(currentUserID);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
