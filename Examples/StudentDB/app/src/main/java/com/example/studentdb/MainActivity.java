package com.example.studentdb;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends Activity implements OnClickListener
{
    EditText editRollno,editName,editMarks;
    Button btnAdd,btnDelete,btnModify,btnView,btnViewAll,btnShowInfo;
    MySQLiteHelper dbHelper;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MySQLiteHelper(this);
        editRollno=(EditText)findViewById(R.id.editRollno);
        editName=(EditText)findViewById(R.id.editName);
        editMarks=(EditText)findViewById(R.id.editMarks);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnModify=(Button)findViewById(R.id.btnModify);
        btnView=(Button)findViewById(R.id.btnView);
        btnViewAll=(Button)findViewById(R.id.btnViewAll);
        btnShowInfo=(Button)findViewById(R.id.btnShowInfo);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShowInfo.setOnClickListener(this);
    }
    public void onClick(View view)
    {
        if(view==btnAdd)
        {
            if(editName.getText().toString().trim().length()==0||
                    editMarks.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            dbHelper.addStudent(new Student(editName.getText().toString(),editMarks.getText().toString()));
            showMessage("Success", "Record added");
            clearText();
        }
        if(view==btnDelete)
        {
            if(editRollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            if (!dbHelper.deleteStudent(new Student(editRollno.getText().toString().trim())))
            {
                showMessage("Error", "Invalid Rollno");
            }
            else
                showMessage("Success", "Deleted student: "+editRollno.getText().toString());
            clearText();
        }
        if(view==btnModify)
        {
            if( editRollno.getText().toString().trim().length()==0 ||
                editName.getText().toString().trim().length()==0||
                    editMarks.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            if (dbHelper.updateStudent(new Student(editRollno.getText().toString(),editName.getText().toString(),editMarks.getText().toString())))
            {
                showMessage("Success", "Record Modified");
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        if(view==btnView)
        {
            if(editRollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Student student = dbHelper.findStudent(editRollno.getText().toString());
            if(student != null)
            {
                editName.setText(student.getName());
                editMarks.setText(student.getMarks());
            }
            else
            {
                showMessage("Error", "Rollno does not exist!");
                clearText();
            }
        }
        if(view==btnViewAll)
        {
            List<Student> students = dbHelper.findAllStudents();
            if(students.isEmpty())
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            for(Student student : students)
            {
                buffer.append("Rollno: "+student.get_id()+"\n");
                buffer.append("Name: "+student.getName()+"\n");
                buffer.append("Marks: "+student.getMarks()+"\n\n");
            }

            showMessage("Student Details", buffer.toString());
        }
        if(view==btnShowInfo)
        {
            showMessage("Student Management Application", "Demonstrates SQLLite concepts!");
        }
    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        editRollno.setText("");
        editName.setText("");
        editMarks.setText("");
        editRollno.requestFocus();
    }
}