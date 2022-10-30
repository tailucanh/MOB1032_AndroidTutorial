package vn.edu.poly.mob1032_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.mob1032_ass.DTO.ClassDTO;
import vn.edu.poly.mob1032_ass.DTO.StudentDTO;
import vn.edu.poly.mob1032_ass.Database.MyDbHelper;

public class StudentDAO {
    SQLiteDatabase sqLiteDatabase;
    MyDbHelper dbHelper;

    public StudentDAO(Context context){
        dbHelper = new MyDbHelper(context);
    }

    public void open(){
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }
    //Hàm thêm sinh viên

    public long insertNew(StudentDTO studentDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentName", studentDTO.getStudentName());
        contentValues.put("birthday",studentDTO.getBirthday());
        contentValues.put("numberPhone",studentDTO.getNumberPhone());
        contentValues.put("email",studentDTO.getEmail());
        contentValues.put("idClass",studentDTO.getIdClass());

        long res = sqLiteDatabase.insert("tb_Students", null, contentValues);

        return  res;
    }

    //Hàm xóa sinh viên
    public int deleteRow(StudentDTO studentDTO){

        int res =sqLiteDatabase.delete("tb_Students","idStudent = ?", new String[]{studentDTO.getIdStudent() +""});

        return  res;
    }
    public StudentDTO selectOneWithGroup(int id){

        StudentDTO studentDTO = new StudentDTO();
        String[] dk = new String[]{id + ""};
        String str_sql = " SELECT idStudent, studentName, birthday ,numberPhone, email ,tb_Students.idClass, tb_Class.className FROM tb_Students INNER JOIN tb_Class ON tb_Students.idClass = tb_Class.idClass WHERE idStudent = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(str_sql,dk);

        if(cursor.moveToFirst()){
            // nếu có dữ liệu thì hàm moveToFirst sẽ trả về giá trị true
            studentDTO.setIdStudent( cursor.getInt(   0   )   );
            studentDTO.setStudentName( cursor.getString(1   ));
            studentDTO.setBirthday( cursor.getString(2   ));
            studentDTO.setNumberPhone( cursor.getString(3   ));
            studentDTO.setEmail( cursor.getString(4   ));
            studentDTO.setIdClass( cursor.getInt(5  ));
            studentDTO.setClassName( cursor.getString(6));

        }
        return studentDTO;
    }

    //Hàm update
    public int updateRow(StudentDTO studentDTO){
        ContentValues contentValues= new ContentValues();
        contentValues.put("studentName", studentDTO.getStudentName());
        contentValues.put("birthday",studentDTO.getBirthday());
        contentValues.put("numberPhone",studentDTO.getNumberPhone());
        contentValues.put("email",studentDTO.getEmail());
        contentValues.put("idClass",studentDTO.getIdClass());
        int res = sqLiteDatabase.update( "tb_Students", contentValues,"idStudent = ?", new String[] { studentDTO.getIdStudent() +"" } );
        return  res;
    }

    //Hàm lấy tất cả dữ liệu trong list
    public ArrayList<StudentDTO> selectAllStudent(){
        ArrayList<StudentDTO> listStudent = new ArrayList<>();
        //Lấy dữ liêu từ database
        String sql_Select = "SELECT * FROM tb_Students";
        //Thực thi

        Cursor cursor = sqLiteDatabase.rawQuery(sql_Select,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setIdStudent(cursor.getInt(0));
                studentDTO.setStudentName(cursor.getString(1));
                studentDTO.setBirthday(cursor.getString(2));
                studentDTO.setNumberPhone(cursor.getString(3));
                studentDTO.setEmail(cursor.getString(4));
                studentDTO.setIdClass(cursor.getInt(5));


                listStudent.add(studentDTO);

                cursor.moveToNext(); ///Phải cho vào while
            }
        }
        return listStudent;
    }







}
