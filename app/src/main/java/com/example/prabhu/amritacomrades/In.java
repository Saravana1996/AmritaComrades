package com.example.prabhu.amritacomrades;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Boolean> stars = new HashMap<>();
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

//    @Exclude
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("title", title);
//        result.put("name", name);
//        result.put("email", email);
//        result.put("cgpa", cgpa);
//        result.put("technical", technical);
//        result.put("department", department);
//        result.put("requesting", requesting);
//
//
//        return result;
//    }
}