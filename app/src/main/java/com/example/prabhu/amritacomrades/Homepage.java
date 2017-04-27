package com.example.prabhu.amritacomrades;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prabhu.amritacomrades.Learn.Aboutus;
import com.example.prabhu.amritacomrades.Learn.learn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Homepage extends ProgressActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private EditText em, pass;
    private Button bn;
    private FirebaseAuth firebaseAuth, firebaseAuth2;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;
    private String rno = "",pwd = "";
    private String ema;
    private AlertDialog.Builder builder;
    private TextView textView;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        builder = new AlertDialog.Builder(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = firebaseAuth.getInstance().getCurrentUser();
        textView = (TextView)findViewById(R.id.textView3);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Enter your Email-id");
                final EditText input = new EditText(Homepage.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ema = input.getText().toString();
                        firebaseAuth2.sendPasswordResetEmail(ema).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Check your mail to reset the password", Toast.LENGTH_SHORT);
                                }
                            }
                        });
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
        dialog = new ProgressDialog(this);
        bn=(Button)findViewById(R.id.button);
        em=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText3);
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){

                if(user != null && user.isEmailVerified()){
                    Intent intent2=new Intent(Homepage.this,selectDep.class);
                    startActivity(intent2);
                    finish();
                }
            }
        };
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetails();
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void Reg(View view){
        Intent intent=new Intent(Homepage.this,Register.class);
        startActivity(intent);

    }
    @Override
    public void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    private void getDetails(){

        if (TextUtils.isEmpty(em.getText().toString())) {
            em.setError("Required");
        } else {
            em.setError(null);
            rno = em.getText().toString();
        }

        if (TextUtils.isEmpty(pass.getText().toString())) {
            pass.setError("Required");
        } else {
            pass.setError(null);
            pwd = pass.getText().toString();
        }
        if(TextUtils.isEmpty(rno) || TextUtils.isEmpty(pwd)){
            Toast.makeText(Homepage.this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else {
            dialog.setMessage("Logging in...");
            dialog.show();
            firebaseAuth.signInWithEmailAndPassword(rno, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        dialog.dismiss();
                        Toast.makeText(Homepage.this, "Invalid e-mail or password", Toast.LENGTH_SHORT).show();
                    }
                    else if(!user.isEmailVerified()){
                        dialog.dismiss();
                        Toast.makeText(Homepage.this, "Verify your email id", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        startActivity(new Intent(Homepage.this, Homepage.class));
                        dialog.dismiss();
                    }
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {

        } else if (id == R.id.aboutus) {
            Intent intent=new Intent(Homepage.this,Aboutus.class);
            startActivity(intent);

        } else if (id == R.id.learn) {
            Intent intent=new Intent(Homepage.this,learn.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
