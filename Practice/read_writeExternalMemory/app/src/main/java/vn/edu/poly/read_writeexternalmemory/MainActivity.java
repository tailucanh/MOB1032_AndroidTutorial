package vn.edu.poly.read_writeexternalmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStreamWriter;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    EditText ed_contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_contents = findViewById(R.id.ed_content);
    }

    public void SaveFileSDCard(View view){
        String content = ed_contents.getText().toString();

        String sdcard_file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hello.txt";
        try {
            FileOutputStream fos = new FileOutputStream(sdcard_file);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(content);
            writer.close();
            fos.close();

            Toast.makeText(this, "Ghi thành công!",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Lỗi đường dẫn!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this, "Ghi lỗi!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void ReadFileSDCard(View view){
        String sdcard_file = Environment.getExternalStorageDirectory().getAbsolutePath() +"/hello.txt";

        File readFile = new File(sdcard_file);
        try {
            Scanner scanner = new Scanner(readFile);
            String tmpdata = "";
            while (scanner.hasNext()){
                tmpdata += scanner.nextLine();
            }
            scanner.close();

            Toast.makeText(this, "Đọc thành công!",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Đọc không thành công!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

}