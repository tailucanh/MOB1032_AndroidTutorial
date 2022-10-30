package vn.edu.poly.ph26495_practiceoop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    String TAG = "ZZZZZZZ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<SinhVienPoly> listSv = new ArrayList<>();

        listSv.add(new SinhVienPoly("1","levana","levana@gmal.com","10 triệu"));
        listSv.add(new SinhVienPoly("2","levanb","levanb@gmal.com","10 triệu"));
        listSv.add(new SinhVienPoly("3","levanc","levanc@gmal.com","10 triệu"));


        Log.d(TAG,"- Số lượng sinh viên: "+listSv.size());
        for(int i = 0; i < listSv.size(); i++){
            SinhVienPoly svpoly = listSv.get(i);
            svpoly.inThongTin();
        }

        //Thêm và thay đổi
        Log.d(TAG,"- Thông tin viên sau khi thêm và sửa");

        listSv.get(0).setName(" Lục Anh Tài");
        listSv.get(0).setHocbong("20 triệu");
        listSv.add(new SinhVienPoly("4","levand","levand@gmal.com","10 triệu"));
        for(int i = 0; i  < listSv.size();i ++){
            SinhVienPoly svpoly = listSv.get(i);
            svpoly.inThongTin();
        }
        Log.d(TAG,"- Số lượng sinh viên sau khi thêm: "+listSv.size());

        //Xóa bớt trong lis


        listSv.remove(1);
        for(int i = 0; i  < listSv.size();i ++){
            SinhVienPoly svpoly = listSv.get(i);
            svpoly.inThongTin();
        }
        Log.d(TAG,"- Số lượng sinh viên sau khi xóa"+listSv.size());

        Log.d(TAG,"- Thêm sinh viên từ form nhập vào List");



        Button btn_save = findViewById(R.id.btn_Save);



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed_id = findViewById(R.id.ed_id);
                EditText ed_name = findViewById(R.id.ed_name);
                EditText ed_email = findViewById(R.id.ed_email);
                EditText ed_hocbong = findViewById(R.id.ed_hocBong);


                String id = ed_id.getText().toString();
                String name = ed_name.getText().toString();
                String email = ed_email.getText().toString();
                String hocBong = ed_hocbong.getText().toString();

                SinhVienPoly sv2 = new SinhVienPoly();
                sv2.setName(name);
                sv2.setEmail(email);
                sv2.setId(id);
                sv2.setHocbong(hocBong);

                listSv.add(sv2);
                sv2.inThongTin();


            }
        });






        }






}















