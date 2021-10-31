package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateMenuListActivity extends AppCompatActivity implements View.OnClickListener {

    TextView currentList, listNo, listName, listContent;
    EditText editListNo, editListName, editListContent ;
    Button createButton, editButton, viewButton, viewAllButton;
    MySQLiteHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu_list);
        dbHelper = new MySQLiteHelper(this);
        currentList = (TextView) findViewById(R.id.currList);
        listNo = (TextView) findViewById(R.id.textListNo);
        listName = (TextView) findViewById(R.id.textName);
        listContent = (TextView) findViewById(R.id.textList);
        editListNo = (EditText) findViewById(R.id.editTextListNo);
        editListName = (EditText) findViewById((R.id.editTextName));
        editListContent = (EditText) findViewById(R.id.editTextList);
        createButton = (Button) findViewById(R.id.createButton);
        editButton = (Button) findViewById(R.id.editButton);
        viewButton = (Button) findViewById(R.id.viewButton);
        viewAllButton = (Button) findViewById(R.id.viewAllButton);

        createButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
        viewButton.setOnClickListener(this);
        viewAllButton.setOnClickListener(this);

    }

    public void onClick(View view)
    {
        if(view==createButton)
        {
            if(editListName.getText().toString().trim().length()==0||
                    editListContent.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            dbHelper.addList(new List(editListName.getText().toString(),editListContent.getText().toString()));
            showMessage("Success", "Record added");
            clearText();
        }

        if(view==editButton)
        {
            if( editListNo.getText().toString().trim().length()==0 ||
                    editListName.getText().toString().trim().length()==0||
                    editListContent.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            if (dbHelper.updateList(new List(editListNo.getText().toString(),editListName.getText().toString(),editListContent.getText().toString())))
            {
                showMessage("Success", "Record Modified");
            }
            else
            {
                showMessage("Error", "Invalid ListNo");
            }
            clearText();
        }

        if(view==viewButton)
        {
            if(editListNo.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter ListNo");
                return;
            }
            List list = dbHelper.findList(editListNo.getText().toString());
            if(list != null)
            {
                editListName.setText(list.getName());
                editListContent.setText(list.getList());
            }
            else
            {
                showMessage("Error", "ListNo does not exist!");
                clearText();
            }
        }

        if(view==viewAllButton)
        {
            java.util.List<List> lists = dbHelper.findAllList();
            if(lists.isEmpty())
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            for(List list : lists)
            {
                buffer.append("ListNo: "+list.get_id()+"\n");
                buffer.append("Name: "+list.getName()+"\n");
                buffer.append("Content: "+list.getList()+"\n\n");
            }

            showMessage("List Details", buffer.toString());
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
    public void clearText()
    {
        editListNo.setText("");
        editListName.setText("");
        editListContent.setText("");
        editListNo.requestFocus();
    }
}

