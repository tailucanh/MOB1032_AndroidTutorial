package vn.edu.poly.thuchanh2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tạo ứng dụng có 2 activity : - activity main: có 1 nút bấm gọi activity con
        //                             - activity máy tính: có 2 ô nhập text và 1 nut

        //Ánh xạ view
        btnOpen = findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Viết lệnh ở đây
                Intent intent = new Intent(getBaseContext(),MayTinhActivity.class);

                //Chuẩn bị dữ liệu
                String hoten = "nguyen van a";
                int tuoi = 30;
                //Gán vào đối tượng Bundle
                Bundle bundle = new Bundle();
                bundle.putString("chuoi_ho_ten", hoten);
                bundle.putInt("tuoi", tuoi);

                //Gửi vào intent
                intent.putExtra("Thong_tin",bundle);


                //Khỏi động activity
                startActivity(intent);
            }
        });


    }
}