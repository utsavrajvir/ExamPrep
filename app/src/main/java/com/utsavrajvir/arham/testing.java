package com.utsavrajvir.arham;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class testing extends AppCompatActivity {

    TextView tx;

    String json_string = null;
    JSONObject jsonObject1;
    JSONArray jsonArray1;
    ContactAdapter contactAdapter;
    ListView listView;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.listview);
        contactAdapter = new ContactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);


        String M_Cid = getIntent().getExtras().getString("M_Cid");
        String M_Time = getIntent().getExtras().getString("M_Time");

        String s=null;

        BackgroundTask backgroundTask = new BackgroundTask(this);

        try {
            s = backgroundTask.execute("test",M_Cid).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //json_string = getIntent().getExtras().getString("json_data");

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();


        String T_id=null;
//        String M_Tid=null;
//        String M_Time1=null;
        try {
            jsonObject1 = new JSONObject(s);
            jsonArray1 = jsonObject1.getJSONArray("result");
            int count=0;

            while(count < jsonArray1.length())
            {
                JSONObject jo = jsonArray1.getJSONObject(count);
                T_id = jo.getString("T_id");
                //Contacts contacts = new Contacts("Test "+ (count+1));
                Contacts contacts = new Contacts(M_Cid,"Test "+ (count+1),M_Time,T_id);
                contactAdapter.add(contacts);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Contacts ss = (Contacts) parent.getItemAtPosition(position);


                //Toast.makeText(getContext().getApplicationContext(), ss.getM_Cid(), Toast.LENGTH_SHORT).show();

                Intent appInfo = new Intent(getApplicationContext(), category.class);

                appInfo.putExtra("M_Cid",ss.getM_Cid());
                appInfo.putExtra("T_id",ss.getId());
                //appInfo.putExtra("M_Name",ss.getM_Name());
                appInfo.putExtra("M_Time",ss.getM_Time());

                startActivity(appInfo);

                //startActivityForResult(appInfo,1);

            }
        });


    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
