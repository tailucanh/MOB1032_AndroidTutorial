package vn.edu.poly.demo5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lv_color;
    // khai báo mảng dữ liệu nguồn
    String [] mang_mau_sac = { "Xanh","Đỏ", "Tím", "Vàng","Xanh","Đỏ", "Tím", "Vàng","Xanh","Đỏ", "Tím", "Vàng"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_color = findViewById(R.id.lv_color);
        // tạo adapter
        ArrayAdapter<String> adapterColor =
                new ArrayAdapter<>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        mang_mau_sac
                );

        // gắn adapter vào listview
        lv_color.setAdapter(  adapterColor );

    }
}