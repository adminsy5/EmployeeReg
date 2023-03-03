package com.mpiyush3510.employeereg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DbName="Employee.db";

    public DbHelper( Context context) {
        super(context, DbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table emp(email text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists emp");
    }

    public boolean insertData(String email,String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result=sqLiteDatabase.insert("emp",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from emp where email=?",new String[]{email});
        if(cursor.getCount()>0){
            return true;
        }else
            return false;
    }

    public Boolean checkEmailPassword(String email,String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from emp where email=? and password =?",new String[]{email,password});
        if(cursor.getCount()>0){
            return true;
        }else
            return false;
    }
}
