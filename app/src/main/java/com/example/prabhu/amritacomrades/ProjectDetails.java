package com.example.prabhu.amritacomrades;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProjectDetails extends AppCompatActivity {
    public static final String EXTRA_POST_KEY = "post_key";
    private static final String TAG = "ProjectDetails";
    private DatabaseReference mPostReference, mPostReference1, mnewRef, mUserRef;
    private FirebaseUser firebaseUser;
    private ValueEventListener mPostListener;
    private String num, value, uid1, email, cgpa, name, department, technical, key, uid, title,keyvalue;
    private TextView mdomainView;
    private TextView mTitleView;
    private TextView mBodyView;
    private RadioGroup radioGroup;
    private Button button;
    private int number;
    private String mPostKey;
    private RadioButton mrchoose1, mrchoose2, mrchoose3, mrchoose4, mrchoose5;
    private Button macceptButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createproject);

        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("All-Projects").child(mPostKey);
        uid = firebaseUser.getUid();
        //Make it asycn process at the end
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);



        mdomainView = (TextView) findViewById(R.id.post_domain);
        mTitleView = (TextView) findViewById(R.id.post_title);
        mBodyView = (TextView) findViewById(R.id.post_body);
        mrchoose1 = (RadioButton)findViewById(R.id.rshow1);
        mrchoose2 = (RadioButton)findViewById(R.id.rshow2);
        mrchoose3 = (RadioButton)findViewById(R.id.rshow3);
        mrchoose4 = (RadioButton)findViewById(R.id.rshow4);
        mrchoose5 = (RadioButton)findViewById(R.id.rshow5);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        button = (Button)findViewById(R.id.buttonaccept);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()            {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int selectedId) {
                button.setVisibility(View.VISIBLE);
                selectedId=radioGroup.getCheckedRadioButtonId();
                RadioButton numberchoosed = (RadioButton) findViewById(selectedId);
                value = numberchoosed.getText().toString();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = mnewRef.child(keyvalue);
                databaseReference.child("title").setValue(title);
                databaseReference.child("name").setValue(name);
                databaseReference.child("email").setValue(email);
                databaseReference.child("cgpa").setValue(cgpa);
                databaseReference.child("technical").setValue(technical);
                databaseReference.child("department").setValue(department);
                databaseReference.child("requesting").setValue(value);
                Intent intent = new Intent(ProjectDetails.this, selectDep.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Post post = dataSnapshot.getValue(Post.class);
                // [START_EXCLUDE]
                uid1 = post.uid;
                title = post.title;
                mnewRef = FirebaseDatabase.getInstance().getReference()
                        .child("Request").child(uid1);
                keyvalue = mnewRef.push().getKey();
                mdomainView.setText(post.domain);
                mTitleView.setText(post.title);
                mBodyView.setText(post.about);
                num = post.members;
                number = Integer.parseInt(num);
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(ProjectDetails.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };

        mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                name = dataSnapshot.child("Name").getValue().toString();
                email = dataSnapshot.child("Email").getValue().toString();
                department = dataSnapshot.child("Department").getValue().toString();
                cgpa = dataSnapshot.child("CGPA").getValue().toString();
                technical = dataSnapshot.child("Technical").getValue().toString();
                Log.e("E_VALUE", technical);
                Log.e("E_VALUE", department);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mPostReference.addValueEventListener(postListener);
        mPostReference1 = FirebaseDatabase.getInstance().getReference()
                .child("All-Projects").child(mPostKey).child("requirements");
        mPostReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> c = dataSnapshot.getChildren();
                int i = 0;
                for (DataSnapshot d:c){
                    String n = d.getValue().toString();
                    int nn = Integer.parseInt(n);
                    if(i == 0 && nn != 0){
                        mrchoose1.setText(d.getKey());
                        i++;
                    }
                    else if(i == 1 && nn != 0){
                        mrchoose2.setVisibility(View.VISIBLE);
                        mrchoose2.setText(d.getKey());
                        i++;
                    }
                    else if(i == 2 && nn != 0){
                        mrchoose3.setVisibility(View.VISIBLE);
                        mrchoose3.setText(d.getKey());
                        i++;
                    }
                    else if(i == 3 && nn != 0){
                        mrchoose4.setVisibility(View.VISIBLE);
                        mrchoose4.setText(d.getKey());
                        i++;
                    }
                    else if(i == 4 && nn != 0){
                        mrchoose5.setVisibility(View.VISIBLE);
                        mrchoose5.setText(d.getKey());
                        i++;
                    }
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;

    }
}
