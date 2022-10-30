package vn.edu.poly.vidu6_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv_product;
    ArrayList<Products> listPr;
    Product_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_product = findViewById(R.id.lv_products);

        //Khởi tạo dữ liệu

        listPr = new ArrayList<Products>();
        listPr.add(new Products(1, "Điẹn thoại",3333,R.drawable.anh2_1));
        listPr.add(new Products(2, "Lap top",3333,R.drawable.anh5_1));
        listPr.add(new Products(3, "Điẹn thoại",3333,R.drawable.anh4));
        listPr.add(new Products(4, "Điẹn thoại",3333,R.drawable.anh2_1));

        adapter = new Product_Adapter(listPr);

        lv_product.setAdapter(adapter);


    }
}