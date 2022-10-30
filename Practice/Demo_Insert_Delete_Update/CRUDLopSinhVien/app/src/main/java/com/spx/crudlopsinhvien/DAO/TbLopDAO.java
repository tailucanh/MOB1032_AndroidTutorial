package com.spx.crudlopsinhvien.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.spx.crudlopsinhvien.DTO.TbLop;
import com.spx.crudlopsinhvien.SQLiteHelper.CreateDbSinhVien;

public class TbLopDAO {
    SQLiteDatabase database;
    CreateDbSinhVien createDbSinhVien;

    public TbLopDAO(Context context) {
        createDbSinhVien = new CreateDbSinhVien(context);
    }
    public void open(){
        database = createDbSinhVien.getWritableDatabase();
    }
    public void close(){
        createDbSinhVien.close();
    }

    public long AddNew(TbLop tbLop){
        // tạo đối tượng contentvalues để làm phương tiện gửi dữ liệu cho database,
        // giống như bundle cho intent
        ContentValues contentValues = new ContentValues();

        contentValues.put(TbLop.COL_NAME_TEN_LOP, tbLop.getTen_lop());
        contentValues.put(TbLop.COL_NAME_NGANH, tbLop.getNganh());
        // gọi hàm insert để thêm dữ liệu vào bảng

        long res = database.insert(TbLop.TB_NAME, null, contentValues);

        return  res;
    }

}
