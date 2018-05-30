package com.utsavrajvir.arham;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentOne extends Fragment {

    String json_string;
    JSONObject jsonObject1;
    JSONArray jsonArray1;
    ContactAdapter contactAdapter;
    ListView listView;
    private View view;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_display_list_view,container,false);



        listView = (ListView)view.findViewById(R.id.listview);
        contactAdapter = new ContactAdapter(getContext().getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(contactAdapter);
        json_string = getArguments().getString("json_data");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Contacts ss = (Contacts) parent.getItemAtPosition(position);

                Intent appInfo = new Intent(getContext().getApplicationContext(), testing.class);
                appInfo.putExtra("testing",ss.getM_Name());
                //appInfo.putExtra("json",json_string);
                startActivity(appInfo);

                //startActivityForResult(appInfo,1);

               // Toast.makeText(getContext().getApplicationContext(), ss.getS_id(), Toast.LENGTH_SHORT).show();

            }
        });






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




        return view;
    }
}
