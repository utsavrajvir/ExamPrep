package com.utsavrajvir.arham;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FragmentOne extends Fragment {

    String json_string;
    JSONObject jsonObject1;
    JSONArray jsonArray1;
    ContactAdapter contactAdapter;
    ListView listView;
    private View view;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.activity_display_list_view,container,false);


        pref = getActivity().getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);
        editor  = pref.edit();

        listView = (ListView)view.findViewById(R.id.listview);
        contactAdapter = new ContactAdapter(getContext().getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(contactAdapter);

        //json_string = getArguments().getString("json_data");

        List<Contacts> ls = FragmentAdapter.list_data;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ConnectivityManager conMgr = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
                NetworkInfo wifi = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if(netInfo == null || wifi == null ){

                    Intent intent = new Intent(getContext(),Network.class);
                    intent.putExtra("name","Main");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else{

                    Contacts ss = (Contacts) parent.getItemAtPosition(position);

                    //Toast.makeText(getContext().getApplicationContext(), ss.getM_Cid(), Toast.LENGTH_SHORT).show();

                    Intent appInfo = new Intent(getContext().getApplicationContext(), testing.class);

                    appInfo.putExtra("M_Cid",ss.getMcid());
                    appInfo.putExtra("M_Name",ss.getMname());
                    appInfo.putExtra("M_Time",ss.getMtime());

                    startActivity(appInfo);

                }

                //startActivityForResult(appInfo,1);

            }
        });

        for(Contacts cs : ls){
            contactAdapter.add(cs);

        }

       /* String M_Cid=null;
        String M_Name=null;
        String M_Time=null;
        try {

           // List<Contacts> ls = new ArrayList<Contacts>(json_string);
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
*/


        return view;
    }
}
