package vn.edu.poly.demo10_6_androidcoban.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper  extends SQLiteOpenHelper {
    static final String DB_NAME = "QLSV";
    static final int DB_VERSION = 1;

    public MyDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String slq_nhanvien = "CREATE TABLE tb_NhanVien ( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,userName TEXT NOT NULL UNIQUE,passWord TEXT NOT NULL UNIQUE,fullName TEXT NOT NULL,birthday TEXT,salary NUMERIC NOT NULL DEFAULT 0)";
        db.execSQL(slq_nhanvien);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
