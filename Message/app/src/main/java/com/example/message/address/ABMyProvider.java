package com.example.message.address;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ABMyProvider extends ContentProvider {
    public ABMyProvider () {}

    private String table = "table1";
    private static final String DBNAME = "table1";
    private static ABDBAdapter.DBHelper dbHelper;

    @Override
    public String getType(Uri uri) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert(DBNAME, null, values);

        db.close();

        Uri dbUri = ContentUris.withAppendedId(uri, id);
        return dbUri;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new ABDBAdapter.DBHelper(getContext(), DBNAME, null, 13);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(table, projection, selection, null, null, null, null);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.update(table, values, selection, selectionArgs);
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete(table, selection, selectionArgs);
        return count;
    }
}
