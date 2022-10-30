package vn.edu.poly.ph26495_th2.DpHelpe;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MydpHelpe extends SQLiteOpenHelper {
    static final String DB_NAME = "danhba";
    static final int DB_VERSION = 1;

    public MydpHelpe(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_Create_tblDanhba = "CREATE TABLE  tb_danhba (id INTEGER NOT NULL,name TEXT NOT NULL,sdt  NUMERIC NOT NULL ,ghichu TEXT,  PRIMARY KEY( id AUTOINCREMENT))";
        db.execSQL(sql_Create_tblDanhba);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
