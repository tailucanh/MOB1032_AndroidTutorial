package vn.edu.poly.mob1032_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.mob1032_ass.DTO.ClassDTO;
import vn.edu.poly.mob1032_ass.Database.MyDbHelper;

public class ClassDAO {
    SQLiteDatabase sqLiteDatabas;
    MyDbHelper dbHelper;

    public ClassDAO(Context context){
        dbHelper = new MyDbHelper(context);
    }

    public void open(){
        sqLiteDatabas = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }
    //Hàm thêm class


    public long insertNew(ClassDTO classDTO){

        ContentValues contentValues = new ContentValues();
        contentValues.put("className", classDTO.getClassName());
        long res = sqLiteDatabas.insert("tb_Class", null, contentValues);

        return  res;
    }

    //Hàm xóa class
    public int deleteRow(ClassDTO classDTO){

        int res =sqLiteDatabas.delete("tb_Class","idClass = ?", new String[]{classDTO.getIdClass() +""});

        return  res;
    }

    //Hàm update
    public int updateRow(ClassDTO classDTO){

        ContentValues values= new ContentValues();
        values.put( "className",classDTO.getClassName()  );

        int res = sqLiteDatabas.update( "tb_Class", values,"idClass = ?", new String[] { classDTO.getIdClass() +"" } );
        return  res;
    }

    //Hàm lấy tất cả dữ liệu trong list
    public ArrayList<ClassDTO> selectAllClass(){
        ArrayList<ClassDTO> listClass = new ArrayList<>();
        //Lấy dữ liêu từ database
        String sql_Select = "SELECT * FROM tb_Class";
        //Thực thi

        Cursor cursor = sqLiteDatabas.rawQuery(sql_Select,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                ClassDTO classDTO = new ClassDTO();
                classDTO.setIdClass(cursor.getInt(0));
                classDTO.setClassName(cursor.getString(1));

                listClass.add(classDTO);

                cursor.moveToNext(); ///Phải cho vào while
            }
        }

        return listClass;
    }

    //Hàm lấy dữ liệu 1 dòng
    public ClassDTO selectOne(int id){
        ClassDTO classDTO = new ClassDTO();
        String[] dieu_kien = new String[]{ id + ""};
        // câu lệnh sql
        String sql = "SELECT idClass, className FROM tb_Class WHERE idClass = ? ";

        Cursor cursor = sqLiteDatabas.rawQuery(sql, dieu_kien);
        if (cursor.moveToFirst()) {
            // có dữ liệu
            classDTO = new ClassDTO();
            classDTO.setIdClass(cursor.getInt(0));
            classDTO.setClassName(cursor.getString(1));
          
        }
        return  classDTO;

    }


}
