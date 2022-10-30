package vn.edu.poly.read_writeinternalmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText ed_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ed_content = findViewById(R.id.ed_content);

    }

    //Hàm save
    public void saveFile(View view) throws FileNotFoundException {
        String contents = ed_content.getText().toString();
        String  my_file = "myfile.txt";
        try {
            FileOutputStream fos = openFileOutput(my_file, Context.MODE_PRIVATE);

            fos.write(contents.getBytes());
            fos.close();
            Toast.makeText(this, "Ghi thành công!", Toast.LENGTH_SHORT).show();

        }catch (FileNotFoundException e) {
            Toast.makeText(this, "Lỗi đường dẫn!",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this, "Ghi lỗi",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }


    //Hàm read
    public void readFile(View view){
        String  my_file = "myfile.txt";

        //tạo đối tượng string buff để xây dựng chuỗi dữ liệu
        StringBuffer stringBuffer = new StringBuffer();
        try {
            // luồng dữ liệu file
            FileInputStream fis = openFileInput(my_file);
            // khai báo luồng đọc dữ liệu
            InputStreamReader isr = new InputStreamReader(fis);
            // tạo biến đệm cho quá trình đọc
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            // Dùng vòng lặp để đọc từ dòng đầu tiên đến hết file
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            Toast.makeText(this, "Đọc thành công!", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Lỗi đường dẫn!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this, "Đọc không thành công!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
}