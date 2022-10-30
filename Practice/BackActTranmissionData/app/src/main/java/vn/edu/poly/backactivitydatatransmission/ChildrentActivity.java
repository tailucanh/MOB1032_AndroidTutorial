package vn.edu.poly.backactivitydatatransmission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ChildrentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childrent);
    }
    @Override
    public void onBackPressed() {

        Intent i = getIntent();
        i.putExtra("hoTen" ,"Dev Mobile Chấm NÉT");  // khai báo biến kiểu intent để gửi dữ liệu sang activity cha

        setResult(ChildrentActivity.RESULT_OK,i); // lệnh này là để gửi dữ liệu, tham số thứ nhất là biến cờ trả lại trạng thái
        super.onBackPressed(); // cái này phải để dưới cuối
    }
}