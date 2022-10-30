package vn.edu.poly.backactivitydatatransmission;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    final  String TAG = "zzzz";

    public void OpenActivityChildren(View view){
        Button btn_click = findViewById(R.id.btn_click);
        Intent intent = new Intent(this, ChildrentActivity.class);
        toolActivityResultLauncher.launch(intent);
        Log.d(TAG, "OpenAct2: " );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    ActivityResultLauncher toolActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Log.d(TAG, "onActivityResult: " + result.toString());  // dòng này là in toàn bộ dữ liệu ra để xem
            Log.d(TAG, "onActivityResult: " + result.getResultCode());  // dòng này để xem cái code mà bên activityCon đánh dấu trả lại là gì
            Log.d(TAG, "onActivityResult: hoTen = " + result.getData().getStringExtra("hoTen"));  // dòng này là lấy dữ liệu từ ActivityCon trả lại thông qua Intent.
        }
    });



}