package com.example.prabhu.amritacomrades;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private GestureDetectorCompat obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obj=new GestureDetectorCompat(this,new nextact());
    }
@Override
public boolean onTouchEvent(MotionEvent e1){
    this.obj.onTouchEvent(e1);
    return super.onTouchEvent(e1);
}
    class nextact extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX, float velocityY){
            if(e2.getX()<e1.getX()){
                Intent intent=new Intent(MainActivity.this,Homepage.class);
                startActivity(intent);
            }
            return true;
        }
    }
}
