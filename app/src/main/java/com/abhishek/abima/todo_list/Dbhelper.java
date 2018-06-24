package com.abhishek.abima.todo_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class Dbhelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TODO_LIST";
    private static final String DB_TABLE = "TASK_LIST";
    private static final String DB_COLUMN = "TASK";
    private static final int DB_VERSION = 1;


    public Dbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL)",DB_TABLE,DB_COLUMN);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = String.format("DELETE TABLE IF EXIST %s",DB_COLUMN);
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public void insertValues(String values){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_COLUMN,values);
        db.insert(DB_TABLE,null,contentValues);
        db.close();
    }

    public void deleteValues(String values){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE,DB_COLUMN + "=?",new String[]{values});
        db.close();
    }

    public ArrayList<String> getTaskList(){
        ArrayList<String> valuesList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(DB_TABLE,new String[]{DB_COLUMN},null,null,null,null,null);
        while (cursor.moveToNext())
        {
            int index = cursor.getColumnIndex(DB_COLUMN);
            valuesList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return valuesList;
    }

}






















