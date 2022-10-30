package vn.edu.poly.sqlite_them_sua_xoa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import vn.edu.poly.sqlite_them_sua_xoa.DAO.TbLopDAO;
import vn.edu.poly.sqlite_them_sua_xoa.DTO.TbLop;

public class MainActivity extends AppCompatActivity {


    EditText ed_ten_lop, ed_nganh;
    TbLopDAO tbLopDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_ten_lop = findViewById(R.id.ed_ten_lop);
        ed_nganh = findViewById(R.id.ed_nganh);

        // mở kết nối CSDL:
        tbLopDAO = new TbLopDAO(this); // khởi tạo đối tượng truyền thêm context
        tbLopDAO.open(); // gọi hàm mở kết nối CSDL


    }


    public void ClickAddRow(View view){

        TbLop tbLop = new TbLop();
        tbLop.setTen_lop( ed_ten_lop.getText().toString() );
        tbLop.setNganh( ed_nganh.getText().toString());
        long kq = tbLopDAO.AddNew(tbLop);
        if(kq>0){
            Toast.makeText(this, "Thêm mới thành công "+ kq , Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Thêm mới thất bại "+ kq , Toast.LENGTH_SHORT).show();


    }

    public void UpdateRow(View view){
        String ten_lop_new = ed_ten_lop.getText().toString();
        String ten_nganh_new = ed_nganh.getText().toString();



    }

    public void DeleteRow(View view){

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        tbLopDAO.close();
    }

}