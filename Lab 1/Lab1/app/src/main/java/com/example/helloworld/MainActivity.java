package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{

    Button btn1;
    TextView txtView1;
    int clickCount = 0;

     @Override
    public void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         //retrieve the Button and Textview objects.
         btn1 = (Button)findViewById(R.id.btn1);
         txtView1 = (TextView) findViewById(R.id.txtView1);

         //register a listner for buttons clicks to be an anonymous
         //OnClickListener objects with the onClick event handler
         btn1.setOnClickListener(new OnClickListener() {
             public void onClick(View v) {
                 clickCount++;
                 txtView1.setText("" + clickCount);
             }
         });

     }


}