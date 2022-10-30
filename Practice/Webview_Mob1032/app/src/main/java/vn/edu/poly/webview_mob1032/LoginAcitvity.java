package vn.edu.poly.webview_mob1032;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAcitvity extends AppCompatActivity {

    EditText ed_name ,ed_email, ed_phone,ed_introduct;
    Button btn_submit;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected  void onResume() {
        super.onResume();
        ed_name = findViewById(R.id.ed_name);
        ed_phone = findViewById(R.id.ed_phone);
        ed_email= findViewById(R.id.ed_email);
        ed_introduct = findViewById(R.id.ed_introduct);

        preferences = getSharedPreferences("info_yoursel", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String name = ed_name.getText().toString();
        String email = ed_email.getText().toString();
        String  phone = ed_phone.getText().toString();
        String introduct = ed_introduct.getText().toString();

        if(name.equals("") || email.equals("") ||phone.equals("") || introduct.equals("")){
            Toast.makeText(getBaseContext(),"Hãy nhập dữ liệu!",Toast.LENGTH_SHORT).show();
        }else {
            editor.putString("name",name);
            editor.putString("email",email);
            editor.putString("phone",phone);
            editor.putString("introduct",introduct);
            Toast.makeText(getBaseContext(),"Gửi thành công!",Toast.LENGTH_SHORT).show();
            editor.commit();
        }



    }
}