package com.spx.crudlopsinhvien.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDbSinhVien extends SQLiteOpenHelper {
    public static final String DB_NAME = "CRUDLopSinhVien.db";
    public static final int DB_VERSION  = 1;

    public CreateDbSinhVien( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_cr_tb_lop = "CREATE TABLE tb_lop (id_lop INTEGER NOT NULL, ten_lop TEXT NOT NULL, nganh TEXT, PRIMARY KEY (id_lop AUTOINCREMENT))";
        db.execSQL(sql_cr_tb_lop);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
