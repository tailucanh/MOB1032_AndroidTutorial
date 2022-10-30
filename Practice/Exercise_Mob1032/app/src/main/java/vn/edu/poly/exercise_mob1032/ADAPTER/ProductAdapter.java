package vn.edu.poly.exercise_mob1032.ADAPTER;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.exercise_mob1032.DAO.ProductDAO;
import vn.edu.poly.exercise_mob1032.DTO.Products;
import vn.edu.poly.exercise_mob1032.R;

public class ProductAdapter extends BaseAdapter {
    ArrayList<Products> listPr;
    ProductDAO productDAO;

    public ProductAdapter(ArrayList<Products> listPr,ProductDAO productDAO){
        this.listPr = listPr;
        this.productDAO = productDAO;
    }

    @Override
    public int getCount() {
        return listPr.size();
    }

    @Override
    public Object getItem(int position) {
        Products product = listPr.get(position);
        return product;
    }

    @Override
    public long getItemId(int position) {
        Products product = listPr.get(position);
        return product.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View itemListPr;
        if(convertView == null){
            itemListPr = View.inflate(parent.getContext(), R.layout.product_item_listview,null);
        }else {
            itemListPr = convertView;
        }

        final  Products products = listPr.get(position);
        final  int index = position;

        TextView id = itemListPr.findViewById(R.id.tv_id);
        TextView name = itemListPr.findViewById(R.id.tv_name);
        TextView price = itemListPr.findViewById(R.id.tv_price);
        TextView delete = itemListPr.findViewById(R.id.tv_delete);
        TextView update = itemListPr.findViewById(R.id.tv_update);


        id.setText(products.getId()+"");
        name.setText(products.getName());
        price.setText("Giá: "+products.getPrice()+"");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Xác nhận xóa!");
                builder.setIcon(android.R.drawable.ic_delete);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int kq = productDAO.deleteRow(products);
                        if(kq >= 0){
                            listPr.remove(index);
                            notifyDataSetChanged();
                            Toast.makeText(parent.getContext(), "Xóa thành công! ",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(parent.getContext(), "Xóa không thành công! ",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(parent.getContext(), "Đã hủy! ",Toast.LENGTH_SHORT).show();
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
                update(products,index,parent.getContext());
            }
        });


        return itemListPr;
    }


    public void update(Products products,int index ,Context context){
        final  Dialog dialog =  new Dialog(context);
        dialog.setContentView(R.layout.dialog_update);


        EditText ed_name = dialog.findViewById( R.id.ed_name);
        EditText ed_price = dialog.findViewById(R.id.ed_price);
        ed_name.setText(products.getName());
        ed_price.setText(products.getPrice()+"");


        Button btn_update = dialog.findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products.setName(ed_name.getText().toString());
                products.setPrice(Double.parseDouble(ed_price.getText().toString()));

                int res = productDAO.updateRow(products);
                if(res > 0){
                    listPr.set(index,products);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã sửa ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Không sửa được ", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
    dialog.show();


    }


    public void insertPro(Context context){
        final  Dialog dialog =  new Dialog(context);
        dialog.setContentView(R.layout.dialog_insert);


        Button btn_save;
        EditText ed_name, ed_price;
        ed_name = dialog.findViewById( R.id.ed_name);
        ed_price = dialog.findViewById(R.id.ed_price);

        btn_save = dialog.findViewById(R.id.button_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Products products = new Products();
                products.setName(ed_name.getText().toString());
                products.setPrice(Double.parseDouble(ed_price.getText().toString()));

                long res = productDAO.insertRow(products);
                if(res > 0){
                    listPr.clear();
                    listPr.addAll(productDAO.sellectAll());
                    notifyDataSetChanged();
                    Toast.makeText(context,"Thêm mới thành công! ",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(context,"Thêm mới thất bại! ",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

}


