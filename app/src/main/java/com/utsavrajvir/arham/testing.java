package com.utsavrajvir.arham;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class testing extends AppCompatActivity {

    TextView tx;

    String json_string = null;
    JSONObject jsonObject1;
    JSONArray jsonArray1;
    ContactAdapter contactAdapter;
    ListView listView;
    private View view;
    String M_Time;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar)findViewById(R.id.progress_display);

        pref = getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);
        editor  = pref.edit();

        listView = (ListView)findViewById(R.id.listview);
        contactAdapter = new ContactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);


        String M_Cid = getIntent().getExtras().getString("M_Cid");
         M_Time = getIntent().getExtras().getString("M_Time");

        String s=null;

        progressBar.setVisibility(View.VISIBLE);

/*        BackgroundTask backgroundTask = new BackgroundTask(this);

        try {
            s = backgroundTask.execute("test",M_Cid).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/
       String str1 = Api.BASE_URL+"/maincats/"+M_Cid+"/";

        Toast.makeText(this, str1, Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(str1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("str1",str1);
        final Api api = retrofit.create(Api.class);

        Call<List<Test>> call = api.getTesting();

        call.enqueue(new Callback<List<Test>>() {
            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {

                 List<Test> heroList = response.body();

                for(Test cs : heroList){
                    contactAdapter.add(cs);
                }
            }

            @Override
            public void onFailure(Call<List<Test>> call, Throwable t) {

            }
        });


        //json_string = getIntent().getExtras().getString("json_data");

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();


       /* String T_id=null;
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
        }*/

        progressBar.setVisibility(View.GONE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

                NetworkInfo wifi = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if(netInfo == null || wifi == null){

                    Intent intent = new Intent(getApplicationContext(),Network.class);
                    intent.putExtra("email","Main");
                    startActivity(intent);

                }else{
                    Test test = (Test) parent.getItemAtPosition(position);
                    //Contacts ss = (Contacts) parent.getItemAtPosition(position);

                    //Toast.makeText(getContext().getApplicationContext(), ss.getM_Cid(), Toast.LENGTH_SHORT).show();

                    Intent appInfo = new Intent(getApplicationContext(), category.class);

                    appInfo.putExtra("M_Cid",test.getMid());//ss.getMcid()
                    appInfo.putExtra("T_id",test.getTid());//ss.getId()
                    //appInfo.putExtra("M_Name",ss.getM_Name());
                    appInfo.putExtra("M_Time",M_Time);//ss.getMtime()

                    startActivity(appInfo);

                }
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
