package vn.edu.poly.mob1032_ass.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import vn.edu.poly.mob1032_ass.DAO.ClassDAO;
import vn.edu.poly.mob1032_ass.DTO.ClassDTO;
import vn.edu.poly.mob1032_ass.R;

public class ClassAdapter extends BaseAdapter {
    ArrayList<ClassDTO> listClass;
    ClassDAO classDAO;

    public ClassAdapter(ArrayList<ClassDTO> listClass, ClassDAO classDAO){
        this.listClass = listClass;
        this.classDAO = classDAO;
    }
    @Override
    public int getCount() {
        return listClass.size();
    }
    @Override
    public Object getItem(int position) {
        ClassDTO classDTO = listClass.get(position);
        return classDTO;
    }

    @Override
    public long getItemId(int position) {
        ClassDTO classDTO = listClass.get(position);
        return classDTO.getIdClass();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemClassView;
        // khởi tạo cho itemView
        if(convertView == null){
            itemClassView =View.inflate(parent.getContext(), R.layout.class_one_list_view,null);
        }else {
            itemClassView = convertView;
        }
        //Lấy thông tin bản ghi
        final ClassDTO  classDTO = listClass.get(position);
        final int index = position;

        //ánh xạ view
        TextView idClass= itemClassView.findViewById(R.id.tv_idClass);
        TextView nameClass= itemClassView.findViewById(R.id.tv_nameClass);
        ImageView imb_update = itemClassView.findViewById(R.id.imb_updateClass);
        ImageView imb_delete = itemClassView.findViewById(R.id.imb_deleteClass);


        //Set text
        idClass.setText(classDTO.getIdClass() + "");
        nameClass.setText(classDTO.getClassName());

        //Sự kiện bấm nút các thành phần dữ liệu
        nameClass.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClassDTO new_class = classDAO.selectOne(classDTO.getIdClass());
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Thông tin lớp");
                builder.setIcon(R.drawable.infoclass);
                builder.setMessage( "*------------------------------------------*"+"\n"+
                        " - Tên lớp: "+new_class.getClassName()+"\n"+"*------------------------------------------*");

                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }

        });

        imb_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Xóa lớp?");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Có chắc chắn xóa lớp: " + classDTO.getClassName());

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int kq = classDAO.deleteRow(classDTO);

                        if(kq > 0)
                        {
                            // xóa thành công trong csdl
                            listClass.remove(index); // xóa khỏi danh sách
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

        imb_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogEdit(classDTO,index,parent.getContext());
            }
        });

        return itemClassView;
    }

    //Phương thức thêm class
    public void showDialogAddClass(Context  context){
        final  Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_class);
        EditText ed_nameClass = dialog.findViewById(R.id.ed_ClassName);
        Button btn_saveClass = dialog.findViewById(R.id.btn_SaveClass);

        btn_saveClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.contactform);
                builder.setTitle("Confirm !!!");
                builder.setMessage("Xác nhận lưu thông tin lớp !");
                builder.setCancelable(true);

                String nameClass = ed_nameClass.getText().toString();

                ClassDTO classDTO = new ClassDTO();
                classDTO.setClassName(nameClass);

                long res = classDAO.insertNew(classDTO);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(res > 0){
                            listClass.clear();
                            listClass.addAll(classDAO.selectAllClass());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã thêm mới lớp ", Toast.LENGTH_SHORT).show();

                        }else {
                            if(nameClass.equals("")){
                                Toast.makeText(context, "Hãy nhập tên lớp ! ", Toast.LENGTH_SHORT).show();
                            }
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



    //phương thức update
    public void showDialogEdit(ClassDTO classDTO,int index ,Context context){
        final  Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_edit_class);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.contactform);
        builder.setTitle("Confirm !!!");
        builder.setMessage("Xác nhận sửa đổi thông tin lớp !");
        builder.setCancelable(true);

        EditText ed_nameUpdate = dialog.findViewById(R.id.ed_ClassName);
        ed_nameUpdate.setText(classDTO.getClassName());


        Button btn_SaveUpdate = dialog.findViewById(R.id.btn_SaveClass);
        btn_SaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classDTO.setClassName(ed_nameUpdate.getText().toString());
                int res = classDAO.updateRow(classDTO);

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(res > 0){
                            listClass.set(index,classDTO);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã sửa thông tin lớp !", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Không sửa được thông tin lớp !" + res, Toast.LENGTH_SHORT).show();
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


