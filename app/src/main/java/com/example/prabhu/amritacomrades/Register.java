package com.example.prabhu.amritacomrades;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class Register extends ProgressActivity {
    private static final String TAG = "MyActiviy" ;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private DatabaseReference childRef, childRef1, childRef2;
    private FirebaseAuth firebaseauth1;
    private String roll, name, cgpa, tech, sem, email,pwd,dep;
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();

        Spinner sp2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter);

        Button bn3 = (Button) findViewById(R.id.button3);
        bn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e = (EditText) findViewById(R.id.editText2);
                EditText rn = (EditText) findViewById(R.id.rno);
                EditText p = (EditText) findViewById(R.id.editText4);
                EditText n = (EditText) findViewById(R.id.name);
                EditText cg = (EditText) findViewById(R.id.cgpa);
                Spinner sp = (Spinner) findViewById(R.id.spinner2);
                EditText t = (EditText) findViewById(R.id.tech);
                EditText de = (EditText) findViewById(R.id.dename);
                roll = rn.getText().toString();
                name = n.getText().toString();
                cgpa = cg.getText().toString();
                tech = t.getText().toString();
                sem = sp.getSelectedItem().toString();
                email = e.getText().toString();
                pwd = p.getText().toString();
                dep = de.getText().toString();

                if (roll.isEmpty() || email.isEmpty() || pwd.isEmpty() || name.isEmpty() || cgpa.isEmpty() || tech.isEmpty() || sem.isEmpty()) {
                    Toast.makeText(Register.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    showProgressDialog();
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                hideProgressDialog();
                                Toast.makeText(Register.this, "Error, please register again", Toast.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                try{
                                    String uid = user.getUid();
                                    childRef = myRef.child("Users");
                                    childRef1 = childRef.child(uid);
                                    childRef2 = childRef1.child("Name");
                                    childRef2.setValue(name);
                                    childRef2 = childRef1.child("RollNumber");
                                    childRef2.setValue(roll);
                                    childRef2 = childRef1.child("Email");
                                    childRef2.setValue(email);
                                    childRef2 = childRef1.child("Semester");
                                    childRef2.setValue(sem);
                                    childRef2 = childRef1.child("CGPA");
                                    childRef2.setValue(cgpa);
                                    childRef2 = childRef1.child("Technical");
                                    childRef2.setValue(tech);
                                    childRef2 = childRef1.child("Department");
                                    childRef2.setValue(dep);
                                }
                                catch (Exception e){

                                }
                                hideProgressDialog();
                                user.sendEmailVerification();
                                firebaseAuth.getInstance().signOut();


                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "User profile updated.");
                                                }
                                            }
                                        });


                                firebaseauth1.getInstance().signOut();
                                Intent intent2 = new Intent(Register.this, Homepage.class);
                                startActivity(intent2);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }

}
