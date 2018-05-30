package com.utsavrajvir.arham;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

public class BackgroundTask extends AsyncTask<String,Void,String> {


    SharedPreferences pref;
    URL url = null;

    Context ctx;

    //Session session;

    public BackgroundTask(Context ctx) {

        pref = ctx.getSharedPreferences(LoginActivity.MYPREF, MODE_PRIVATE);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        //super.onPostExecute(result);

        //Toast.makeText(ctx, result.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... voids) {

        StringBuffer response = null;

        String str = voids[0];



        if(str.equals("login"))
        {

            String email_id = voids[1];
            String password = voids[2];

            Log.i("utsav","if..login");
            try {

                String login_url = "http://192.168.43.54:80/arham/login.php";


                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();
                json_object.put("email_id", email_id);
                json_object.put("password", password);

                String message = json_object.toString();

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream out = new BufferedOutputStream(httpURLConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(json_object.toString());
                writer.close();
                out.close();


                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                Log.i("response",response.toString());

                pref.edit().putString("reply", response.toString()).apply();



                if (!response.equals("try again")) {
                   // session.createLoginSession(email_id);
                    pref.edit().putString("result", "Exist").apply();
                    pref.edit().apply();
                    pref.edit().commit();
                } else {
                    pref.edit().putString("result", "Invalid UserName or Password").apply();
                    pref.edit().apply();
                    pref.edit().commit();

                }

                in.close();
                return response.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();

            }

        }
        else if(str.equals("main"))
        {
            Log.i("utsav","if..main");
            try {

                String login_url = "http://192.168.43.54:80/arham/main_category.php";


                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();

                String message = json_object.toString();

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream out = new BufferedOutputStream(httpURLConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(json_object.toString());
                writer.close();
                out.close();


                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                Log.i("response",response.toString());



                /*pref.edit().putString("reply", response.toString()).apply();

                if (pref.getString("reply", "sry").equals("UserExists")) {
                    // session.createLoginSession(email_id);
                    pref.edit().putString("result", "Exist").apply();
                    pref.edit().apply();
                    pref.edit().commit();
                } else {
                    pref.edit().putString("result", "Invalid UserName or Password").apply();
                    pref.edit().apply();
                    pref.edit().commit();

                }
*/
                in.close();
                return response.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



        return null;
    }
}
