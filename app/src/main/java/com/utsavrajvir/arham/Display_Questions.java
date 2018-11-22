package com.utsavrajvir.arham;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Display_Questions extends AppCompatActivity {

    String json_string = null;
    JSONObject jsonObject1;
    JSONArray jsonArray1;
   // ContactAdapter contactAdapter;
    ListView listView;
    private View view;
    QuestionsAdapter questionsAdapter;
    TextView tv;
    int secondsLeft;
    String C_Time,M_Time;
    String cat;
    RadioGroup radio;
    String Sc_Time,Sc_Id,C_Id;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    JSONArray jsonArray;
    ProgressBar progressBar;
    List<Questions> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__questions);

        Toolbar mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);

        progressBar = (ProgressBar)findViewById(R.id.progress_question);
questionsList = new ArrayList<Questions>();
        radio = (RadioGroup)findViewById(R.id.radioGroup);

        pref = getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);
        editor  = pref.edit();

        this.setTitle("");

        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Display_Questions.this, "Hello", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Display_Questions.this);

                // Setting Dialog Title
                alertDialog.setTitle("Confirm Quit...");

                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want Quit this?");

                // Setting Icon to Dialog
                // alertDialog.setIcon(R.drawable.delete);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        // Write your code here to invoke YES event
                       // Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                       // Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();

                        /*int i = questionsAdapter.getCount()-1;
                        int count =0;

                       jsonArray = new JSONArray();
                        Questions questions = new Questions();

                        while(i-- >= 0)
                        {
                            JSONObject  json_object  = new JSONObject();
                            try {

                                questions = (Questions) questionsAdapter.getItem(count++);


                                json_object.put("St_Id", pref.getString("St_Id","-1"));
                                json_object.put("Qc_Id", questions.getQ_id());
                                json_object.put("Stud_Answer", questions.getStud_answer());


                                jsonArray.put(json_object);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        //Log.i("json_array",jsonObject.toString());
                        Log.i("json_array1",jsonArray.toString());

                       *//* JSONObject jsonObject = null;

                        try {
                           jsonObject.put("result",jsonArray.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*//*

                        //Log.i("json_array",jsonObject.toString());
                       // Log.i("json_array1",jsonArray.toString());

                        //Toast.makeText(Display_Questions.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();


                       BackgroundTask backgroundTask1 = new BackgroundTask(getApplicationContext());

                        backgroundTask1.execute("student_history",jsonArray.toString());
*/
                        NavUtils.navigateUpFromSameTask(Display_Questions.this);

                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();


            }
        });

        listView = (ListView)findViewById(R.id.question_listview);
        questionsAdapter = new QuestionsAdapter(this,R.layout.display_question_row);
        listView.setAdapter(questionsAdapter);


        //Toast.makeText(Display_Questions.this, radioButton.getText(), Toast.LENGTH_SHORT).show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Questions questions = (Questions) parent.getItemAtPosition(position);

                //Toast.makeText(Display_Questions.this, questions.getOptionA() + " " + questions.getOptionB() + " " + questions.getOptionC()+ " "+questions.getOptionD(), Toast.LENGTH_SHORT).show();


            }
        });


        String M_Cid = getIntent().getExtras().getString("M_id");
        String T_id = getIntent().getExtras().getString("T_id");
        cat = getIntent().getExtras().getString("cat");


        String s=null;

        progressBar.setVisibility(View.VISIBLE);

        BackgroundTask backgroundTask = new BackgroundTask(this);

        //try {
            if(cat.equals("category"))
            {
                Toast.makeText(this, "Enter....Mid = "+M_Cid+" T_id = "+T_id, Toast.LENGTH_SHORT).show();
                M_Time = getIntent().getExtras().getString("M_Time");

                //s = backgroundTask.execute("question2",M_Cid,T_id).get();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL+"maincat/"+M_Cid+"/"+"test/"+T_id+"/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final Api api = retrofit.create(Api.class);

                Call<List<Questions>> call = api.getMainSection();

                call.enqueue(new Callback<List<Questions>>() {
                    @Override
                    public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {

                        List<Questions> heroList = response.body();

                        for(Questions cs : heroList){
                            questionsAdapter.add(cs);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Questions>> call, Throwable t) {

                    }
                });

                //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            }
            else if(cat.equals("sub_category"))
            {
                C_Time = getIntent().getExtras().getString("C_Time");
                String C_id = getIntent().getExtras().getString("C_id");
                //s = backgroundTask.execute("question3",M_Cid,T_id,C_id).get();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL+"maincat/"+M_Cid+"/"+"test/"+T_id+"/category/"+C_id)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final Api api = retrofit.create(Api.class);

                Call<List<Questions>> call = api.getCategoryQuestion();

                call.enqueue(new Callback<List<Questions>>() {
                    @Override
                    public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {

                        List<Questions> heroList = response.body();

                        for(Questions cs : heroList){
                            questionsAdapter.add(cs);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Questions>> call, Throwable t) {

                    }
                });
            }
            else if(cat.equals("sub_category1"))
            {
                Sc_Time = getIntent().getExtras().getString("Sc_Time");
                Sc_Id = getIntent().getExtras().getString("Sc_id");
                String C_id = getIntent().getExtras().getString("C_id");
                //s = backgroundTask.execute("question4",M_Cid,T_id,C_id,Sc_Id).get();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL+"maincat/"+M_Cid+"/"+"test/"+T_id+"/category/"+C_id+"/subcategory/"+Sc_Id)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final Api api = retrofit.create(Api.class);

                Call<List<Questions>> call = api.getCategoryQuestion();

                call.enqueue(new Callback<List<Questions>>() {
                    @Override
                    public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {

                        List<Questions> heroList = response.body();

                        for(Questions cs : heroList){
                            questionsAdapter.add(cs);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Questions>> call, Throwable t) {

                    }
                });

            }

       /* } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/

        //json_string = getIntent().getExtras().getString("json_data");

        //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();


        /*String Q_id=null;
        String question=null;
        String optionA=null;
        String optionB=null;
        String optionC=null;
        String optionD=null;
        String questionAns=null;
        try {
            jsonObject1 = new JSONObject(s);
            jsonArray1 = jsonObject1.getJSONArray("result");
            int count=0;

            while(count < jsonArray1.length())
            {
                JSONObject jo = jsonArray1.getJSONObject(count);
                Q_id = jo.getString("Q_Id");
                question = jo.getString("Q_Detail");
                optionA = jo.getString("Q_OptionA");
                optionB = jo.getString("Q_OptionB");
                optionC = jo.getString("Q_OptionC");
                optionD = jo.getString("Q_OptionD");
                questionAns = jo.getString("Q_CorrectAns");

               Questions questions = new Questions(Q_id,question,optionA,optionB,optionC,optionD,questionAns,String.valueOf(count+1));
               questionsAdapter.add(questions);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
        progressBar.setVisibility(View.GONE);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                Toast.makeText(this, "Try", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
                return true;
        }
        //return super.onOptionsItemSelected(item);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.question_layout, menu);

        tv = new TextView(this);
//        tv.setText("");
        tv.setTextColor(getResources().getColor(R.color.colorModified));
        // tv.setOnClickListener(this);
        tv.setPadding(5, 0, 5, 0);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setTextSize(14);
        menu.add(0, 1, 1, "").setActionView(tv).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        Long time1=null;

        if(cat.equals("category")) {
            String[] time = M_Time.split(":");
            Log.i("category",time[0]);
            time1 = Long.parseLong(time[0]) * 3600000;
        }
        else if(cat.equals("sub_category")) {
            String[] time = C_Time.split(":");
            time1 = Long.parseLong(time[1]) * 60000;
        }
        else if(cat.equals("sub_category1")) {
            String[] time = Sc_Time.split(":");
            time1 = Long.parseLong(time[1]) * 60000;
        }

        startTimer(time1,1000);
        return true;
    }


    private void startTimer(long duration, long interval) {

        CountDownTimer timer = new CountDownTimer(duration, interval) {

            @Override
            public void onFinish() {
                //TODO Whatever's meant to happen when it finishes
                tv.setText("Done!");
            }

            @Override
            public void onTick(long millisecondsLeft) {
                secondsLeft = (int) Math.round((millisecondsLeft / (double) 1000));

                Log.i("seconds", String.valueOf(secondsLeft));


                tv.setText(secondsToString(secondsLeft));
            }
        };

        timer.start();
    }


    @SuppressWarnings("deprecation")
    private String secondsToString(int improperSeconds) {

        //Seconds must be fewer than are in a day

        Time secConverter = new Time(30000);


        secConverter.setHours(0);

        //secConverter.hour = 0;
        secConverter.setMinutes(0);
        secConverter.setSeconds(0);

        secConverter.setSeconds(improperSeconds);
        //secConverter.normalize(true);


        String hours = String.valueOf(secConverter.getHours());
        String minutes = String.valueOf(secConverter.getMinutes());
        String seconds = String.valueOf(secConverter.getSeconds());

        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        if (hours.length() < 2) {
            hours = "0" + hours;
        }

        String timeString = hours + ":" + minutes + ":" + seconds;
        return timeString;
    }

    public void submit(View view) {

        int i = questionsAdapter.getCount()-1;
        int count =0;

        jsonArray = new JSONArray();
        Questions questions = new Questions();

        while(i-- >= 0)
        {
            /*JSONObject  json_object  = new JSONObject();
            try {
*/
                questions = (Questions) questionsAdapter.getItem(count++);
                    questionsList.add(questions);

  /*              json_object.put("St_Id", pref.getString("St_Id","-1"));
                json_object.put("Qc_Id", questions.getQ_id());
                json_object.put("Stud_Answer", questions.getStud_answer());

                //Toast.makeText(this, pref.getString("St_Id","-1") + questions.getQ_id() + questions.getStud_answer(), Toast.LENGTH_SHORT).show();

                jsonArray.put(json_object);
*/
  /*          } catch (JSONException e) {
                e.printStackTrace();
            }
*/
        }
  //      Log.i("json_array1",jsonArray.toString());

/*
        BackgroundTask backgroundTask1 = new BackgroundTask(getApplicationContext());
        backgroundTask1.execute("student_history",jsonArray.toString());
*/

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(RetroClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api api1 = retrofit1.create(Api.class);

        final Call<ResponseBody> callable = RetroClient
                .getInstance()
                .getApi()
                .submit(questionsList);

        callable.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //Toast.makeText(WebSevice.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("name",pref.getString("Name","sry"));
        intent.putExtra("name",pref.getString("Email","sry"));
        startActivity(intent);

    }
}
