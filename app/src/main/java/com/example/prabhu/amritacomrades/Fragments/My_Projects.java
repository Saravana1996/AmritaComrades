package com.example.prabhu.amritacomrades.Fragments;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


public class My_Projects extends PostListFragment {


    public My_Projects() {
        // Required empty public constructor
    }


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("Projects")
                .child(getUid());
    }
}
