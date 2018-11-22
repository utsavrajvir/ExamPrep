package com.utsavrajvir.arham;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Sub_Category extends AppCompatActivity {

    String json_string = null;
    JSONObject jsonObject1;
    JSONArray jsonArray1;
    SubCategoryAdapter contactAdapter;
    ListView listView;
    private View view;
    String C_Id, M_Cid,T_Id;
    String Sc_id;
    String Sc_Name;
    String Sc_Time;
    String C_Time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.category_listview);
        contactAdapter = new SubCategoryAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);



       C_Id = getIntent().getExtras().getString("C_Id");
         M_Cid = getIntent().getExtras().getString("M_Cid");
         T_Id = getIntent().getExtras().getString("T_Id");
         C_Time = getIntent().getExtras().getString("C_Time");


        String s=null;

        /*BackgroundTask backgroundTask = new BackgroundTask(this);

        try {
            s = backgroundTask.execute("sub_category",C_Id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL+"maincat/"+M_Cid+"/test/"+T_Id+"/category/"+C_Id+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api api = retrofit.create(Api.class);

        Call<List<SubCategoryPojo>> call = api.getSubCategory();

        call.enqueue(new Callback<List<SubCategoryPojo>>() {

            @Override
            public void onResponse(Call<List<SubCategoryPojo>> call, Response<List<SubCategoryPojo>> response) {
                List<SubCategoryPojo> heroList = response.body();

                for(SubCategoryPojo cs : heroList){
                    contactAdapter.add(cs);
                }
            }

            @Override
            public void onFailure(Call<List<SubCategoryPojo>> call, Throwable t) {

            }
        });

        //json_string = getIntent().getExtras().getString("json_data");

        Toast.makeText(this, M_Cid + " "+ T_Id + " "+C_Id, Toast.LENGTH_SHORT).show();


      /*  Sc_id=null;
        Sc_Name=null;
        Sc_Time=null;
        try {
            jsonObject1 = new JSONObject(s);
            jsonArray1 = jsonObject1.getJSONArray("result");
            int count=0;

            while(count < jsonArray1.length())
            {
                JSONObject jo = jsonArray1.getJSONObject(count);
                Sc_id = jo.getString("Sc_Id");
                Sc_Name = jo.getString("Sc_Name");
                Sc_Time = jo.getString("Sc_Time");
                Contacts contacts = new Contacts(Sc_id,Sc_Name,Sc_Time);
                contactAdapter.add(contacts);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
*/



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

                if(netInfo == null){

                    finish();
                    Intent intent = new Intent(getApplicationContext(),Network.class);
                    intent.putExtra("name","First");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }

                Contacts ss = (Contacts) parent.getItemAtPosition(position);

                //Toast.makeText(getContext().getApplicationContext(), ss.getM_Cid(), Toast.LENGTH_SHORT).show();

                Intent appInfo = new Intent(getApplicationContext(), Instruction.class);

                appInfo.putExtra("M_id",M_Cid);
                appInfo.putExtra("T_id",T_Id);
                appInfo.putExtra("cat","sub_category1");

                appInfo.putExtra("Sc_Id",ss.getMcid());
                appInfo.putExtra("Sc_Time",ss.getMtime());
                appInfo.putExtra("C_id",C_Id);


                startActivity(appInfo);

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


    public void Questions(View view) {



        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null){

            finish();
            Intent intent1 = new Intent(getApplicationContext(),Network.class);
            intent1.putExtra("name","First");
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent1);

        }


        Intent intent = new Intent(this,Instruction.class);
        intent.putExtra("M_id",M_Cid);
        intent.putExtra("T_id",T_Id);
        intent.putExtra("C_id",C_Id);
        intent.putExtra("C_Time",C_Time);
        intent.putExtra("cat","sub_category");
        startActivity(intent);

        /*Intent intent = new Intent(this,Display_Questions.class);

        intent.putExtra("M_id",M_Cid);
        intent.putExtra("T_id",T_Id);

        startActivity(intent);
*/
    }
}
