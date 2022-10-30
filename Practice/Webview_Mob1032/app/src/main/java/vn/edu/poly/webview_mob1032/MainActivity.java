package vn.edu.poly.webview_mob1032;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView wb_info;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wb_info = findViewById(R.id.wb_info);
        wb_info.getSettings().setJavaScriptEnabled(true);

        wb_info.setWebChromeClient(new WebChromeClient());
        wb_info.setWebViewClient(new WebViewClient());

        preferences = getSharedPreferences("info_yoursel", Context.MODE_PRIVATE);
        String name = preferences.getString("name","");
        String email = preferences.getString("email","");
        String phone = preferences.getString("phone","");
        String introduct = preferences.getString("introduct","");

        String chuoiHTML = "    <div class=\"container\" style=\"width: 100%; height: 290px; border: 1px solid gray\">\n" +
                "        <h2 class=\"title\" style=\"color: red;text-align: center; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;font-size: 25px;\" >Thông tin giới thiệu</h2>\n" +
                "        <h3 style=\" padding-left: 20px; font-size: 20px; font-weight: bold;\"> - Họ và tên: <a style=\"padding-left: 10px;font-size: 20px; color: rgb(38, 38, 241); font-weight: bold;\">"+name+" </a></h3> \n" +
                "       \n" +
                "        <h3 style=\" padding-left: 20px; font-size: 20px; font-weight: bold;\"> - Email: <i style=\"padding-left: 10px;font-size: 20px; font-weight: 400;\">"+email+" </i></h3> \n" +
                "\n" +
                "        <h3 style=\" padding-left: 20px; font-size: 20px; font-weight: bold;\"> - Số điện thoại: <a style=\"padding-left: 10px;font-size: 20px;color: red;\">"+phone+"</a></h3>\n" +
                "            \n" +
                "        <h3 style=\" padding-left: 20px; font-size: 20px; font-weight: bold;\"> - Giới thiệu: <a style=\"padding-left: 10px;font-size: 20px;\">"+introduct+" </a></h3>\n" +
                "    </div>";
        wb_info.loadData(chuoiHTML,"text/html","utf-8");
    }
}