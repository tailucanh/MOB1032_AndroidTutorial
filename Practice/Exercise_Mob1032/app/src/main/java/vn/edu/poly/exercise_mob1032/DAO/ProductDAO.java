package vn.edu.poly.exercise_mob1032.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import vn.edu.poly.exercise_mob1032.DATABASE.MyDbHelper;
import vn.edu.poly.exercise_mob1032.DTO.Products;

public class ProductDAO  {
        SQLiteDatabase db;
     MyDbHelper myDbHelper;

    public ProductDAO(Context context){myDbHelper = new MyDbHelper(context);}

    public void open(){
        db = myDbHelper.getWritableDatabase();
    }
    public void close(){
        myDbHelper.close();
    }


    public  long insertRow(Products products ){
        ContentValues values = new ContentValues();
        values.put("name",products.getName());
        values.put("price",products.getPrice());
        return  db.insert("tb_products",null,values);
    }

    public int deleteRow(Products products){


        String [] dk = new String[]{products.getId() +""};

     return  db.delete("tb_products","id=?",dk);

    }
    public int updateRow(Products products){
        ContentValues values = new ContentValues();

        values.put("name",products.getName());
        values.put("price",products.getPrice());
        String [] dk = new String[]{products.getId() +""};

        return  db.update("tb_products",values,"id = ?",dk);

    }
    public ArrayList<Products> sellectAll(){
        ArrayList<Products> listPr = new ArrayList<>();
        String sql_select = "SELECT * FROM tb_products";
        Cursor cursor =db.rawQuery(sql_select,null);

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Products products  = new Products();
                products.setId(cursor.getInt(0));
                products.setName(cursor.getString(1));
                products.setPrice(cursor.getDouble(2));

                listPr.add(products);
                cursor.moveToNext();
            }
        }
        return listPr;
    }


    public Products sellectOne(int id){
        Products products = null;
        String [] dk = new String[]{products.getId() +""};
        String sql_select = "SELECT id,name,price FROM tb_products where id = ?";

        Cursor cursor =db.rawQuery(sql_select,dk);

        if(cursor.moveToFirst()){
             products = new Products();
            products.setId(cursor.getInt(0));
             products.setName(cursor.getString(1));
             products.setPrice(cursor.getDouble(2));
        }

        return products;
    }



}
