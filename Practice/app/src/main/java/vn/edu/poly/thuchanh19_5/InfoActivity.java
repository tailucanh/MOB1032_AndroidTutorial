package vn.edu.poly.thuchanh19_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    EditText ed_Phone;
    Button btn_Call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ed_Phone = findViewById(R.id.ed_Phone);
        btn_Call = findViewById(R.id.btn_Call);


        Intent intent = getIntent();
        Bundle jobBundle =intent.getBundleExtra("Info");

        String email =jobBundle.getString("Email");
        String pass =jobBundle.getString("Pass");

        TextView tv_Info = findViewById(R.id.txtInfo);
        tv_Info.setText(" Email: "+ email+ "\n Pass: "+ pass);



     btn_Call.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String phoneNumber = ed_Phone.getText().toString();
             Toast.makeText(InfoActivity.this," Gọi đến số :"+ phoneNumber,Toast.LENGTH_LONG).show();
             Intent intent_call = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+ phoneNumber));

            startActivity(intent_call);


         }
     });

    }
}