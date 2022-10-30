package vn.edu.poly.mob1032_ass.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html.ImageGetter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.mob1032_ass.DAO.ClassDAO;
import vn.edu.poly.mob1032_ass.DAO.StudentDAO;
import vn.edu.poly.mob1032_ass.DTO.ClassDTO;
import vn.edu.poly.mob1032_ass.DTO.StudentDTO;
import vn.edu.poly.mob1032_ass.R;

public class StudentAdapter extends BaseAdapter {
    ArrayList<StudentDTO> listStudent;
    StudentDAO studentDAO;

    public StudentAdapter(ArrayList<StudentDTO> listStudent,StudentDAO studentDAO){
        this.listStudent = listStudent;
        this.studentDAO = studentDAO;
    }


    @Override
    public int getCount() {
        return listStudent.size();
    }

    @Override
    public Object getItem(int position) {
        StudentDTO studentDTO = listStudent.get(position);
        return studentDTO;
    }

    @Override
    public long getItemId(int position) {
        StudentDTO studentDTO = listStudent.get(position);
        return studentDTO.getIdStudent();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemStudentView;
        if(convertView == null){
            itemStudentView = View.inflate(parent.getContext(), R.layout.user_one_list_view,null);
        }else {
            itemStudentView = convertView;
        }

        final  StudentDTO studentDTO = listStudent.get(position);
        final int index = position;

        TextView tv_idStudent = itemStudentView.findViewById(R.id.tv_idUser);
        TextView tv_nameStudent = itemStudentView.findViewById(R.id.tv_nameUser);
        ImageView imb_update = itemStudentView.findViewById(R.id.imb_UpdateStudent);
        ImageView imb_delete = itemStudentView.findViewById(R.id.imb_deleteStudent);

        tv_idStudent.setText(studentDTO.getIdStudent() + "");
        tv_nameStudent.setText(studentDTO.getStudentName());

        tv_nameStudent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Thông tin sinh viên");
                builder.setIcon(R.drawable.infostudent);
                StudentDTO show_Student =  studentDAO.selectOneWithGroup(studentDTO.getIdStudent());
                builder.setMessage("*------------------------------------------*"+"\n"+ "  - ID: "+show_Student.getIdStudent()+"\n"+
                        "  - Họ và tên: "+show_Student.getStudentName()+"\n"+
                        "  - Ngày sinh: "+show_Student.getBirthday()+"\n"+"  - Số điện thoại: "+show_Student.getNumberPhone()+"\n"+
                        "  - Email: "+show_Student.getEmail() +"\n"+"  - Lớp: "+show_Student.getClassName()+"\n"+
                        "\"*------------------------------------------*\"");



                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });
        imb_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogEditStudent(studentDTO,index,parent.getContext());
            }
        });

        imb_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Xóa sinh viên?");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Có chắc chắn xóa sinh viên: " + studentDTO.getStudentName());

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int kq = studentDAO.deleteRow(studentDTO);

                        if(kq > 0)
                        {
                            // xóa thành công trong csdl
                            listStudent.remove(index); // xóa khỏi danh sách
                            notifyDataSetChanged();
                            Toast.makeText(parent.getContext(), "Đã xóa lớp", Toast.LENGTH_SHORT).show();

                        }else
                            Toast.makeText(parent.getContext(), "Không xóa được lớp " + kq, Toast.LENGTH_SHORT).show();

                        dialogInterface.dismiss(); // đóng dialog

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(parent.getContext(), "Đã hủy", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss(); // đóng dialog
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        return itemStudentView;
    }

    //Hàm thêm sinh viên

    public void showDialogAddStudent(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_user);
        EditText ed_nameStudent = dialog.findViewById(R.id.ed_UserName);
        EditText ed_phone = dialog.findViewById(R.id.ed_phoneUser);
        EditText ed_email = dialog.findViewById(R.id.ed_emailUser);
        EditText ed_birthday = dialog.findViewById(R.id.ed_dateuser);

        Button btn_saveStudent= dialog.findViewById(R.id.btn_SaveUser);

        //LÀM SPIN CHỌN
        final Spinner spinner_class = dialog.findViewById(R.id.spin_group);
        ClassDAO classDAO = new ClassDAO(context);
        classDAO.open();

        SpinGroupClassStudent adapter = new SpinGroupClassStudent(classDAO.selectAllClass());
        spinner_class.setAdapter(adapter);

        btn_saveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.userconfirm);
                builder.setTitle("Confirm !!!");
                builder.setMessage("Xác nhận lưu sinh viên!");
                builder.setCancelable(true);

                String nameStudent = ed_nameStudent.getText().toString();
                String birthday = ed_birthday.getText().toString();
                String email = ed_email.getText().toString();
                String phone = ed_phone.getText().toString();

                StudentDTO studentDTO = new StudentDTO();

                //lấy id spin chọn
                ClassDTO classDTO = (ClassDTO) spinner_class.getSelectedItem();
                studentDTO.setIdClass(classDTO.getIdClass());


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(nameStudent.equals("")){
                            Toast.makeText(context, "Hãy nhập tên!",Toast.LENGTH_SHORT).show();
                        }else if(birthday.equals("")){
                            Toast.makeText(context, "Hãy nhập ngày sinh!",Toast.LENGTH_SHORT).show();
                        }else if(phone.equals("")) {
                            Toast.makeText(context, "Hãy nhập số điện thoại!", Toast.LENGTH_SHORT).show();
                        }else if(phone.length() > 10 || phone.length() < 10){
                            Toast.makeText(context, "Hãy nhập số điện thoại 10 số!",Toast.LENGTH_SHORT).show();
                        } else if(email.equals("")){
                            Toast.makeText(context, "Hãy nhập email!",Toast.LENGTH_SHORT).show();
                        }else if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                            Toast.makeText(context, "Hãy nhập email đúng định dạng!",Toast.LENGTH_SHORT).show();
                        } else {
                            studentDTO.setStudentName(nameStudent);
                            studentDTO.setBirthday(birthday);
                            studentDTO.setNumberPhone(phone);
                            studentDTO.setEmail(email);
                            long res = studentDAO.insertNew(studentDTO);
                            if (res > 0) {
                                listStudent.clear();
                                listStudent.addAll(studentDAO.selectAllStudent());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Đã thêm sinh viên mới ", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Không thêm được sinh viên ", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }

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
        });

        dialog.show();
    }

    //HÀm update sinh viên
    public void showDialogEditStudent(StudentDTO studentDTO,int index ,Context context){
        final  Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_edit_user);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.infostudent);
        builder.setTitle("Confirm !!!");
        builder.setMessage("Xác nhận thay đổi thông tin sinh viên !");
        builder.setCancelable(true);

        EditText ed_nameUpdate = dialog.findViewById(R.id.ed_UserName);
        EditText ed_phoneUpdate = dialog.findViewById(R.id.ed_phoneUser);
        EditText ed_dateUpdate = dialog.findViewById(R.id.ed_dateuser);
        EditText ed_emailUpdate = dialog.findViewById(R.id.ed_emailUser);

        ed_nameUpdate.setText(studentDTO.getStudentName());
        ed_phoneUpdate.setText(studentDTO.getNumberPhone());
        ed_dateUpdate.setText(studentDTO.getBirthday());
        ed_emailUpdate.setText(studentDTO.getEmail());

        final  Spinner spinner_class = dialog.findViewById(R.id.spin_group);
        ClassDAO classDAO = new ClassDAO(context);
        classDAO.open();

        ArrayList<ClassDTO> listClass = classDAO.selectAllClass();
        SpinGroupClassStudent adapter = new SpinGroupClassStudent(listClass);
        spinner_class.setAdapter(adapter);

        for(int i = 0; i < listClass.size(); i++){
            ClassDTO temp = listClass.get(i);
            if(temp.getIdClass() == studentDTO.getIdStudent()){
                spinner_class.setSelection(i);
                spinner_class.setSelected(true);
            }
        }


        Button btn_UpdateUr = dialog.findViewById(R.id.btn_UpdateUser);
        btn_UpdateUr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentDTO.setStudentName(ed_nameUpdate.getText().toString());
                studentDTO.setBirthday(ed_dateUpdate.getText().toString());
                studentDTO.setNumberPhone(ed_phoneUpdate.getText().toString());
                studentDTO.setEmail(ed_emailUpdate.getText().toString());

                //lấy id spin group
                ClassDTO classDTO = (ClassDTO) spinner_class.getSelectedItem();
                studentDTO.setIdClass(classDTO.getIdClass());

                int res = studentDAO.updateRow(studentDTO);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(res > 0){
                            listStudent.set(index,studentDTO);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã sửa thông tin sinh viên!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Không sửa được thông tin sinh viên !" + res, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
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
        });
        dialog.show();

    }

}
