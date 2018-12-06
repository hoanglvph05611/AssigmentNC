package com.hoang.lvhco.assigmentnc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoang.lvhco.assigmentnc.db.DataBaseQL;
import com.hoang.lvhco.assigmentnc.model.CheckTest;

import java.util.ArrayList;
import java.util.List;


public class TestDAO {
    private SQLiteDatabase db;
    private DataBaseQL dbql;
    public TestDAO(Context context){
        dbql = new DataBaseQL(context);
        db = dbql.getWritableDatabase();
    }

    public List<CheckTest> getPr(String sql, String...selectionArgs){
        List<CheckTest> list = new ArrayList<>();
        Cursor c =db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            CheckTest pr = new CheckTest();
            pr.idTest = c.getInt(c.getColumnIndex("ID"));
            pr.dateTest = c.getString(c.getColumnIndex("DATE"));
            pr.subjectTest = c.getString(c.getColumnIndex("SUBJECT"));
            list.add(pr);
        }
        return list;
    }
    public List<CheckTest> getTestAll(){
        String sql = "SELECT * FROM TEST";
        return getPr(sql);
    }

    public long insert(CheckTest p){
        ContentValues values = new ContentValues();
        values.put("DATE",p.dateTest);
        values.put("SUBJECT",p.subjectTest);
        return db.insert("TEST",null,values);
    }
    public int update(CheckTest p){
        ContentValues values = new ContentValues();
        values.put("DATE",p.dateTest);
        values.put("SUBJECT",p.subjectTest);
        return db.update("TEST",values,"ID=?",new String[]{String.valueOf(p.idTest)});
    }
    public int delete(int id){
        return db.delete("TEST","ID=?",new String[]{String.valueOf(id)});
    }
}
