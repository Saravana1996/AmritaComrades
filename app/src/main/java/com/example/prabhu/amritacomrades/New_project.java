package com.example.prabhu.amritacomrades;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.id;
import static android.R.attr.state_multiline;
import static com.example.prabhu.amritacomrades.R.id.field_member;
import static com.example.prabhu.amritacomrades.R.id.start;
import static com.example.prabhu.amritacomrades.R.id.visible;

public class New_project extends ProgressActivity {
    private String selectedtext, value, number;
    private int num, check, check1, check2, check3;
    private static final String TAG = "New_project";
    private boolean flagre1, flagre2, flagre3, flagre4,flagre5;
    private EditText editrequest1, editrequest2, editrequest3, editrequest4, editrequest5, edittitle, editabout, editdomain, editdepartment;
    private Spinner spinrequest1, spinrequest2, spinrequest3, spinrequest4, spinrequest5;
    private RadioGroup radioGroup;
    private String req1, req2, req3, req4, req5, title, about, domain, department, email, uid, key;
    private int[] no = new int[5];
    private DatabaseReference databaseReference;
    private ArrayAdapter<CharSequence> numadapter1,numadapter2, numadapter3, numadapter4, numadapter5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
//        final TextView textView = new TextView(this);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbarnew);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        databaseReference  = FirebaseDatabase.getInstance().getReference();
        edittitle = (EditText)findViewById(R.id.field_title);
        editabout = (EditText)findViewById(R.id.field_body);
        editdepartment = (EditText)findViewById(R.id.field_department);
        editdomain = (EditText)findViewById(R.id.field_domain);
        editrequest1 = (EditText)findViewById(R.id.field_member);
        editrequest2 = (EditText)findViewById(R.id.field_member1);
        editrequest3 = (EditText)findViewById(R.id.field_member2);
        editrequest4 = (EditText)findViewById(R.id.field_member3);
        editrequest5 = (EditText)findViewById(R.id.field_member4);
        spinrequest1 = (Spinner)findViewById(R.id.spinner4);
        spinrequest2 = (Spinner)findViewById(R.id.spinner5);
        spinrequest3 = (Spinner)findViewById(R.id.spinner6);
        spinrequest4 = (Spinner)findViewById(R.id.spinner7);
        spinrequest5 = (Spinner)findViewById(R.id.spinner8);
        numadapter1 = ArrayAdapter.createFromResource(this, R.array.number1, android.R.layout.simple_spinner_item);
        numadapter2 = ArrayAdapter.createFromResource(this, R.array.number2, android.R.layout.simple_spinner_item);
        numadapter3 = ArrayAdapter.createFromResource(this, R.array.number3, android.R.layout.simple_spinner_item);
        numadapter4 = ArrayAdapter.createFromResource(this, R.array.number4, android.R.layout.simple_spinner_item);
        numadapter5 = ArrayAdapter.createFromResource(this, R.array.number5, android.R.layout.simple_spinner_item);
        numadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numadapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numadapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numadapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()            {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int selectedId) {
                selectedId=radioGroup.getCheckedRadioButtonId();
                RadioButton numberchoosed = (RadioButton) findViewById(selectedId);
                number = numberchoosed.getText().toString();
                num = Integer.parseInt(number);

                switch (num){
                    case 1: spinrequest1.setAdapter(numadapter5);flagre1 = true;break;
                    case 2: spinrequest1.setAdapter(numadapter4);flagre1 = true;break;
                    case 3: spinrequest1.setAdapter(numadapter3);flagre1 = true;break;
                    case 4: spinrequest1.setAdapter(numadapter2);flagre1 = true;break;
                    case 5: spinrequest1.setAdapter(numadapter1);flagre1 = true;break;
                    default:break;
                }

            }
        });

        spinrequest1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String items = spinrequest1.getSelectedItem().toString();
                int temp = Integer.parseInt(items);
                no[0] = temp;
                check = num-temp;
                if(check == 0){
                    flagre2 = false;
                    flagre3 = false;
                    flagre4 = false;
                    flagre5 = false;
                    editrequest2.setVisibility(View.GONE);
                    editrequest3.setVisibility(View.GONE);
                    editrequest4.setVisibility(View.GONE);
                    editrequest5.setVisibility(View.GONE);
                    spinrequest2.setVisibility(View.GONE);
                    spinrequest3.setVisibility(View.GONE);
                    spinrequest4.setVisibility(View.GONE);
                    spinrequest5.setVisibility(View.GONE);
                }
                check1 = 0;
                check2 = 0;
                switch (check){
                    case 1:editrequest2.setVisibility(View.VISIBLE); spinrequest2.setVisibility(View.VISIBLE); spinrequest2.setAdapter(numadapter5);flagre2 = true;break;
                    case 2:editrequest2.setVisibility(View.VISIBLE); spinrequest2.setVisibility(View.VISIBLE); spinrequest2.setAdapter(numadapter4);flagre2 = true;break;
                    case 3:editrequest2.setVisibility(View.VISIBLE); spinrequest2.setVisibility(View.VISIBLE); spinrequest2.setAdapter(numadapter3);flagre2 = true;break;
                    case 4:editrequest2.setVisibility(View.VISIBLE); spinrequest2.setVisibility(View.VISIBLE); spinrequest2.setAdapter(numadapter2);flagre2 = true;break;
                    case 5:return;
                    default:return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        spinrequest2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String items = spinrequest2.getSelectedItem().toString();
                int temp = Integer.parseInt(items);
                no[1] = temp;
                check1 = check-temp;
                if(check1 == 0){
                    flagre3 = false;
                    flagre4 = false;
                    flagre5 = false;
                    editrequest3.setVisibility(View.GONE);
                    editrequest4.setVisibility(View.GONE);
                    editrequest5.setVisibility(View.GONE);
                    spinrequest3.setVisibility(View.GONE);
                    spinrequest4.setVisibility(View.GONE);
                    spinrequest5.setVisibility(View.GONE);
                }
                switch (check1){
                    case 1:editrequest3.setVisibility(View.VISIBLE); spinrequest3.setVisibility(View.VISIBLE); spinrequest3.setAdapter(numadapter5);flagre3 = true;break;
                    case 2:editrequest3.setVisibility(View.VISIBLE); spinrequest3.setVisibility(View.VISIBLE); spinrequest3.setAdapter(numadapter4);flagre3 = true;break;
                    case 3:editrequest3.setVisibility(View.VISIBLE); spinrequest3.setVisibility(View.VISIBLE); spinrequest3.setAdapter(numadapter3);flagre3 = true;break;
                    case 4:break;
                    default:break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        spinrequest3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String items = spinrequest3.getSelectedItem().toString();
                int temp = Integer.parseInt(items);
                no[2] = temp;
                check2 = check1-temp;
                if(check2 == 0){
                    flagre4 = false;
                    flagre5 = false;
                    editrequest4.setVisibility(View.GONE);
                    editrequest5.setVisibility(View.GONE);
                    spinrequest4.setVisibility(View.GONE);
                    spinrequest5.setVisibility(View.GONE);
                }
                switch (check2){
                    case 1:editrequest4.setVisibility(View.VISIBLE); spinrequest4.setVisibility(View.VISIBLE); spinrequest4.setAdapter(numadapter5);flagre4 = true;break;
                    case 2:editrequest4.setVisibility(View.VISIBLE); spinrequest4.setVisibility(View.VISIBLE); spinrequest4.setAdapter(numadapter4);flagre4 = true;break;
                    case 3:break;
                    default:break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        spinrequest4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String items = spinrequest4.getSelectedItem().toString();
                int temp = Integer.parseInt(items);
                no[3] = temp;
                check3 = check2-temp;
                if(check2 == 0){
                    flagre5 = false;
                    editrequest5.setVisibility(View.GONE);
                    spinrequest5.setVisibility(View.GONE);
                }
                switch (check3){
                    case 1:editrequest5.setVisibility(View.VISIBLE); spinrequest5.setVisibility(View.VISIBLE); spinrequest5.setAdapter(numadapter5);flagre5 = true;break;
                    case 2:break;
                    default:break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        spinrequest5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String items = spinrequest5.getSelectedItem().toString();
                int temp = Integer.parseInt(items);
                no[4] = temp;

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab_submit_post);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(edittitle.getText().toString())) {
                    edittitle.setError("Required");
                } else {
                    edittitle.setError(null);
                    title = edittitle.getText().toString();
                }

                if (TextUtils.isEmpty(editabout.getText().toString())) {
                    editabout.setError("Required");
                } else {
                    editabout.setError(null);
                    about = editabout.getText().toString();
                }
                if (TextUtils.isEmpty(editdomain.getText().toString())) {
                    editdomain.setError("Required");
                } else {
                    editdomain.setError(null);
                    domain = editdomain.getText().toString();
                }
                if (TextUtils.isEmpty(editdepartment.getText().toString())) {
                    editdepartment.setError("Required");
                } else {
                    editdepartment.setError(null);
                    department = editdepartment.getText().toString();
                }
                if(TextUtils.isEmpty(title) || TextUtils.isEmpty(about) || TextUtils.isEmpty(domain) || TextUtils.isEmpty(department)){
                    Toast.makeText(New_project.this, "Enter all fields.", Toast.LENGTH_SHORT).show();
                }
                else{
                    createprojectProgress();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();
                    String email = user.getEmail();
                    String key = databaseReference.child("Projects").push().getKey();
                    DatabaseReference databaseReference2 = databaseReference.child("Projects").child(uid).child(key);
                    DatabaseReference databaseReference3 = databaseReference.child("All-Projects").child(key);
                    databaseReference2.child("title").setValue(title);
                    databaseReference3.child("title").setValue(title);
                    databaseReference2.child("uid").setValue(uid);
                    databaseReference3.child("uid").setValue(uid);
                    databaseReference2.child("email").setValue(email);
                    databaseReference3.child("email").setValue(email);
                    databaseReference2.child("about").setValue(about);
                    databaseReference3.child("about").setValue(about);
                    databaseReference2.child("domain").setValue(domain);
                    databaseReference3.child("domain").setValue(domain);
                    databaseReference2.child("department").setValue(department);
                    databaseReference3.child("department").setValue(department);
                    databaseReference2.child("members").setValue(number);
                    databaseReference3.child("members").setValue(number);
                    DatabaseReference databaseReference1 = databaseReference2.child("requirements");
                    DatabaseReference databaseReference4 = databaseReference3.child("requirements");
                    if(flagre1){
                        req1 = editrequest1.getText().toString();
                        databaseReference1.child(req1).setValue(no[0]);
                        databaseReference4.child(req1).setValue(no[0]);
                    }
                    if(flagre2){
                        req2 = editrequest2.getText().toString();
                        databaseReference1.child(req2).setValue(no[1]);
                        databaseReference4.child(req2).setValue(no[1]);
                    }
                    if(flagre3){
                        req3 = editrequest3.getText().toString();
                        databaseReference1.child(req3).setValue(no[2]);
                        databaseReference4.child(req3).setValue(no[2]);
                    }
                    if(flagre4){
                        req4 = editrequest4.getText().toString();
                        databaseReference1.child(req4).setValue(no[3]);
                        databaseReference4.child(req4).setValue(no[3]);
                    }
                    if(flagre5){
                        req5 = editrequest5.getText().toString();
                        databaseReference1.child(req5).setValue(no[4]);
                        databaseReference4.child(req5).setValue(no[4]);
                    }
                    hideProgressDialog();
                    Intent intent = new Intent(New_project.this, selectDep.class);
                    startActivity(intent);
                }







            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
