package vn.edu.poly.mob1032_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageButton;
import pl.droidsonroids.gif.GifImageView;
import vn.edu.poly.mob1032_ass.Adapter.ClassAdapter;
import vn.edu.poly.mob1032_ass.Adapter.StudentAdapter;
import vn.edu.poly.mob1032_ass.DAO.ClassDAO;
import vn.edu.poly.mob1032_ass.DAO.StudentDAO;

public class MainActivity extends AppCompatActivity {


    public  void clickMess(Context context, String url){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Warning !!!");
        builder.setMessage("Bạn sắp chuyển hướng đến 1 trang web mới. Hãy xác nhận !");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                Toast.makeText(context,"Điều hướng thành công !",Toast.LENGTH_SHORT).show();
                Activity activity = (Activity) context;
                activity.startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Đã hủy !",Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        AlertDialog sh = builder.create();
        sh.show();
    }

    public  void clickCall(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Warning !!!");
        builder.setMessage("Bạn muốn gọi cho tôi ?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"0931360954"));
                Activity activity = (Activity) context;
                Toast.makeText(context,"Đang gọi đến số 0931360954",Toast.LENGTH_LONG).show();
                activity.startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Đã hủy !",Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        AlertDialog sh = builder.create();
        sh.show();

    }

    ImageButton tv_moon;
    Button btn_showList;
    ImageView imb_addClass,imb_addStudent;
    ClassAdapter classAdapter;
    ClassDAO classDAO;
    StudentDAO studentDAO;
    StudentAdapter studentAdapter;
    GifImageView gif_callSun,gif_messSun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_moon = findViewById(R.id.tv_moon);
        gif_callSun = findViewById(R.id.imb_CallSun);
        gif_messSun = findViewById(R.id.imb_messSun);


        tv_moon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),DarkActivity.class);
                startActivity(intent);
            }
        });

        gif_callSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCall(MainActivity.this);
            }
        });
        gif_messSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMess(MainActivity.this,"https://www.facebook.com/lucanh.tai");
            }
        });
        //Gọi phương thức thêm class
        classDAO = new ClassDAO(this);
        classDAO.open();
        classAdapter = new ClassAdapter(classDAO.selectAllClass(),classDAO);

        imb_addClass = findViewById(R.id.imb_addClass);
        imb_addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classAdapter.showDialogAddClass(MainActivity.this);
            }
        });
        //Gọi phương thức thêm sinh viên
        studentDAO = new StudentDAO(this);
        studentDAO.open();
        studentAdapter = new StudentAdapter(studentDAO.selectAllStudent(),studentDAO);

        imb_addStudent = findViewById(R.id.imb_addStudent);
        imb_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentAdapter.showDialogAddStudent(MainActivity.this);
            }
        });


        //Click vào button thì hiện list sinh viên
        btn_showList = findViewById(R.id.btn_showList);
        btn_showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ListStudent.class);
                startActivity(intent);
            }
        });

    }
}