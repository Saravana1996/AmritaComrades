package com.example.prabhu.amritacomrades.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ProgressDialog;
import android.widget.EditText;
import android.widget.LinearLayout;


import com.example.prabhu.amritacomrades.Holder.ProjectViewHolder;
import com.example.prabhu.amritacomrades.Inbox;
import com.example.prabhu.amritacomrades.Post;
import com.example.prabhu.amritacomrades.Profile;
import com.example.prabhu.amritacomrades.ProjectDetails;
import com.example.prabhu.amritacomrades.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;

/**
 * Created by PRABHU on 21-04-2017.
 */

public abstract class PostListFragment extends Fragment {

    private static final String TAG = "PostListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]
    private FirebaseUser firebaseUser;
    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder builder;
    private String uid;

    private FirebaseRecyclerAdapter<Post, ProjectViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public PostListFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.postlistfragment, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        builder = new AlertDialog.Builder(getActivity());
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading...Please Wait");

        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.messages_list);
        mRecycler.setHasFixedSize(true);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        try{
            uid = firebaseUser.getUid();
        }catch (Exception e){

        }


        return rootView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout

        mProgressDialog.show();

        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<Post, ProjectViewHolder>(Post.class, R.layout.item_post,
                ProjectViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(final ProjectViewHolder viewHolder, final Post model, final int position) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
                Log.e("E_Value", postKey);
                viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Log.e("E_VALUE", "LongPress");
                        builder.setTitle("Delete Project");
                        builder.setMessage("Are you sure to delete your project");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DatabaseReference databaseReference4 = FirebaseDatabase.getInstance().getReference().child("All-Projects").child(postKey);
                                databaseReference4.removeValue();
                                DatabaseReference databaseReference5 = FirebaseDatabase.getInstance().getReference().child("Projects").child(uid).child(postKey);
                                databaseReference5.removeValue();
                                databaseReference4 = FirebaseDatabase.getInstance().getReference().child("Request").child(model.uid).child(postKey);
                                databaseReference4.removeValue();
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        if(uid.equals(model.uid)){
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }

                        return false;
                    }
                });
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch PostDetailActivity
                        Log.e("E_VALUE", uid);
                        Log.e("E_VALUE", model.uid);
                        if(uid.equals(model.uid)){
                            Intent intent1 = new Intent(getActivity(), Inbox.class);
                            startActivity(intent1);
                        }
                        else{
                            Intent intent = new Intent(getActivity(), ProjectDetails.class);
                            intent.putExtra(ProjectDetails.EXTRA_POST_KEY, postKey);
                            startActivity(intent);
                        }

                    }
                });
                viewHolder.bindToPost(model);
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
