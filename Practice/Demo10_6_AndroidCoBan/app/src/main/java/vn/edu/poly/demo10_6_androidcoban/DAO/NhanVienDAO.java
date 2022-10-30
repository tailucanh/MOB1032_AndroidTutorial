package vn.edu.poly.demo10_6_androidcoban.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import vn.edu.poly.demo10_6_androidcoban.DTO.NhanVienDTO;
import vn.edu.poly.demo10_6_androidcoban.Database.MyDbHelper;

public class NhanVienDAO {
    SQLiteDatabase db;
    MyDbHelper myDbHelper;


    public NhanVienDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }

    public long insertNew(NhanVienDTO nhanVienDTO){
        ContentValues values = new ContentValues();
        values.put("userName",nhanVienDTO.getUserName());
        values.put("passWord",nhanVienDTO.getPassWord());
        values.put("fullName",nhanVienDTO.getFullName());
        values.put("birthday",nhanVienDTO.getBirthday());
        values.put("salary",nhanVienDTO.getSalary());

        return  db.insert("tb_NhanVien",null,values);

    }


    public int updateRow( NhanVienDTO nhanVienDTO){
        ContentValues values = new ContentValues();
        values.put("userName",nhanVienDTO.getUserName());
        values.put("passWord",nhanVienDTO.getPassWord());
        values.put("fullName",nhanVienDTO.getFullName());
        values.put("birthday",nhanVienDTO.getBirthday());
        values.put("salary",nhanVienDTO.getSalary());

        String [] dk = new String[]{nhanVienDTO.getId()+""};
        return db.update("tb_NhanVien",values,"id = ?",dk);

    }

    public int deleteRow(NhanVienDTO nhanVienDTO){
        String [] dk = new String[]{nhanVienDTO.getId()+""};
        return db.delete("tb_NhanVien","id = ?",dk);
    }


    public ArrayList<NhanVienDTO> sellectAll(){
        ArrayList<NhanVienDTO> listNv = new ArrayList<>();
        String sql_select = "SELECT * FROM tb_NhanVien";
        Cursor cursor = db.rawQuery(sql_select,null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                NhanVienDTO nhanVienDTO = new NhanVienDTO();
                nhanVienDTO.setId(cursor.getInt(0));
                nhanVienDTO.setUserName(cursor.getString(1));
                nhanVienDTO.setPassWord(cursor.getString(2));
                nhanVienDTO.setFullName(cursor.getString(3));
                nhanVienDTO.setBirthday(cursor.getString(4));
                nhanVienDTO.setSalary(cursor.getDouble(5));

                listNv.add(nhanVienDTO);
                cursor.moveToNext();
            }
        }
        return  listNv;
    }



}
