package vn.edu.poly.ph26495_th2.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.ph26495_th2.DAO.DanhBaDAO;
import vn.edu.poly.ph26495_th2.DTO.DanhBaDTO;
import vn.edu.poly.ph26495_th2.MainActivity;
import vn.edu.poly.ph26495_th2.R;

public class DanhBaAdapter extends BaseAdapter {

    final  ArrayList<DanhBaDTO> listdanhba;
    DanhBaDAO danhBaDAO;

    public DanhBaAdapter(ArrayList<DanhBaDTO> listdanhba,DanhBaDAO danhBaDAO) {
        this.listdanhba = listdanhba;
        this.danhBaDAO = danhBaDAO;
    }



    @Override
    public int getCount() {
        return listdanhba.size();
    }

    @Override
    public Object getItem(int position) {
        DanhBaDTO objdanhba = listdanhba.get(position);
        return  objdanhba;
    }

    @Override
    public long getItemId(int position) {
        DanhBaDTO objdanhba = listdanhba.get(position);
        return  objdanhba.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if (convertView == null) {
            row = View.inflate(parent.getContext(), R.layout.layout_lv_danhba, null);  //Chọn layout 1 dòng và tạo riêng layout
        } else{
            row = convertView;
        }
        DanhBaDTO objdanhba= listdanhba.get(position);

        //Ánh xạ
        TextView id = row.findViewById(R.id.tv_id);
        TextView name = row.findViewById(R.id.tv_ten);
        TextView sdt = row.findViewById(R.id.tv_sdt);
        TextView ghichu = row.findViewById(R.id.tv_ghichu);
        Button delete = row.findViewById(R.id.btn_delete);
        LinearLayout layout = row.findViewById(R.id.tv_info);

        //gán dữ liệu

        id.setText(objdanhba.getId() + "");
        name.setText(objdanhba.getTen());
        sdt.setText(objdanhba.getSdt());
        ghichu.setText(objdanhba.getGhiChu());

        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Thông tin");
                DanhBaDTO new_name = danhBaDAO.selectOne(objdanhba.getId());

                builder.setMessage("Họ và tên: "+new_name.getTen()+"\n"+"Số điện thoại: "+new_name.getSdt()+"\n"+"Ghi chú: "+new_name.getGhiChu());

                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });

        //Trả về dòng
        return  row;
    }
}
