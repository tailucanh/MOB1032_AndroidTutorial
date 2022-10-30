package vn.edu.poly.demo_intent_filter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         imageView = findViewById(R.id.img_shared);
         if(getIntent().getExtras() != null){
             imageView.setImageURI((Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM));
         }


    }
}