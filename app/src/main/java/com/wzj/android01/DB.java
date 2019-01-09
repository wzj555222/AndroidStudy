package com.wzj.android01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DB extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String NAME="myDB";

    public DB( Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DB(Context context, String name,int version)
    {
        this(context, name, null, version);
    }
    public DB(Context context, String name)
    {
        this(context, name,VERSION);
    }
    public DB( Context context) {
        this(context,NAME);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username VARCHAR(20),password VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}














