package com.example.prabhu.amritacomrades;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by PRABHU on 23-04-2017.
 */

@IgnoreExtraProperties
public class User {
    public String name;
    public String semester;
    public String cgpa;
    public String department;
    public String email;
    public String uid;
    public String technical;

    //public Map<String, Boolean> stars = new HashMap<>();
    public User() {

    }

    public User(String name, String email, String cgpa, String department, String semester, String technical, String uid) {
        this.name = name;
        this.semester= semester;
        this.cgpa = cgpa;
        this.department = department;
        this.email = email;
        this.uid = uid;
        this.technical = technical;
    }
}