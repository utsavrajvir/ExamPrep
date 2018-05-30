package com.utsavrajvir.arham;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string = null;
    JSONObject jsonObject1;
    JSONArray jsonArray1;
    ContactAdapter contactAdapter;
    ListView listView;
    private View view;

    /*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_display_list_view,container,false);

        String json_string = getArguments().getString("json_data");

        listView = (ListView)view.findViewById(R.id.listview);
        contactAdapter = new ContactAdapter(getContext().getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(contactAdapter);

        String s_id=null;
        try {
            jsonObject1 = new JSONObject(json_string);
            jsonArray1 = jsonObject1.getJSONArray("result");
            int count=0;

            while(count < jsonArray1.length())
            {
                JSONObject jo = jsonArray1.getJSONObject(count);
                s_id = jo.getString("St_Id");

                Contacts contacts = new Contacts(s_id);
                contactAdapter.add(contacts);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




        return view;
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);

        listView = (ListView)findViewById(R.id.listview);
        contactAdapter = new ContactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);

        json_string = getIntent().getExtras().getString("json_data");

        String M_Cid=null;
        String M_Name=null;
        String M_Time=null;
        try {
            jsonObject1 = new JSONObject(json_string);
            jsonArray1 = jsonObject1.getJSONArray("result");
            int count=0;

            while(count < jsonArray1.length())
            {
                JSONObject jo = jsonArray1.getJSONObject(count);
                M_Cid = jo.getString("M_Cid");
                M_Name = jo.getString("M_Name");
                M_Time = jo.getString("M_Time");
                Contacts contacts = new Contacts(M_Cid,M_Name,M_Time);
                contactAdapter.add(contacts);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
