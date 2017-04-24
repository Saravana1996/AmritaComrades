package com.example.prabhu.amritacomrades;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Faculty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbarfaculty);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        WebView wv=(WebView)findViewById(R.id.wview);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());

        Bundle b=getIntent().getExtras();
        String dept=b.getString("dept");
        if(dept.equalsIgnoreCase("Computer Science Engineering")){
            wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=101&field_center_name_tid=All");
        }
        if(dept.equalsIgnoreCase("Electrical and Electronics Engineering")){
            wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=103&field_center_name_tid=All");
        }
        if(dept.equalsIgnoreCase("Electronics and Communication Engineering")){
            wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=102&field_center_name_tid=All");
        }
        if(dept.equalsIgnoreCase("Mechanical Engineering")){
            wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=104&field_center_name_tid=All");
        }
        if(dept.equalsIgnoreCase("Aerospace Engineering")){
            wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=137&field_center_name_tid=All");
        }
        if(dept.equalsIgnoreCase("Civil Engineering")){
            wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=138&field_center_name_tid=All");
        }
        if(dept.equalsIgnoreCase("Chemical Engineering")){
            wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=147&field_center_name_tid=All");
        }
        if(dept.equalsIgnoreCase("Electronics and Instrumentation Engineering")){
            wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=101&field_center_name_tid=All");
        }

//        wv.setWebViewClient(new WebViewClient() {
//            @SuppressLint("NewApi")
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//            }
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//            }
//        });



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
