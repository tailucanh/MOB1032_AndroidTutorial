package vn.edu.poly.exercise_mob1032.DATABASE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String  DB_NAME = "ListProduct";
    public static final int  DB_VERSION = 1;

    public MyDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE  tb_products (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,name TEXT NOT NULL , price NUMBER NOT NULL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
