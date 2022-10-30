package vn.edu.poly.mob1032_ass.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "StudentManagement";
    public static final int DB_VERSION = 2;


    public MyDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE  tb_Class (idClass INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,className TEXT NOT NULL UNIQUE);";
        db.execSQL(sql);


        String sql2 = "CREATE TABLE  tb_Students (idStudent INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,studentName TEXT NOT NULL UNIQUE,birthday TEXT ,numberPhone TEXT UNIQUE,email TEXT UNIQUE ,idClass INTEGER NOT NULL );";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
