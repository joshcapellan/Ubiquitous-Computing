package com.example.lab3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_List = "List";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_list = "list";

    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_List + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY, " + COLUMN_NAME
            + " VARCHAR, " + COLUMN_list
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_List);
        onCreate(db);
    }

    public void addList(com.example.lab3.List list) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, list.getName());
        values.put(COLUMN_list, list.getList());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_List, null, values);
        db.close();
    }


    public boolean updateList(com.example.lab3.List list) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, list.getName());
        values.put(COLUMN_list, list.getList());
        int nrows = db.update(TABLE_List,values, COLUMN_ID + " = ?", new String[] {list.get_id()});
        db.close();
        return nrows != 0;
    }

    private List cursorToList(Cursor cursor)
    {
        List list = new List();
        list.set_id(cursor.getString(0));
        list.setName(cursor.getString(1));
        list.setList(cursor.getString(2));
        return list;
    }

    public List findList(String id) {
        String query = "Select * FROM " + TABLE_List + " WHERE " + COLUMN_ID + " =  \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List list=null;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            list = cursorToList(cursor);
            cursor.close();
        }
        db.close();
        return list;
    }

    public java.util.List<List> findAllList() {
        java.util.List<List> lists = new ArrayList<List>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_List,
                new String[]{COLUMN_ID,COLUMN_NAME,COLUMN_list}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            List list = cursorToList(cursor);
            lists.add(list);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        db.close();
        return lists;
    }


}