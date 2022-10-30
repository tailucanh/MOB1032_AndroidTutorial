package vn.edu.poly.demo_sqlite.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.demo_sqlite.DTO.CatDTO;
import vn.edu.poly.demo_sqlite.R;

public class CategoryAdapter extends BaseAdapter {

    ArrayList<CatDTO> listCat;

    public CategoryAdapter(ArrayList<CatDTO> listCat) {
        this.listCat = listCat;
    }

    @Override
    public int getCount() {
        return listCat.size();
    }

    @Override
    public Object getItem(int position) {
        CatDTO objCat = listCat.get(position);
        return  objCat;
    }

    @Override
    public long getItemId(int position) {
        CatDTO objCat = listCat.get(position);
        return  objCat.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if (convertView == null) {
            row = View.inflate(parent.getContext(), R.layout.layout_listview_items, null);
        } else{
            row = convertView;
     }

        //Ánh xạ
        TextView id = row.findViewById(R.id.tv_id);
        TextView name = row.findViewById(R.id.tv_name);
        TextView update = row.findViewById(R.id.tv_update);
        TextView delete = row.findViewById(R.id.tv_delete);

        //gán dữ liệu
        CatDTO objCat = listCat.get(position);
        id.setText(objCat.getId() + "");
        name.setText(objCat.getName());

        //Trả về dòng
        return  row;
    }
}
