package vn.edu.poly.vidu6_listview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Product_Adapter extends BaseAdapter {

    ArrayList<Products> listsPr;

    public Product_Adapter(ArrayList<Products> listProduct) {
        this.listsPr = listProduct;
    }


    @Override
    public int getCount() {
        return listsPr.size();   //Trả về số lượng phần tử
    }

    @Override
    public Object getItem(int position) {
        Products objPr = listsPr.get( position);  //Trả về 1 phần tử
        return objPr;
    }

    @Override
    public long getItemId(int position) {
        Products objPr = listsPr.get( position);  //Trả về id của phần tử
        return objPr.id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Trả về 1 view là dòng trong danh sách.

        View row;
        if(convertView == null){
            row = View.inflate(parent.getContext(),R.layout.item_row_list_info,null);

        }else {
            row = convertView;
        }
            //Lấy dữ liệu về phần tử hiện tại
        Products objPr = listsPr.get(position);
        //Ánh xạ view
        TextView tv_Name = row.findViewById(R.id.tv_Name);
        TextView tv_Price = row.findViewById(R.id.tv_Price);
        ImageView img = row.findViewById(R.id.img_1);
        //Gán dữ liệu lên để hiển thị

        tv_Name.setText(objPr.name);   //trong file product
        tv_Price.setText(objPr.price + "");
        img.setImageResource(objPr.img_res);


        //Trả về dòng được tùy chỉnh
        return row;
    }
}
