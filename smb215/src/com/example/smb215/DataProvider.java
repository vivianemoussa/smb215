package com.example.smb215;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
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
    public static final Uri CONTENT_URI_MESSAGES = Uri.parse("content://com.example.smb215.provider/messages");
    public static final Uri CONTENT_URI_PROFILE = Uri.parse("content://com.example.smb215.provider/profile");
     
    private static final int MESSAGES_ALLROWS = 1;
    private static final int MESSAGES_SINGLE_ROW = 2;
    private static final int PROFILE_ALLROWS = 3;
    private static final int PROFILE_SINGLE_ROW = 4;
    private static final UriMatcher uriMatcher;
 
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
   
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.smb215.provider", "messages", MESSAGES_ALLROWS);
        uriMatcher.addURI("com.example.smb215.provider", "messages/#", MESSAGES_SINGLE_ROW);
        uriMatcher.addURI("com.example.smb215.provider", "profile", PROFILE_ALLROWS);
        uriMatcher.addURI("com.example.smb215.provider", "profile/#", PROFILE_SINGLE_ROW);
    }
    
    public Cursor query1(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        android.database.sqlite.SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
         
        switch(uriMatcher.match(uri)) {
        case MESSAGES_ALLROWS:
        case PROFILE_ALLROWS:
            qb.setTables(getTableName(uri));
            break;
             
        case MESSAGES_SINGLE_ROW:
        case PROFILE_SINGLE_ROW:
            qb.setTables(getTableName(uri));
            qb.appendWhere("_id = " + uri.getLastPathSegment());
            break;
             
        default:
            throw new IllegalArgumentException("Unsupported URI: " + uri);         
        }
         
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
     
    public Uri insert1 (Uri uri, ContentValues values) {
        android.database.sqlite.SQLiteDatabase db = dbHelper.getWritableDatabase();
         
        long id;
        switch(uriMatcher.match(uri)) {
        case MESSAGES_ALLROWS:
            id = db.insertOrThrow(TABLE_MESSAGES, null, values);
            if (values.get(COL_TO) == null) {
                db.execSQL("update profile set count=count+1 where email = ?", new Object[]{values.get(COL_FROM)});
                getContext().getContentResolver().notifyChange(CONTENT_URI_PROFILE, null);
            }
            break;
             
        case PROFILE_ALLROWS:
            id = db.insertOrThrow(TABLE_PROFILE, null, values);
            break;
             
        default:
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
         
        Uri insertUri = ContentUris.withAppendedId(uri, id);
        getContext().getContentResolver().notifyChange(insertUri, null);
        return insertUri;
    }
     
    public int update1(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        android.database.sqlite.SQLiteDatabase db = dbHelper.getWritableDatabase();
         
        int count;
        switch(uriMatcher.match(uri)) {
        case MESSAGES_ALLROWS:
        case PROFILE_ALLROWS:
            count = db.update(getTableName(uri), values, selection, selectionArgs);
            break;
             
        case MESSAGES_SINGLE_ROW:
        case PROFILE_SINGLE_ROW:
            count = db.update(getTableName(uri), values, "_id = ?", new String[]{uri.getLastPathSegment()});
            break;
             
        default:
            throw new IllegalArgumentException("Unsupported URI: " + uri);         
        }
         
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
     
    public int delete1(Uri uri, String selection, String[] selectionArgs) {
        android.database.sqlite.SQLiteDatabase db = dbHelper.getWritableDatabase();
         
        int count;
        switch(uriMatcher.match(uri)) {
        case MESSAGES_ALLROWS:
        case PROFILE_ALLROWS:
            count = db.delete(getTableName(uri), selection, selectionArgs);
            break;
             
        case MESSAGES_SINGLE_ROW:
        case PROFILE_SINGLE_ROW:
            count = db.delete(getTableName(uri), "_id = ?", new String[]{uri.getLastPathSegment()});
            break;
             
        default:
            throw new IllegalArgumentException("Unsupported URI: " + uri);         
        }
         
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
     
    private String getTableName(Uri uri) {
        switch(uriMatcher.match(uri)) {
        case MESSAGES_ALLROWS:
        case MESSAGES_SINGLE_ROW:
            return TABLE_MESSAGES;
             
        case PROFILE_ALLROWS:
        case PROFILE_SINGLE_ROW:
            return TABLE_PROFILE;          
        }
        return null;
    }
        
    
       
}