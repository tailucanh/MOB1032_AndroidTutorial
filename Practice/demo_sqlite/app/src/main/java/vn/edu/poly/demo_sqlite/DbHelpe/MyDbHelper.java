package vn.edu.poly.demo_sqlite.DbHelpe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    static  final  String DB_NAME = "CSLD_Products";
    static final  int DB_VERSION = 1;  //Chạy lại phải thay đổi
    //Viết hàm khởi tạo( tự viết)

    public MyDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //Chạy lần đâu gọi làm này.Lần sau k gọi đến
        //Viết lệnh tạo bảng SQL
        String sql_Create_tblCat = "CREATE TABLE  tb_cat (id INTEGER NOT NULL,name TEXT NOT NULL,PRIMARY KEY( id AUTOINCREMENT))";
        db.execSQL(sql_Create_tblCat);


        //Chèn thêm 1 số bảng ghi mẫu
        String sql_Insert_tblCat = "INSERT INTO tb_cat (name) VALUES ('Điện thoại'),('laptop'),('Tivi');";
        db.execSQL(sql_Insert_tblCat);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            String sql_Create_tblProduct = "CREATE TABLE  tb_Products (id  INTEGER NOT NULL,name  TEXT NOT NULL,price  NUMERIC NOT NULL DEFAULT 0,img_res  INTEGER NOT NULL,id_cat  INTEGER,PRIMARY KEY( id  AUTOINCREMENT))";
            db.execSQL(sql_Create_tblProduct);

            String sql_Insert_Product = "INSERT INTO tb_Products (name,price,img_res,id_cat)  VALUES ('Samsung'),(1000000),(1),(1)";
            db.execSQL(sql_Insert_Product);

    }
}
