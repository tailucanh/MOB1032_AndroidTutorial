package vn.edu.poly.demo_sqlite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.demo_sqlite.DTO.CatDTO;
import vn.edu.poly.demo_sqlite.DbHelpe.MyDbHelper;

public class CatDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    // hàm khởi tạo bangr
    public CatDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();// Nếu chưa có csdl sẽ tự tạo, có rồi thò mở ra

    }

    //Hàm đóng
    public void close() {
        db.close(); //Dóng kết nối csdl
    }


    //Hàm thêm: Đầu vào objCat chưa có ID ------------------------------------
    public long insertRow(CatDTO objCat) {
        // Sử dụng ContentValue để chèn dữ liệu
        ContentValues objContent = new ContentValues();
        objContent.put("name", objCat.getName());
        /// thực hiện lệnh insert
        return db.insert("tb_cat", null, objContent);
        // hàm trả về id của dòng mới thêm
    }

    //---- hàm sửa dữ liệu: đầu vào là objCat phải gắn thêm ID dòng muốn sửa
    public int updateRow(CatDTO objCat) {
        ContentValues objContent = new ContentValues();
        objContent.put("name", objCat.getName());
        // tạo ra mảng điều kiện để truyền vào tham số
        String[] dieu_kien = new String[]{objCat.getId() + ""};
        // thực hiện lệnh cập nhật
        return db.update("tb_cat", objContent, "id = ?", dieu_kien);
        // hàm trả về số lượng dòng bị sửa
    }

    // hàm xóa: Dầu vào là objCat
    public int deleteRow(CatDTO objCat) {
        // Tạo mảng điều kiện
        String[] dieu_kien = new String[]{objCat.getId() + ""};
        return db.delete("tb_cat", "id = ?", dieu_kien);
    }

    // hàm lấy ra 1 dòng ==> trả về objCat --> đầu vào là id của dòng.
    public CatDTO selectOne(int id) {
        CatDTO objCat = null; // tạo đối tượng rỗng để tránh lỗi return

        String[] dieu_kien = new String[]{id + ""};
        // câu lệnh sql
        String sql = "SELECT id, name FROM tb_cat WHERE id = ? ";

        Cursor c = db.rawQuery(sql, dieu_kien);
        if (c.moveToFirst()) {
            // có dữ liệu
            objCat = new CatDTO();
            objCat.setId(c.getInt(0));
            objCat.setName(c.getString(1));
        }

        return objCat;
    }


    //Lấy danh sách bản ghi trong bảng Cat
    public ArrayList<CatDTO> selectAll(){
        //Tạo 1 danh sách rỗng
        ArrayList<CatDTO> listCat = new ArrayList<CatDTO>();

        //Lấy dữ liêu

        String sql_Select = "SELECT * FROM tb_cat";
        //Thực thi

        Cursor c = db.rawQuery(sql_Select,null);
        //Kiểm tra có dữ liệu lấy ra không

        if(c.moveToNext()){   //Có dữ liệu
            while (!c.isAfterLast()){   //Con trỏ chưa đến dòng cuối cùng
                //Tạo đối tượng gắn DL
                CatDTO objCat = new CatDTO();
                objCat.setId(c.getInt(0) );
                objCat.setName(c.getString(1) );

                listCat.add(objCat);
                //Đưa cn trỏ sang dòng kế tiếp
                c.moveToNext();
            }
        }
        return listCat;
    }

}
