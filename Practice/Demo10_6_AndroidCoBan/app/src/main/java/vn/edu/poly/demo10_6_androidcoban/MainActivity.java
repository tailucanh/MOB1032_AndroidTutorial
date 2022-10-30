package vn.edu.poly.demo10_6_androidcoban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.demo10_6_androidcoban.Adapter.NhanVienAdapter;
import vn.edu.poly.demo10_6_androidcoban.DAO.NhanVienDAO;
import vn.edu.poly.demo10_6_androidcoban.DTO.NhanVienDTO;

public class MainActivity extends AppCompatActivity {

    Button btn_save;

    NhanVienDAO nhanVienDAO;
    NhanVienAdapter adapter;
    ListView lv_sv;
    ArrayList<NhanVienDTO> listNv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_sv = findViewById(R.id.lv_sinhvien);
        btn_save = findViewById(R.id.btnSave);

        nhanVienDAO = new NhanVienDAO(MainActivity.this);

        adapter = new NhanVienAdapter(nhanVienDAO.sellectAll(),nhanVienDAO);
        lv_sv.setAdapter(adapter);


        EditText name = findViewById(R.id.ed_username);
        EditText pass = findViewById(R.id.ed_pass);
        EditText birthday = findViewById(R.id.ed_birthday);
        EditText fullname = findViewById(R.id.ed_fullname);
        EditText salary = findViewById(R.id.ed_salary);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NhanVienDTO nhanVienDTO = new NhanVienDTO();
                 nhanVienDTO.setUserName(name.getText().toString());
                 nhanVienDTO.setPassWord(pass.getText().toString());
                 nhanVienDTO.setBirthday(birthday.getText().toString());
                 nhanVienDTO.setFullName(fullname.getText().toString());
                 nhanVienDTO.setSalary(Double.parseDouble(salary.getText().toString()));

                 listNv = new ArrayList<>();
                 long res = nhanVienDAO.insertNew(nhanVienDTO);
                 if(res > 0){
                     listNv.clear();
                     listNv.addAll(nhanVienDAO.sellectAll());
                     adapter.notifyDataSetChanged();
                     Toast.makeText(MainActivity.this, "Đã thêm",Toast.LENGTH_SHORT).show();

                 }else {
                     Toast.makeText(MainActivity.this, "Thêm thất bại" ,Toast.LENGTH_SHORT).show();
                 }

            }
        });





    }
}