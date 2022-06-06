package cn.edu.sict.lc.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UserAccount {
    SQLiteDatabase db;

    public UserAccount(Context context) {
        db = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().toString()+"/users.db",null);
        db.execSQL("create table if not exists users(name varchar(12),password varchar(16),primary key(name))");
    }
    //定义getter和setter方法
    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
}
