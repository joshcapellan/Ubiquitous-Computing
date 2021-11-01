package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MySQLiteHelper dbHelper;
    TextView textView;
    Button createButton, editButton;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MySQLiteHelper(this);
        textView = (TextView)findViewById(R.id.textView);
        createButton = (Button) findViewById(R.id.createButton);
        editButton = (Button) findViewById(R.id.editButton);
        userList = findViewById(R.id.user_list);

        listItem = new ArrayList<>();


        createButton.setOnClickListener(this);
        editButton.setOnClickListener(this);

        viewData();

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = userList.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, ""+ text, Toast.LENGTH_SHORT). show();
            }
        });

    }

    private void viewData() {
        Cursor cursor = dbHelper.viewData();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1)); //index 0 is id while 1 is the name fo the list
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem);
            userList.setAdapter(adapter);


        }
    }

    public void onClick(View view)
    {

        if(view==createButton)
        {
            Intent createMenu = new Intent(this, CreateMenuListActivity.class);
            startActivity(createMenu);
        }
        if(view==editButton)
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