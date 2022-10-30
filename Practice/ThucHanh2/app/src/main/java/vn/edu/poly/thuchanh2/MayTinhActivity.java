package vn.edu.poly.thuchanh2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MayTinhActivity extends AppCompatActivity {
    EditText ed_A, ed_B;
    Button btnTong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maytinh);

        //Lấy dữ liệu từ intent
        Intent intent = getIntent();
       Bundle objBundle = intent.getBundleExtra("Thong_tin");

       //Mở gói hàm
        String hoTen = objBundle.getString("chuoi_ho_ten");
        int tuoi = objBundle.getInt("Tuoi");
        //Ghi lên textView
        TextView tv_info = findViewById(R.id.txtInfo);
        tv_info.setText("Họ và tên: "+hoTen+" Tuổi: "+tuoi);



        ed_A = findViewById(R.id.ed_soA);
        ed_B = findViewById(R.id.ed_soB);
        btnTong = findViewById(R.id.btnTong);

        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double soA = Double.parseDouble(ed_A.getText().toString());
                double soB = Double.parseDouble(ed_B.getText().toString());

                Toast.makeText(MayTinhActivity.this,"Tổng hai số = "+ (soA + soB),Toast.LENGTH_LONG).show();



            }
        });
    }
}