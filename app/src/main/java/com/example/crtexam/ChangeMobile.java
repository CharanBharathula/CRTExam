package com.example.crtexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeMobile extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference MobileRef;
    EditText mobile;
    Button update;
    String currentStudentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_mobile);

        mobile=findViewById(R.id.editText_mobile);
        update=findViewById(R.id.button_update);

        mAuth= FirebaseAuth.getInstance();
        currentStudentId=mAuth.getCurrentUser().getUid();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobileRef=FirebaseDatabase.getInstance().getReference("StudentRef").child(currentStudentId).child("mobile");
            }
        });
    }
}
