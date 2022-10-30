package vn.edu.poly.lottery;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_Click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        btn_Click = findViewById(R.id.btnClick);
        btn_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),LotteryAcitvity.class);
                Toast.makeText(MainActivity.this,"Chào mừng đến với sổ số tự chọn !",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }
}