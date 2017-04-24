package com.example.prabhu.amritacomrades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Profile extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private TextView textname, textmail, textpass, textnum, textdep, textsem, textcg, texttech;
    private Button button;
    private String uid;


    private static final String TAG = "Profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(uid);
        Toolbar toolbar=(Toolbar)findViewById(R.id.projecttoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        textname = (TextView)findViewById(R.id.textView21);
        textmail = (TextView)findViewById(R.id.textView23);

        textnum = (TextView)findViewById(R.id.textView27);
        textdep = (TextView)findViewById(R.id.textView29);
        textsem = (TextView)findViewById(R.id.textView31);
        textcg = (TextView)findViewById(R.id.textView33);
        texttech = (TextView)findViewById(R.id.textView35);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                textname.setText(dataSnapshot.child("Name").getValue().toString());
                textmail.setText(dataSnapshot.child("Email").getValue().toString());
                textnum.setText(dataSnapshot.child("RollNumber").getValue().toString());
                textdep.setText(dataSnapshot.child("Department").getValue().toString());
                textsem.setText(dataSnapshot.child("Semester").getValue().toString());
                textcg.setText(dataSnapshot.child("CGPA").getValue().toString());
                texttech.setText(dataSnapshot.child("Technical").getValue().toString());

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        button = (Button)findViewById(R.id.buttondone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, selectDep.class);
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }




}

