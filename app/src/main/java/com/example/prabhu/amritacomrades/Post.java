package com.example.prabhu.amritacomrades;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Post {
    public String title;
    public String about;
    public String domain;
    public String department;
    public String email;
    public String uid;
    public String members;
    //public Map<String, Boolean> stars = new HashMap<>();
    public Post(){

    }

    public Post(String Title, String about, String Domain, String department, String Email, String Uid, String members){
        this.title = Title;
        this.about = about;
        this.domain = Domain;
        this.department = department;
        this.email = Email;
        this.uid = Uid;
        this.members = members;
    }

//    @Exclude
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("uid", uid);
//        result.put("discription", about);
//        result.put("title", title);
//        result.put("teamnum", teamnum);
//        result.put("email", email);
//        result.put("domain", domain);
//
//        return result;
//    }
}
