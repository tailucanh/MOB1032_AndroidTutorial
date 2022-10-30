package vn.edu.poly.ph26495_th2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import vn.edu.poly.ph26495_th2.Adapter.DanhBaAdapter;
import vn.edu.poly.ph26495_th2.DAO.DanhBaDAO;
import vn.edu.poly.ph26495_th2.DTO.DanhBaDTO;

public class MainActivity extends AppCompatActivity {

    EditText ed_name,ed_phone,ed_note;

    ListView lv_danhba;
    DanhBaDAO objdao;
    DanhBaDTO objdto;
    DanhBaAdapter adapter;
    String TAG = "TTTTTT";
    ArrayList<DanhBaDTO> listDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_danhba = findViewById(R.id.lv_danhba);


        ed_name = findViewById(R.id.ed_name);
        ed_phone = findViewById(R.id.ed_phone);
        ed_note = findViewById(R.id.ed_note);

        objdao = new DanhBaDAO(this);
        objdao.open();
        adapter = new DanhBaAdapter(objdao.selectAll(),objdao);
        lv_danhba.setAdapter(adapter);


    }
    @Override
    protected  void onDestroy(){
        super.onDestroy();
        objdao.close();
    }



    public void AddRow(View view){
        listDb = new ArrayList<>();
         DanhBaDTO danhBaDTO = new DanhBaDTO();
         danhBaDTO.setTen(ed_name.getText().toString());
        danhBaDTO.setSdt(ed_phone.getText().toString());
        danhBaDTO.setGhiChu(ed_note.getText().toString());

    long res = objdao.insertRow(danhBaDTO);
        if(res > 0){
            listDb.clear();
            listDb.addAll(objdao.selectAll());
            adapter.notifyDataSetChanged();
            Toast.makeText(this,"Thêm mới thành công! "+res,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Thêm mới thất bại! ",Toast.LENGTH_SHORT).show();
        }





    }

    public void UpdateRow(View view){

        String name_new = ed_name.getText().toString();
        String phone_new = ed_phone.getText().toString();
        String note_new = ed_note.getText().toString();

        if(objdto != null && (!objdto.getTen().equalsIgnoreCase(name_new) || !objdto.getSdt().equalsIgnoreCase(phone_new) || !objdto.getGhiChu().equalsIgnoreCase(note_new))) {
            objdto.setTen(name_new);
            objdto.setSdt(phone_new);
            objdto.setGhiChu(note_new);

            int res = objdao.updateRow(objdto);
            if (res > 0) {
                ed_name.setText("");
                ed_phone.setText("");
                ed_note.setText("");

                listDb.clear();
                listDb.addAll(objdao.selectAll());
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Cập nhật thành công! ", Toast.LENGTH_SHORT).show();
                objdto = null;

            } else {
                Toast.makeText(this, "Cập nhật không thành công! ", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Không có thay đổi để cập nhật! ", Toast.LENGTH_SHORT).show();
        }
    }

    public void DeleteRow(View view){
        Button btnDelete =  findViewById(R.id.btn_delete);
        if(objdto != null) {
            int res = objdao.deleteRow(objdto);
            if (res > 0) {
                ed_name.setText("");
                ed_phone.setText("");
                ed_note.setText("");

                listDb.clear();
                listDb.addAll(objdao.selectAll());
                adapter.notifyDataSetChanged();

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Xóa thông tin");
                        builder.setIcon(android.R.drawable.ic_delete);
                        builder.setMessage("Chắc chắn xóa "+objdto.getTen());

                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int kq = objdao.deleteRow(objdto);
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });

                ed_name.setText("");
                ed_phone.setText("");
                ed_note.setText("");
                Toast.makeText(this, "Xóa thành công! " + objdto.getTen(), Toast.LENGTH_SHORT).show();
                objdto = null;

            } else {
                Toast.makeText(this, "Xóa không thành công! ", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Hãy nhấn giữ một bản ghi trước khi xóa ! ", Toast.LENGTH_SHORT).show();
        }
    }
}