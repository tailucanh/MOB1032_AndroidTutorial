package vn.edu.poly.mob1032_ass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {

    EditText ed_username, ed_pass;
    Button btn_login;
    SharedPreferences preferences;
    RadioButton rdo_user, rdo_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle( "   "+ getString(R.string.tiile_actionBar));
        actionBar.setLogo(R.mipmap.smile2);

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);

        Drawable drawable = getResources().getDrawable(R.drawable.custom_color_actionbar);
        actionBar.setBackgroundDrawable(drawable);

        ed_username = findViewById(R.id.ed_UserDialog);
        ed_pass = findViewById(R.id.ed_PassDialog);
        btn_login = findViewById(R.id.btnDialog);
        rdo_user = findViewById(R.id.rdo_user);
        rdo_admin = findViewById(R.id.rdo_admin);

        //lưu thông tin người dùng
        preferences = getSharedPreferences("login_User",Context.MODE_PRIVATE);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                String name = ed_username.getText().toString();
                String pass = ed_pass.getText().toString();

                if (name.equals("") ){
                    Toast.makeText(getBaseContext(),"Hãy nhập tên !",Toast.LENGTH_SHORT).show();

                }else if(!rdo_admin.isChecked() && !rdo_user.isChecked()){
                    Toast.makeText(getBaseContext(),"Hãy chọn quyền đăng nhập!",Toast.LENGTH_SHORT).show();
                } else {
                  if(rdo_user.isChecked()){
                      editor.putString("name", name);
                      editor.putString("pass", name);
                      editor.commit();
                      Toast.makeText(getBaseContext(), "Tài khoản của bạn đã được lưu!", Toast.LENGTH_LONG).show();
                      ShowDialogWarning(LoginActivity.this);
                  }
                  if(rdo_admin.isChecked()  ){
                      if(pass.equals("")) {
                          Toast.makeText(getBaseContext(), "Hãy nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                      }else {
                          editor.putString("name", name);
                          editor.putString("pass",pass);
                          editor.commit();
                          Toast.makeText(getBaseContext(), "Tài khoản và mật khẩu của bạn đã được lưu!", Toast.LENGTH_LONG).show();
                          ShowDialogWarning(LoginActivity.this);
                      }
                  }


                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option_info,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.opt_info:
                    Intent intent = new Intent(getBaseContext(),MyInfoActivity.class);
                    startActivity(intent);
                    break;

            }

        return super.onOptionsItemSelected(item);
    }






    public  void ShowDialogWarning( Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Warning !!!");
        builder.setMessage("Xác nhận đăng nhập !");
        builder.setCancelable(true);


        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Đã hủy !",Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        AlertDialog sh = builder.create();
        sh.show();
    }



}