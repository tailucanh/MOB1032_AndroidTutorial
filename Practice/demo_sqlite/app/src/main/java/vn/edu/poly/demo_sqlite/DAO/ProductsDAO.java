package vn.edu.poly.demo_sqlite.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.demo_sqlite.DTO.CatDTO;
import vn.edu.poly.demo_sqlite.DTO.Product_DTO;
import vn.edu.poly.demo_sqlite.DbHelpe.MyDbHelper;

public class ProductsDAO {

    SQLiteDatabase db;
    MyDbHelper dbHelper;

    // hàm khởi tạo bangr
    public ProductsDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();// Nếu chưa có csdl sẽ tự tạo, có rồi thò mở ra

    }

    //Hàm đóng
    public void close(){
        db.close(); //Dóng kết nối csdl
    }

    //Lấy danh sách bản ghi trong bảng Cat
    public ArrayList<Product_DTO> selectAll(){
        //Tạo 1 danh sách rỗng
        ArrayList<Product_DTO> listPr = new ArrayList<Product_DTO>();

        //Lấy dữ liêu
        String sql_Select = "SELECT * FROM tb_Products";
        //Thực thi
        Cursor c = db.rawQuery(sql_Select,null);
        //Kiểm tra có dữ liệu lấy ra không
        if(c.moveToNext()){   //Có dữ liệu
            while (!c.isAfterLast()){   //Con trỏ chưa đến dòng cuối cùng
                //Tạo đối tượng gắn DL
                Product_DTO objPr = new Product_DTO();
                objPr.setId(c.getInt(0) );
                objPr.setName(c.getString(1) );
                objPr.setPrice(c.getDouble(2));
                objPr.setImg_res(c.getInt(3));
                objPr.setId_cat(c.getInt(4));

                listPr.add(objPr);
                //Đưa cn trỏ sang dòng kế tiếp
                c.moveToNext();
            }
        }
        return listPr;
    }
}
