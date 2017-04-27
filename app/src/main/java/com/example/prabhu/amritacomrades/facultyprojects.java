package com.example.prabhu.amritacomrades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class facultyprojects extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyprojects);
        Toolbar tb=(Toolbar)findViewById(R.id.toolbarfacultyprojects);
        setSupportActionBar(tb);
        Spinner spinner = (Spinner) findViewById(R.id.fspinner);
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.Departments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Toast.makeText(getApplication(),"Please select Department again",Toast.LENGTH_SHORT).show();
                }
                else{
                    WebView wv=(WebView)findViewById(R.id.wview);
                    WebSettings webSettings = wv.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    wv.setWebViewClient(new WebViewClient());

                    if(position==4){
                        wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=101&field_center_name_tid=All");
                    }
                    if(position==5){
                        wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=103&field_center_name_tid=All");
                    }
                    if(position==6){
                        wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=102&field_center_name_tid=All");
                    }
                    if(position==8){
                        wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=104&field_center_name_tid=All");
                    }
                    if(position==1){
                        wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=137&field_center_name_tid=All");
                    }
                    if(position==2){
                        wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=138&field_center_name_tid=All");
                    }
                    if(position==3){
                        wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=147&field_center_name_tid=All");
                    }
                    if(position==7){
                        wv.loadUrl("https://www.amrita.edu/faculty?field_faculty_department_tid=38&field_faculty_campus_tid=53&field_faculty_designation_tid=All&field_faculty_department_main_tid=101&field_center_name_tid=All");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}
