package com.example.prabhu.amritacomrades.Fragments;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by PRABHU on 24-04-2017.
 */

public class Inbox_Request extends ProjectFragment {


    public Inbox_Request() {
        // Required empty public constructor
    }


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("Request").child(getUid());
    }

}
