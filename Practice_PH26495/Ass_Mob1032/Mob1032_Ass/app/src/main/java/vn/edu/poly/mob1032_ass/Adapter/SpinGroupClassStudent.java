package vn.edu.poly.mob1032_ass.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.mob1032_ass.DTO.ClassDTO;
import vn.edu.poly.mob1032_ass.R;

public class SpinGroupClassStudent extends BaseAdapter {
    ArrayList<ClassDTO> listClass ;

    public SpinGroupClassStudent(ArrayList<ClassDTO> listClass){
        this.listClass = listClass;
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
        View itemSpinClass;
        if(convertView == null){
            itemSpinClass = View.inflate(parent.getContext(), R.layout.item_spin_class,null);
        }else {
            itemSpinClass = convertView;
        }
        final  ClassDTO classDTO = listClass.get(position);


        TextView tv_idSpinnClass = itemSpinClass.findViewById(R.id.tv_IdSpinClass);
        TextView tv_nameSpinnClass = itemSpinClass.findViewById(R.id.tv_spinNameClass);

        tv_idSpinnClass.setText(classDTO.getIdClass() + "");
        tv_nameSpinnClass.setText(classDTO.getClassName());


        return itemSpinClass;
    }
}
