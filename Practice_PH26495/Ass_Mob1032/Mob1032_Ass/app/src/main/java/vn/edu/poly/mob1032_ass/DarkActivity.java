package vn.edu.poly.mob1032_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DarkActivity extends AppCompatActivity {
    ImageButton tv_Sun, imb_callMoon,imb_messMoon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark);

        tv_Sun = findViewById(R.id.tv_sun);


        tv_Sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}