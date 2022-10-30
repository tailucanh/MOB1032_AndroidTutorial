package vn.edu.poly.ph26495_mob1032_lab3;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginMainActivity extends AppCompatActivity {
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);


        btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        EditText edname = findViewById(R.id.ed_name);
        EditText edpass = findViewById(R.id.ed_pass);


        String name = edname.getText().toString();
        String pass = edpass.getText().toString();
        if(name.equals("")){
            Toast.makeText(this,"Hãy nhập tên! ",Toast.LENGTH_SHORT).show();
        }else if(pass.equals("")){
            Toast.makeText(this,"Hãy nhập mk! ",Toast.LENGTH_SHORT).show();
        }else {
            intent.putExtra("name" ,name);
            intent.putExtra("pass" ,pass);

            setResult(LoginMainActivity.RESULT_OK,intent);
            super.onBackPressed(); // cái này phải để  cuối
        }


    }


}