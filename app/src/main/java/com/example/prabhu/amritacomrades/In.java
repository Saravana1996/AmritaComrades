package com.example.prabhu.amritacomrades;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by PRABHU on 23-04-2017.
 */

@IgnoreExtraProperties
public class In {
    public String name;
    public String cgpa;
    public String requesting;
    public String department;
    public String email;
    public String technical;
    public String title;

    //public Map<String, Boolean> stars = new HashMap<>();
    public In() {

    }

    public In(String title,String name, String email, String cgpa, String technical, String department, String requesting) {
        this.title = title;
        this.name = name;
        this.email= email;
        this.cgpa = cgpa;
        this.technical = technical;
        this.department = department;
        this.requesting = requesting;
    }
}