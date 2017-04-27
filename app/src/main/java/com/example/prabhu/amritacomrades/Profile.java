package com.example.prabhu.amritacomrades;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    private ProgressDialog dialog;
    private DatabaseReference databaseReference;
    private TextView textname, textmail, textpass, textnum, textdep, textsem, textcg, texttech;
    private Button button;
    private AlertDialog.Builder builder;
    private String uid;
    private Button bdone, b4, b6, b7, b8, b9, b10;


    private static final String TAG = "Profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        builder = new AlertDialog.Builder(this);
        dialog = new ProgressDialog(Profile.this);
        dialog.setMessage("Loading...Please Wait");
        dialog.show();
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(uid);
        Toolbar toolbar=(Toolbar)findViewById(R.id.projecttoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        bdone = (Button)findViewById(R.id.buttondone);

        b8 = (Button)findViewById(R.id.button8);
        b9 = (Button)findViewById(R.id.button9);
        b10 = (Button)findViewById(R.id.button10);

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
                dialog.dismiss();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Update Semester");
                final EditText input = new EditText(Profile.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);

                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String n = input.getText().toString();
                        databaseReference.child("Semester").setValue(n);
                        Intent intent = new Intent(Profile.this, Profile.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Update CGPA");
                final EditText input = new EditText(Profile.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);

                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.getInstance().signOut();
                        String n = input.getText().toString();
                        databaseReference.child("CGPA").setValue(n);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Update Technical Specification");
                final EditText input = new EditText(Profile.this);
                input.setText(texttech.getText().toString());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);

                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.getInstance().signOut();
                        String n = input.getText().toString();
                        databaseReference.child("Technical").setValue(n);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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

