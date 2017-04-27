package com.example.prabhu.amritacomrades.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.prabhu.amritacomrades.Holder.InboxViewHolder;
import com.example.prabhu.amritacomrades.Holder.ProjectViewHolder;
import com.example.prabhu.amritacomrades.In;
import com.example.prabhu.amritacomrades.Inbox;
import com.example.prabhu.amritacomrades.Post;
import com.example.prabhu.amritacomrades.ProjectDetails;
import com.example.prabhu.amritacomrades.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by PRABHU on 24-04-2017.
 */

public abstract class ProjectFragment extends Fragment {
    private static final String TAG = "ProjectFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase, myUserReference;
    // [END define_database_reference]
    private ProgressDialog mProgressDialog;
    private String name, department, semester, uid;

    private FirebaseRecyclerAdapter<In, InboxViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public ProjectFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading...Please Wait");
        mProgressDialog.show();
        View rootView = inflater.inflate(R.layout.projectfragment, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        try{
            uid = firebaseUser.getUid();
            myUserReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        }
        catch (Exception e){

        }

        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("Name").getValue().toString();
                department = dataSnapshot.child("Department").getValue().toString();
                semester = dataSnapshot.child("Semester").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRecycler = (RecyclerView) rootView.findViewById(R.id.messages_list1);
        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout

        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<In, InboxViewHolder>(In.class, R.layout.item_inbox,
                InboxViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(final InboxViewHolder viewHolder, final In model, final int position) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
                viewHolder.acceptbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //DatabaseReference databaseReference8 = FirebaseDatabase
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setData(Uri.parse("To:"));
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {model.email});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Confirmation of Acceptance");
                        //intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                        intent.putExtra(Intent.EXTRA_TEXT, "Hello, " + model.name + '\n' + "     I am " + name + " from " + department + ", semester " + semester+" would like to work with you on \""+model.title+"\". Contact me for further details.\n\nMobile number:");
                        intent.setType("message/rfc822");
                        Intent chooser = Intent.createChooser(intent, "Send email");
                        startActivity(chooser);
                    }
                });
                viewHolder.declinebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Request").child(uid).child(postKey);
                        ref.removeValue();

                    }
                });
//                Log.e("E_VALUE", model.department);
                try{
                    viewHolder.titleView.setText(model.title);
                    viewHolder.nameView.setText("Requeted by "+model.name);
                    viewHolder.cgpaView.setText("CGPA : "+model.cgpa);
                    viewHolder.technicalView.setText("Technically strong in - "+model.technical);
                    viewHolder.departmentView.setText("Department : "+model.department);
                    viewHolder.requestingView.setText("Requesting for - "+model.requesting);
                }
                catch (Exception e){

                }
                mProgressDialog.dismiss();

            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public String getUid() {
         return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);
}
