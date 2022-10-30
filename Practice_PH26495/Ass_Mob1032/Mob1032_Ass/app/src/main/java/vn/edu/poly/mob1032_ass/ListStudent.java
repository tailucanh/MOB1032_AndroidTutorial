package vn.edu.poly.mob1032_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import vn.edu.poly.mob1032_ass.Adapter.ClassAdapter;
import vn.edu.poly.mob1032_ass.Adapter.StudentAdapter;
import vn.edu.poly.mob1032_ass.DAO.ClassDAO;
import vn.edu.poly.mob1032_ass.DAO.StudentDAO;

public class ListStudent extends AppCompatActivity {

    ImageButton imb_Back;
    ListView lv_class, lv_students;
    ClassDAO classDAO;
    StudentDAO studentDAO;
    ClassAdapter classAdapter;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);

        imb_Back = findViewById(R.id.imb_Back);
        imb_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);

            }
        });

        //Gọi phương thức để gán dữ liệu vào listView class
        lv_class = findViewById(R.id.lv_class);
        classDAO = new ClassDAO(this);
        classDAO.open();
        classAdapter = new ClassAdapter(classDAO.selectAllClass(), classDAO);
        lv_class.setAdapter(classAdapter);

        //Gọi phương thức để gán dữ liệu vào listView sinh viên
        lv_students = findViewById(R.id.lv_students);
        studentDAO = new StudentDAO(this);
        studentDAO.open();
        studentAdapter = new StudentAdapter(studentDAO.selectAllStudent(),studentDAO);
        lv_students.setAdapter(studentAdapter);


    }


}