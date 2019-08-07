package com.example.message;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "userdata.db";
    public static final String TABLE_NAME = "user";
    public static final int DB_VERSION = 1;
    public static final String NAME = "name";
    public static final String PWD = "pwd";
    public DBHelper (Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE "+TABLE_NAME+" (_id integer primary key autoincrement, "+
                    NAME+ " varchar, "+PWD+" varchar ) ";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
