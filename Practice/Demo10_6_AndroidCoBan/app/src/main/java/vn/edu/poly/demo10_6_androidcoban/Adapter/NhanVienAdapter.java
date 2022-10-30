package vn.edu.poly.demo10_6_androidcoban.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.demo10_6_androidcoban.DAO.NhanVienDAO;
import vn.edu.poly.demo10_6_androidcoban.DTO.NhanVienDTO;
import vn.edu.poly.demo10_6_androidcoban.R;

public class NhanVienAdapter extends BaseAdapter {
        ArrayList<NhanVienDTO> listNv;
        NhanVienDAO nhanVienDAO;

    public NhanVienAdapter(ArrayList<NhanVienDTO> listNv, NhanVienDAO nhanVienDAO) {
        this.listNv = listNv;
        this.nhanVienDAO = nhanVienDAO;
    }


    @Override
    public int getCount() {
        return listNv.size();
    }

    @Override
    public Object getItem(int position) {
        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        return nhanVienDTO;
    }

    @Override
    public long getItemId(int position) {
        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        return nhanVienDTO.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemListView;
        if(convertView == null){
            itemListView = View.inflate(parent.getContext(), R.layout.item_listview_nhanvien,null);
        }else{
            itemListView = convertView;
        }
        final  NhanVienDTO  nhanVienDTO  = listNv.get(position);
        final  int index = position;

        TextView id = itemListView.findViewById(R.id.tv_id);
        TextView name = itemListView.findViewById(R.id.tv_name);
        TextView delete = itemListView.findViewById(R.id.tv_delete);
        TextView update = itemListView.findViewById(R.id.tv_update);


        id.setText(nhanVienDTO.getId()+"");
        name.setText(nhanVienDTO.getFullName());

        name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                nhanVienDTO.setUserName(nhanVienDTO.getUserName());
                nhanVienDTO.setPassWord(nhanVienDTO.getPassWord());
                nhanVienDTO.setBirthday(nhanVienDTO.getBirthday());
                nhanVienDTO.setFullName(nhanVienDTO.getFullName());
                nhanVienDTO.setSalary(Double.parseDouble(String.valueOf(nhanVienDTO.getSalary())));
                return false;
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context;
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Xác nhận xóa");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            int res = nhanVienDAO.deleteRow(nhanVienDTO);
                            if(res >= 0){
                                listNv.remove(index);
                                notifyDataSetChanged();
                                Toast.makeText(parent.getContext(), "Đã xóa",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(parent.getContext(), "Không xóa được",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                        }

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(parent.getContext(), "Đã hủy",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return itemListView;
    }

    public void update (NhanVienDTO nhanVienDTO, int index, Context context){



    }


}



