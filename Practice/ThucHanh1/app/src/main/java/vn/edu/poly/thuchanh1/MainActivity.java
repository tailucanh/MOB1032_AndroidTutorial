package vn.edu.poly.thuchanh1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //B1: khai báo biến để ánh xạ view vào làm việc
    TextView tv_hoten, tv_dt, tv_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //B2: ánh xạ view vào biến
        tv_hoten = findViewById(R.id.tv_hoten);
        tv_dt = findViewById(R.id.tv_dt);
        tv_button = findViewById(R.id.tv_button);


        //B3: Xử lí tương tác
        tv_hoten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Các lệch xử lí click
                //Lấy chuỗi tên và thông báo

                String hoten = tv_hoten.getText().toString();
                Toast.makeText(MainActivity.this,"HELLO "+hoten,Toast.LENGTH_SHORT).show();
            }
        });

        tv_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = tv_dt.getText().toString();
                Toast.makeText(MainActivity.this," Gọi đến số :"+ phone,Toast.LENGTH_LONG).show();

                //Tạo đối tượng intent để gọi điện
                Intent intent_call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                //Gọi activity gọi điện
                startActivity(intent_call);

                //Đăng kí xin quyền sử dụng chức năng gọi điện đối với điện thoại bên mainifest

            }
        });

        tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String button = tv_button.getText().toString();
                Toast.makeText(MainActivity.this, "WELLCOM TO SMILE !",Toast.LENGTH_SHORT).show();
            }
        });









    }
}