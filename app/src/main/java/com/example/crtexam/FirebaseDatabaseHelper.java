package com.example.crtexam;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    List<Questions> ques=new ArrayList<>();
    public interface DataStatus
    {
        void DataIsLoaded(List<Questions> ques,List<String>keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    FirebaseDatabaseHelper()
    {
        mDatabase=FirebaseDatabase.getInstance();
        mReference=mDatabase.getReference("Questions");
    }
    public void readQuestions(final DataStatus dataStatus)
    {
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ques.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot qsnap:dataSnapshot.getChildren())
                {
                    keys.add(qsnap.getKey());
                    Questions questionobj=qsnap.getValue(Questions.class);
                    ques.add(questionobj);
                }
                dataStatus.DataIsLoaded(ques,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
