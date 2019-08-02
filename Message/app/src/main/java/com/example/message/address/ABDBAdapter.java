package com.example.message.address;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ABDBAdapter {

    private static DBHelper dbHelper;
    public ABDBAdapter(Context context, String name, CursorFactory factory, int version) {
        dbHelper = new DBHelper(context, name, factory, version);
    }

    static class DBHelper extends SQLiteOpenHelper{

        public static final String TABLE = "table1";
        public static final String ID = "_id";
        public static final String FIRSTNAME = "firstName";
        public static final String LASTNAME = "lastName";
        public static final String PHONE = "phone";
        public static final String PHONE_TWO = "phoneTwo";
        public static final String EMAIL = "email";
        public static final String EMAIL_TWO = "emailTwo";
        public static final String ADDRESS = "address";
        public static final String IMAGE = "image";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                FIRSTNAME + " VARCHAR(128) NOT NULL," + LASTNAME + " VARCHAR(128) NOT NULL," + PHONE + " VARCHAR(30) ," + PHONE_TWO + " VARCHAR(30) ," + EMAIL + " VARCHAR(30) ," + EMAIL_TWO + " VARCHAR(30) ," + ADDRESS + " VARCHAR(50) ," + IMAGE + " BLOB" + ");";


        public static final String DROP_TABLE = "drop table if exists table1";

        public DBHelper(Context context, String name, CursorFactory factory, int version){
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }


    }
}
