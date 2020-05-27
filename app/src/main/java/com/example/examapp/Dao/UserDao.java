package com.example.examapp.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.examapp.DB.DBOpenHelper;
import com.example.examapp.Entity.UserEntity;

public class UserDao {

    DBOpenHelper helper;

    public UserDao(Context context) {
       helper = new DBOpenHelper(context);
    }

    //查询用户信息
    public UserEntity get(String username,String pwd)
    {
        UserEntity user = null;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.query("user",new String[]{"id","userName","userPwd"},"userName=? AND userPwd=?",new String[]{username,pwd},null,null,null);
        if (cs!=null && cs.moveToNext()){
            user = new UserEntity();
            int id = cs.getInt(cs.getColumnIndex("id"));
            String name = cs.getString(cs.getColumnIndex("userName"));
            String password = cs.getString(cs.getColumnIndex("userPwd"));

            user.setId(id);
            user.setUserName(name);
            user.setUserPWD(password);

        }
        return user;
    }

    public boolean delete(String username)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        int flag = db.delete("user","userName=?",new String[]{username});
        return flag!=0?true:false;
    }

}
