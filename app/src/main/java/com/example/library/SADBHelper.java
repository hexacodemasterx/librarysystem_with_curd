package com.example.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SADBHelper extends SQLiteOpenHelper {
    public SADBHelper(Context context) {
        super(context, "Userdata2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails2(mname TEXT primary key, bname TEXT, bissued TEXT, breturned TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails2");
    }

    public Boolean insertuserdata(String mname, String bname, String bissued, String breturned)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mname", mname);
        contentValues.put("bname", bname);
        contentValues.put("bissued", bissued);
        contentValues.put("breturned", breturned);
        long result = DB.insert("Userdetails2", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(String mname, String bname, String bissued, String breturned)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mname", mname);
        contentValues.put("bname", bname);
        contentValues.put("bissued", bissued);
        contentValues.put("breturned", breturned);
        Cursor cursor = DB.rawQuery("Select * from Userdetails2 where mname = ?", new String[]{mname});
        if(cursor.getCount()>0)
        {
            long result = DB.update("Userdetails2", contentValues, "mname=?", new String[]{mname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    public Boolean deletedata(String mname)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails2 where mname = ?", new String[]{mname});
        if(cursor.getCount()>0)
        {
            long result = DB.delete("Userdetails2", "mname=?", new String[]{mname});
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
        Cursor cursor = DB.rawQuery("Select * from Userdetails2", null);
        return cursor;
    }
}
