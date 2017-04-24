package com.example.prabhu.amritacomrades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class createproject extends AppCompatActivity {
    public static final String EXTRA_POST_KEY = "post_key";
    private DatabaseReference mPostReference;
    private ValueEventListener mPostListener;
    private TextView mdomainView;
    private TextView mTitleView;
    private TextView mBodyView;
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

        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("All-Projects").child(mPostKey);


    }
}
