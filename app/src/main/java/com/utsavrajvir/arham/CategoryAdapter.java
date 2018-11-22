package com.utsavrajvir.arham;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public CategoryAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        CategoryHolder categoryHolder;
        row = convertView;

        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            categoryHolder = new CategoryHolder();
            categoryHolder.txt1 = (TextView) row.findViewById(R.id.textview);
            row.setTag(categoryHolder);
        }else
        {
            categoryHolder = (CategoryHolder) row.getTag();
        }

        CategoryPojo categoryPojo = (CategoryPojo) this.getItem(position);
        categoryHolder.txt1.setText(categoryPojo.getCname());
        return row;
    }



    static class CategoryHolder{

        TextView txt1,txt2,txt3;
    }

}

