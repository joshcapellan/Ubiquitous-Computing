package com.example.studentdb;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_STUDENT = "student";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MARKS = "marks";

    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_STUDENT + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY, " + COLUMN_NAME
            + " VARCHAR, " + COLUMN_MARKS
            + " VARCHAR );";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    public void addStudent(Student student) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_MARKS, student.getMarks());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public boolean deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        int nrows = db.delete(TABLE_STUDENT, COLUMN_ID + " = ?", new String[] {student.get_id()});
        db.close();
        return nrows != 0;
    }

    public boolean updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_MARKS, student.getMarks());
        int nrows = db.update(TABLE_STUDENT,values, COLUMN_ID + " = ?", new String[] {student.get_id()});
        db.close();
        return nrows != 0;
    }

    private Student cursorToStudent(Cursor cursor)
    {
        Student student = new Student();
        student.set_id(cursor.getString(0));
        student.setName(cursor.getString(1));
        student.setMarks(cursor.getString(2));
        return student;
    }

    public Student findStudent(String id) {
        String query = "Select * FROM " + TABLE_STUDENT + " WHERE " + COLUMN_ID + " =  \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student=null;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student = cursorToStudent(cursor);
            cursor.close();
        }
        db.close();
        return student;
    }

    public List<Student> findAllStudents() {
        List<Student> students = new ArrayList<Student>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_STUDENT,
                new String[]{COLUMN_ID,COLUMN_NAME,COLUMN_MARKS}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = cursorToStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        db.close();
        return students;
    }



}
