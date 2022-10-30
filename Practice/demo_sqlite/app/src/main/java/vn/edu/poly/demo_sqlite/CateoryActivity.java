package vn.edu.poly.demo_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import vn.edu.poly.demo_sqlite.Adapter.CategoryAdapter;
import vn.edu.poly.demo_sqlite.DAO.CatDAO;
import vn.edu.poly.demo_sqlite.DTO.CatDTO;

public class CateoryActivity extends AppCompatActivity {

    ListView lv_cat;
    CatDAO dao;
    CategoryAdapter adapter;
    String TAG = "TTTTTT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cateory);

        lv_cat = findViewById(R.id.lv_cat);
        dao = new CatDAO(this);
        Log.d(TAG,"oncreate : số dòng = "+dao.selectAll().size());

        //Thử nghiệm thêm mớic
        CatDTO objcat = new CatDTO();
        objcat.setName("Them zzzz");

        long new_id = dao.insertRow(objcat);
        Log.d(TAG,"oncreate : Id mới thêm = "+ new_id);


        //TẠO Adapter
        adapter = new CategoryAdapter(dao.selectAll());
        lv_cat.setAdapter(adapter);

    }
}