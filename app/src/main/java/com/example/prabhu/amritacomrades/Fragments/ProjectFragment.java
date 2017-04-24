package com.example.prabhu.amritacomrades.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prabhu.amritacomrades.Holder.InboxViewHolder;
import com.example.prabhu.amritacomrades.Holder.ProjectViewHolder;
import com.example.prabhu.amritacomrades.In;
import com.example.prabhu.amritacomrades.Post;
import com.example.prabhu.amritacomrades.ProjectDetails;
import com.example.prabhu.amritacomrades.R;
import com.example.prabhu.amritacomrades.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by PRABHU on 24-04-2017.
 */

public abstract class ProjectFragment extends Fragment {
    private static final String TAG = "ProjectFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]
    private ProgressDialog mProgressDialog;

    private FirebaseRecyclerAdapter<In, InboxViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public ProjectFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.projectfragment, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.messages_list1);
        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    public abstract Query getQuery(DatabaseReference databaseReference);


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
//                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Launch PostDetailActivity
//                        Intent intent = new Intent(getActivity(), ProjectDetails.class);
//                        intent.putExtra(ProjectDetails.EXTRA_POST_KEY, postKey);
//                        startActivity(intent);
//                    }
//                });
                viewHolder.bindToPost(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
