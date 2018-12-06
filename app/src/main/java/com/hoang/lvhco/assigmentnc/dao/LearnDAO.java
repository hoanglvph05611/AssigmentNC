package com.hoang.lvhco.assigmentnc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoang.lvhco.assigmentnc.db.DataBaseQL;
import com.hoang.lvhco.assigmentnc.model.SignupLearn;

import java.util.ArrayList;
import java.util.List;


public class LearnDAO {
    private SQLiteDatabase db;
    private DataBaseQL dbql;
    public LearnDAO(Context context){
        dbql = new DataBaseQL(context);
        db = dbql.getWritableDatabase();
    }

    public List<SignupLearn> getPr(String sql, String...selectionArgs){
        List<SignupLearn> list = new ArrayList<>();
        Cursor c =db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            SignupLearn pr = new SignupLearn();
            pr.id = c.getInt(c.getColumnIndex("ID"));
            pr.stname = c.getString(c.getColumnIndex("NAME"));
            pr.stclass = c.getString(c.getColumnIndex("MCLASS"));
            pr.stcode = c.getString(c.getColumnIndex("SID"));
            pr.stsubject = c.getString(c.getColumnIndex("SUBJECT"));
            list.add(pr);
        }
        return list;
    }
    public List<SignupLearn> getLearnAll(){
        String sql = "SELECT * FROM SIGNUPLEARN";
        return getPr(sql);
    }

    public long insert(SignupLearn p){
        ContentValues values = new ContentValues();
        values.put("NAME",p.stname);
        values.put("MCLASS",p.stclass);
        values.put("SID",p.stcode);
        values.put("SUBJECT",p.stsubject);
        return db.insert("SIGNUPLEARN",null,values);
    }
    public int update(SignupLearn p){
        ContentValues values = new ContentValues();
        values.put("NAME",p.stname);
        values.put("MCLASS",p.stclass);
        values.put("SID",p.stcode);
        values.put("SUBJECT",p.stsubject);
        return db.update("SIGNUPLEARN",values,"ID=?",new String[]{String.valueOf(p.id)});
    }
    public int delete(int id){
        return db.delete("SIGNUPLEARN","ID=?",new String[]{String.valueOf(id)});
    }
}
