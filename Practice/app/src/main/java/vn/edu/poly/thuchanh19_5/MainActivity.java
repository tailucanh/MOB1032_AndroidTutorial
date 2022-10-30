package vn.edu.poly.thuchanh19_5;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed_Email,ed_Pass;
    Button btn_Sign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--------------Chỉnh màu trong action bar--------------
       ActionBar actionBar;
       actionBar = getSupportActionBar();

        // Gọi hàm set màu cho back ground
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EBDAB5E7"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        //------------------------------------
        ed_Email = findViewById(R.id.ed_email);
        ed_Pass = findViewById(R.id.ed_Pass);
        btn_Sign = findViewById(R.id.btnSign);

        btn_Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_Email.getText().toString();
                String pass = ed_Pass.getText().toString();

                if (email.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this,"Hãy nhập thông tin",Toast.LENGTH_SHORT).show();
                }else if(email.equals("Admin@gmail.com") && pass.equals("123")){
                    Toast.makeText(MainActivity.this,"Bạn đã đăng nhập thành công !",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), InfoActivity.class);
                    String email2 = ed_Email.getText().toString();
                    String pass2 = ed_Pass.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("Email",email2);
                    bundle.putString("Pass",pass2);

                    intent.putExtra("Info",bundle);

                    startActivity(intent);

                }else {
                    Toast.makeText(MainActivity.this,"Bạn đã nhập sai thông tin !",Toast.LENGTH_SHORT).show();

                }



            }
        });

    }
}