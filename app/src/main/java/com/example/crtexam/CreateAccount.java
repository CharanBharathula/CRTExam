package com.example.crtexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    EditText name,rollnumber,branch,email,password,phonenumber;
    Button submit;
    Intent i;
    FirebaseAuth mAuth;
    DatabaseReference StudentRef;
    ProgressDialog loadingBar;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        IntitializeFields();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNewAccount();
            }
        });

    }

    private void IntitializeFields() {

        name=findViewById(R.id.name_for_createacnt);
        rollnumber=findViewById(R.id.rollnumber_for_createacnt);
        branch=findViewById(R.id.branch_for_createacnt);
        email=findViewById(R.id.email_for_createacnt);
        password=findViewById(R.id.password_for_createacnt);
        phonenumber=findViewById(R.id.phonenumber_for_createacnt);
        submit=findViewById(R.id.submit_createacnt);
        loadingBar=new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        StudentRef = FirebaseDatabase.getInstance().getReference();
        database=FirebaseDatabase.getInstance();

    }

    private void CreateNewAccount() {
            final String StudentKey = StudentRef.push().getKey();
            final String Email = email.getText().toString();
            final String Password = password.getText().toString();
            final String Name=name.getText().toString();
            final String Rollno=rollnumber.getText().toString();
            final String Branch=branch.getText().toString();
            final String Mobile=phonenumber.getText().toString();
            if (TextUtils.isEmpty(Email))
            {
                Toast.makeText(this, "Please enter email...", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(Password))
            {
                Toast.makeText(this, "Please enter password...", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(Name))
            {
                Toast.makeText(this, "Please enter your Name...", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(Branch))
            {
                Toast.makeText(this, "Please enter your branch...", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(Mobile))
            {
                Toast.makeText(this, "Please enter your mobile number...", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(Rollno))
            {
                Toast.makeText(this, "Please enter your roll number...", Toast.LENGTH_SHORT).show();
            }
            else
            {
                loadingBar.setTitle("Creating New Account");
                loadingBar.setMessage("Please wait, while we wre creating new account for you...");
                loadingBar.setCanceledOnTouchOutside(true);
                loadingBar.show();

                mAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful())
                                {
                                    String currentUserId=mAuth.getCurrentUser().getUid();
                                    Student students=new Student(Email,Password,Name,Rollno,Mobile,Branch);
                                    StudentRef.child("StudentRef").child(currentUserId).setValue(students);

                                    SendUserToLoginActivity();
                                    Toast.makeText(CreateAccount.this, "Account Created Successfully...", Toast.LENGTH_SHORT).show();
                                    loadingBar.dismiss();
                                }
                                else
                                {
                                    String message = task.getException().toString();
                                    Toast.makeText(CreateAccount.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                                    loadingBar.dismiss();
                                }
                            }
                        });
            }
    }

    private void SendUserToLoginActivity() {
        i=new Intent(CreateAccount.this,LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
