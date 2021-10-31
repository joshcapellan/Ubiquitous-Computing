package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MySQLiteHelper dbHelper;
    TextView textView;
    Button createButton, editButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MySQLiteHelper(this);
        textView = (TextView)findViewById(R.id.textView);
        createButton = (Button) findViewById(R.id.createButton);
        editButton = (Button) findViewById(R.id.editButton);

    }

    public void onClick(View view)
    {

        if(view==createButton)
        {
            Intent createMenu = new Intent(this, CreateMenuListActivity.class);
            startActivity(createMenu);
        }

    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}