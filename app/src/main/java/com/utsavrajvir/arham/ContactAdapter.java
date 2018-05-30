package com.utsavrajvir.arham;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends ArrayAdapter {

    List list = new ArrayList();


    public ContactAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(Contacts object) {
        //super.add(object);

        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        ContactHolder contactHolder;
        row = convertView;
        
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.M_Cid = (TextView) row.findViewById(R.id.textview);

            row.setTag(contactHolder);
        }
        else
        {
            contactHolder = (ContactHolder)row.getTag();
        }

        Contacts contacts = (Contacts) this.getItem(position);
        contactHolder.M_Cid.setText(contacts.getM_Name());

        return row;
    }


    static  class ContactHolder
    {
        TextView M_Cid,M_Name,M_Time;
    }


}
