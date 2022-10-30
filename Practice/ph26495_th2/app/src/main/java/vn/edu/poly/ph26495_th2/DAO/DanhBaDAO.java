package vn.edu.poly.ph26495_th2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.ph26495_th2.DTO.DanhBaDTO;
import vn.edu.poly.ph26495_th2.DpHelpe.MydpHelpe;

public class DanhBaDAO {
    SQLiteDatabase db;
    MydpHelpe dbHelper;

    public DanhBaDAO(Context context) {
        dbHelper = new MydpHelpe(context);
        db = dbHelper.getWritableDatabase();// Nếu chưa có csdl sẽ tự tạo, có rồi thò mở ra

    }

    //Hàm đóng
    public void close() {
        db.close(); //Dóng kết nối csdl
    }

    public void open(){
      db = dbHelper.getWritableDatabase();
    }




    public long insertRow( DanhBaDTO objdanhba) {
        // Sử dụng ContentValue để chèn dữ liệu
        ContentValues objContent = new ContentValues();

        objContent.put("name", objdanhba.getTen());
        objContent.put("sdt", objdanhba.getSdt());
        objContent.put("ghichu", objdanhba.getGhiChu());
        /// thực hiện lệnh insert

        return db.insert("tb_danhba", null, objContent);



    }


    public int updateRow(DanhBaDTO objdanhba) {
        ContentValues objContent = new ContentValues();
        objContent.put("name", objdanhba.getTen());
        objContent.put("sdt", objdanhba.getSdt());
        objContent.put("ghichu", objdanhba.getGhiChu());

        String[] dieu_kien = new String[]{objdanhba.getId() + ""};

        // thực hiện lệnh cập nhật
        return db.update("tb_danhba", objContent, "id = ?", dieu_kien);
        // hàm trả về số lượng dòng bị sửa
    }


    public int deleteRow(DanhBaDTO objdanhba ){
        // Tạo mảng điều kiện
        String[] dieu_kien = new String[]{objdanhba.getId() + ""};
        return db.delete("tb_danhba", "id = ?", dieu_kien);
    }


    public DanhBaDTO selectOne(int id) {
        DanhBaDTO objdanhba = null; // tạo đối tượng rỗng để tránh lỗi return

        String[] dieu_kien = new String[]{id + ""};
        // câu lệnh sql
        String sql = "SELECT id, name,sdt,ghichu FROM tb_danhba WHERE id = ? ";

        Cursor c = db.rawQuery(sql, dieu_kien);
        if (c.moveToFirst()) {
            // có dữ liệu
            objdanhba = new DanhBaDTO();
            objdanhba.setId(c.getInt(0));
            objdanhba.setTen(c.getString(1));
            objdanhba.setSdt(c.getString(2));
            objdanhba.setGhiChu(c.getString(3));
        }

        return objdanhba;
    }

    //Lấy danh sách bản ghi trong bảng
    public ArrayList<DanhBaDTO> selectAll(){
        //Tạo 1 danh sách rỗng
        ArrayList<DanhBaDTO> listDanhba = new ArrayList<DanhBaDTO>();

        //Lấy dữ liêu
        String sql_Select = "SELECT * FROM tb_danhba";
        //Thực thi

        Cursor c = db.rawQuery(sql_Select,null);
        //Kiểm tra có dữ liệu lấy ra không

        if(c.moveToFirst()){   //Có dữ liệu
            while (!c.isAfterLast()){   //Con trỏ chưa đến dòng cuối cùng
                //Tạo đối tượng gắn DL
                DanhBaDTO objdanhba = new DanhBaDTO();
                objdanhba.setId(c.getInt(0) );
                objdanhba.setTen(c.getString(1));
                objdanhba.setSdt("0"+c.getString(2));
                objdanhba.setGhiChu(c.getString(3));

                listDanhba.add(objdanhba);
                //Đưa cn trỏ sang dòng kế tiếp
                c.moveToNext();    //Phải gọi vào
            }
        }
        return listDanhba;
    }
}
