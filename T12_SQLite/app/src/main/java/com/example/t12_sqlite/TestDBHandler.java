package com.example.t12_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TestDBHandler {
    TestSQLiteOpenHelper helper;

    public TestDBHandler(Context context) {
        helper = new TestSQLiteOpenHelper(context, "people.db", null, 1);
    }

    public void insert(String name, int age, String address){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("address", address);
        db.insert("student", null, values);
    }

    public void delete(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("student", "name = ?",
                new String[]{name});
    }
}
