package com.example.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {
    public DBHelper2(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(bname TEXT primary key, bauthor TEXT, bpublisher TEXT, bcopy TEXT, bbranch TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String bname, String bauthor, String bpublisher, String bcopy, String bbranch)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bname", bname);
        contentValues.put("bauthor", bauthor);
        contentValues.put("bpublisher", bpublisher);
        contentValues.put("bcopy", bcopy);
        contentValues.put("bbranch", bbranch);
        long result = DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(String bname, String bauthor, String bpublisher, String bcopy, String bbranch)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bauthor", bauthor);
        contentValues.put("bpublisher", bpublisher);
        contentValues.put("bcopy", bcopy);
        contentValues.put("bbranch", bbranch);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where bname = ?", new String[]{bname});
        if(cursor.getCount()>0)
        {
            long result = DB.update("Userdetails", contentValues, "bname=?", new String[]{bname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    public Boolean deletedata(String bname)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where bname = ?", new String[]{bname});
        if(cursor.getCount()>0)
        {
            long result = DB.delete("Userdetails", "bname=?", new String[]{bname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}
