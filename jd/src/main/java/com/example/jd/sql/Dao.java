package com.example.jd.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    private MHelper mHelper;

    public Dao(Context context) {
        mHelper = new MHelper(context);
    }

    public void addData(String data) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("data", data);
        db.insert("lishi", null, values);
    }

    public List<String> queryData() {
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor query = db.rawQuery("select * from lishi", null);
        //是否有下一行
        while (query.moveToNext()) {
            //获取当前行中每个列的值
            String string = query.getString(1);
            list.add(string);
        }
        return list;
    }

    public void deleteData() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete("lishi", null, null);

    }
}
