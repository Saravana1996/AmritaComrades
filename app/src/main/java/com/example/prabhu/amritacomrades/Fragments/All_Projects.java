package com.example.prabhu.amritacomrades.Fragments;


import android.support.v4.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class All_Projects extends PostListFragment {


    public All_Projects() {
        // Required empty public constructor
    }


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("All-Projects")
                .limitToFirst(30);
    }

}
