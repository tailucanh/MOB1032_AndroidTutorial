package vn.edu.poly.ph26495_mob1032_lab3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginMainActivity.class);
        toolActivityResultLauncher.launch(intent);

        
    }

 ActivityResultLauncher toolActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
     @Override
     public void onActivityResult(ActivityResult result) {
         TextView name = findViewById(R.id.tv_name);
         TextView pass = findViewById(R.id.tv_pass);


       String takeName = result.getData().getStringExtra("name");
       String takepass = result.getData().getStringExtra("pass");

        name.setText(takeName.toString());
         pass.setText(takepass.toString());

     }
 });

}