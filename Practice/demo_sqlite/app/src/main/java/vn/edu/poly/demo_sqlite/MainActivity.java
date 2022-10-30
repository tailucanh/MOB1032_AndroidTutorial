package vn.edu.poly.demo_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import vn.edu.poly.demo_sqlite.DAO.CatDAO;
import vn.edu.poly.demo_sqlite.DAO.ProductsDAO;
import vn.edu.poly.demo_sqlite.DTO.CatDTO;
import vn.edu.poly.demo_sqlite.DTO.Product_DTO;

public class MainActivity extends AppCompatActivity {
    String TAG = "Smile";
    String TAG2 = "yyyyyyyyyy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  /*  //TẠO DAO  để thử nghiệm

        CatDAO  catDAO = new CatDAO(this);
        // khai báo sdanh sách array list
        ArrayList<CatDTO> listCat = catDAO.selectAll();

        //Ghi log
        Log.d(TAG,"onCreate: số lượng bản ghi: "+listCat.size());

        for(int i = 0; i < listCat.size(); i++){
            CatDTO objCat = listCat.get(i);
            Log.d(TAG,"onCreate: Tên danh mục = "+objCat.getName());
        }


        //TẠO Prodcuct  để thử nghiệm

        ProductsDAO productsDAO = new ProductsDAO(this);
        // khai báo sdanh sách array list
        ArrayList<Product_DTO> listPr = productsDAO.selectAll();

        //Ghi log
        Log.d(TAG2,"onCreate: số lượng bản ghi: "+listPr.size());

        for(int i = 0; i < listPr.size(); i++){
            Product_DTO objpr = listPr.get(i);
            Log.d(TAG2,"onCreate: Tên danh mục = "+objpr.getName());
            Log.d(TAG2,"onCreate: Giá = "+objpr.getPrice());

        }*/
        Button btn_click = findViewById(R.id.btn_click);

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),CateoryActivity.class);
                startActivity(intent);
            }
        });


    }
}