package vn.edu.poly.lab_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed_name, ed_pass;
    CheckBox chk_ghinho;
    Button btn_save;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_name = findViewById(R.id.ed_name);
        ed_pass = findViewById(R.id.ed_pass);
        chk_ghinho = findViewById(R.id.chk_ghinho);
        btn_save = findViewById(R.id.btn_login);


        preferences = getSharedPreferences("user_login", Context.MODE_PRIVATE);

        String user = preferences.getString("userName","");
        String password = preferences.getString("pass","");

       ed_name.setText(user);
       if(password.length() > 0){
           ed_pass.setText(password);
           chk_ghinho.setChecked(true);
       }else {
           chk_ghinho.setChecked(false);
       }


//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = preferences.edit();
//                String name = ed_name.getText().toString();
//                String pass = ed_pass.getText().toString();
//                editor.putString("userName", name);
//                editor.putString("pass",pass);
//                if(chk_ghinho.isChecked()){
//                    String user_new = preferences.getString("userName","");
//                    String password_new = preferences.getString("pass","");
//                    editor.commit();
//                    Toast.makeText(getBaseContext(), "Đăng nhập thành công!"+"\n - Họ và tên: " + user_new + "\n -  Password: "+ password_new, Toast.LENGTH_SHORT).show();
//                }else{
//                    String user_new = preferences.getString("userName","");
//                    editor.commit();
//                    Toast.makeText(getBaseContext(), "Đăng nhập thành công!"+"\n - Họ và tên: " + user_new , Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

        //Yêu cầu 1
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                String name = ed_name.getText().toString();
                String pass = ed_pass.getText().toString();
                if(name.equals("") || pass.equals("")){
                    Toast.makeText(getBaseContext(),"Hãy nhập dữ liệu!",Toast.LENGTH_SHORT).show();
                }else {
                        if(chk_ghinho.isChecked()){
                            editor.putString("userName", name);
                            editor.putString("pass",pass);
                            editor.commit();

                            String user = preferences.getString("userName","");
                            String password = preferences.getString("pass","");
                            Toast.makeText(getBaseContext(), "Đăng nhập thành công!"+"\n - Họ và tên: " + user + "\n -  Password: ********" , Toast.LENGTH_SHORT).show();

                        }else{
                            editor.putString("userName", name);
                            editor.commit();
                            String user = preferences.getString("userName","");
                            Toast.makeText(getBaseContext(), "Đăng nhập thành công!"+"\n - Họ và tên: " + user , Toast.LENGTH_SHORT).show();
                        }


                }
            }
        });
    }

}