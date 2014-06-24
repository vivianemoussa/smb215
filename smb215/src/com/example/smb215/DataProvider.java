package com.example.smb215;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class DataProvider extends ContentProvider {
	 
    public static final String COL_ID = "_id";
     
    public static final String TABLE_MESSAGES = "messages";
    public static final String COL_MSG = "msg";
    public static final String COL_FROM = "email";
    public static final String COL_TO = "email2";
    public static final String COL_AT = "at";
     
    public static final String TABLE_PROFILE = "profile";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_COUNT = "count";                
                     
    private DbHelper dbHelper;
 
    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }
 
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }
 
    @Override
    public String getType(Uri uri) {
        return null;
    }
 
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }
 
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }
 
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}