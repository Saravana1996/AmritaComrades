package com.example.prabhu.amritacomrades;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prabhu.amritacomrades.Learn.Aboutus;
import com.example.prabhu.amritacomrades.Learn.learn;
import com.google.firebase.auth.FirebaseAuth;

public class selectDep extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dep);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Toolbar tb=(Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(tb);

        builder = new AlertDialog.Builder(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Departments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    RadioButton r1=(RadioButton)findViewById(R.id.radioButton3);
                    RadioButton r2=(RadioButton)findViewById(R.id.radioButton4);
                    if(r1.isChecked()){
                        callfaculty();
                    }
                    else if(r2.isChecked()){
                        Spinner spinner = (Spinner) findViewById(R.id.spinner);
                        String s=spinner.getSelectedItem().toString();
                        if(s.equalsIgnoreCase("select a department")){
                            Toast.makeText(getApplication(),"Please select Department again",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent=new Intent(selectDep.this,Pro.class);
                            intent.putExtra("val",s);
                            startActivity(intent);
                        }

                    }
                    else{
                        Toast.makeText(getApplication(),"Please select Search for again",Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }

    public void callfaculty(){

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String s=spinner.getSelectedItem().toString();

        if(s.equalsIgnoreCase("select a department")){
            Toast.makeText(getApplication(),"Please select Department again",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent3=new Intent(selectDep.this,Faculty.class);
            intent3.putExtra("dept",s);
            startActivity(intent3);
        }
    }

    public void callprojects(){

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_dep, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout2) {
            builder.setTitle("Logout");
            builder.setMessage("Are you sure you want to Logout?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    firebaseAuth.getInstance().signOut();
                    Toast.makeText(getApplication(),"Logout Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(selectDep.this, Homepage.class);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent intent=new Intent(selectDep.this,Profile.class);
            startActivity(intent);
        } else if (id == R.id.aboutus1) {
            Intent intent=new Intent(selectDep.this,Aboutus.class);
            startActivity(intent);

        } else if (id == R.id.learn1) {
            Intent intent=new Intent(selectDep.this,learn.class);
            startActivity(intent);

        } else if (id == R.id.nav_share1) {

        } else if (id == R.id.nav_send1) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
