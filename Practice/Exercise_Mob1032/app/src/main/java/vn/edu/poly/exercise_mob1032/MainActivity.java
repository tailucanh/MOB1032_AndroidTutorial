package vn.edu.poly.exercise_mob1032;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import vn.edu.poly.exercise_mob1032.ADAPTER.ProductAdapter;
import vn.edu.poly.exercise_mob1032.DAO.ProductDAO;

public class MainActivity extends AppCompatActivity {

    Button btn_them;
    ListView lv_prs;
    ProductAdapter productAdapter;
    ProductDAO prDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv_prs = findViewById(R.id.lv_prs);
        prDAO = new ProductDAO(this);
        prDAO.open();
        productAdapter = new ProductAdapter(prDAO.sellectAll(),prDAO);


        lv_prs.setAdapter(productAdapter);

        btn_them = findViewById(R.id.btn_them);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productAdapter.insertPro(MainActivity.this);
            }
        });



    }


}