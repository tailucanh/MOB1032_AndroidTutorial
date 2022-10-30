package vn.edu.poly.mob1032_ass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyInfoActivity extends AppCompatActivity {
    WebView wb_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  My infomation");
        actionBar.setLogo(R.mipmap.smile2);

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);

        Drawable drawable= getResources().getDrawable(R.drawable.backbutton);

        Drawable drawable2 = getResources().getDrawable(R.drawable.custom_actionbar_info);

        actionBar.setBackgroundDrawable(drawable2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);



        wb_info = findViewById(R.id.wb_info);
        wb_info.getSettings().setJavaScriptEnabled(true);

        wb_info.setWebChromeClient(new WebChromeClient());
        wb_info.setWebViewClient(new WebViewClient());


        wb_info.loadUrl("file:///android_asset/info.html");

    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return  true;

        }

        return super.onOptionsItemSelected(item);
    }

}