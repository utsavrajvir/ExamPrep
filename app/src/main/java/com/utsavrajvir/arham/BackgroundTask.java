package com.utsavrajvir.arham;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class BackgroundTask extends AsyncTask<String,Void,String> {


    SharedPreferences pref;
    URL url = null;
    String ip = "http://192.168.43.54:80/arham/";
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

        if(str.equals("view1")){
//            String login_url = "http://192.168.43.93:8096/students/";

            String login_url = "http://192.168.43.93:8096/add/";
            String  inputLine = "Hello";

            String str1 = voids[1];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

Log.i("hii",str1);

                OutputStream out = new BufferedOutputStream(httpURLConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(str1);
                writer.close();
                out.close();

                /*BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                inputLine = in.readLine().toString();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                Log.i("response1",inputLine);
                */
                //return inputLine;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputLine;
        }


        if(str.equals("login"))
        {

            String email_id = voids[1];
            String password = voids[2];

            Log.i("utsav","if..login");
            try {

                //String login_url = "http://192.168.43.54:80/arham/login.php";
                String login_url = ip + str +".php";

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

                Log.i("response1",response.toString()+"utsav");

                pref.edit().putString("reply", response.toString()).apply();

                //Log.i("ans", String.valueOf(!(response.equals("try again"))));


                if (response.toString().equals("try again"))
                {

                    pref.edit().putString("result", "Invalid UserName or Password").apply();
                    pref.edit().apply();
                    pref.edit().commit();

                } else {

                    // session.createLoginSession(email_id);
                    pref.edit().putString("result", "Exist").apply();
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

                //String login_url = "http://192.168.43.54:80/arham/main_category.php";
                String login_url = ip + "main_category" +".php";

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


                in.close();
                return response.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(str.equals("test"))
        {

            String mid = voids[1];


            Log.i("utsav","if..test");
            try {

               // String login_url = "http://192.168.43.54:80/arham/test.php";
                String login_url = ip + str +".php";

                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();
                json_object.put("mid", mid);


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
        else  if(str.equals("category"))
        {

            String mid = voids[1];


            Log.i("utsav","if..Category");
            try {

               // String login_url = "http://192.168.43.54:80/arham/category.php";
                String login_url = ip + str +".php";

                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();
                json_object.put("mid", mid);


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
        else  if(str.equals("sub_category")) {

            String Sc_Id = voids[1];


            Log.i("utsav", "if..Sub_Category");
            try {

                //String login_url = "http://192.168.43.54:80/arham/sub_category.php";
                String login_url = ip + str +".php";

                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();
                json_object.put("Sc_Id", Sc_Id);


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

                Log.i("response", response.toString());

                /*pref.edit().putString("reply", response.toString()).apply();


                if (!response.equals("try again")) {
                    // session.createLoginSession(email_id);
                    pref.edit().putString("result", "Exist").apply();
                    pref.edit().apply();
                    pref.edit().commit();
                } else {
                    pref.edit().putString("result", "Invalid UserName or Password").apply();
                    pref.edit().apply();
                    pref.edit().commit();

                }*/

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
        else  if(str.equals("question2")) {

            String M_Cid = voids[1];
            String T_id = voids[2];

            Log.i("utsav", "if..Question");
            try {

                //String login_url = "http://192.168.43.54:80/arham/question2.php";
                String login_url = ip + str +".php";

                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();
                json_object.put("M_Cid", M_Cid);
                json_object.put("T_id", T_id);


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

                Log.i("response", response.toString());



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
        else  if(str.equals("question3")) {

            String M_Cid = voids[1];
            String T_id = voids[2];
            String C_id = voids[3];

            Log.i("utsav", "if..Question");
            try {

                //String login_url = "http://192.168.43.54:80/arham/question3.php";
                String login_url = ip + str +".php";

                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();
                json_object.put("M_Cid", M_Cid);
                json_object.put("T_id", T_id);
                json_object.put("C_id", C_id);

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

                Log.i("response", response.toString());



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
        else  if(str.equals("question4")) {

            String M_Cid = voids[1];
            String T_id = voids[2];
            String C_id = voids[3];
            String Sc_id = voids[4];

            Log.i("utsav", "if..Question4");
            try {

                //String login_url = "http://192.168.43.54:80/arham/question4.php";
                String login_url = ip + str +".php";

                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();
                json_object.put("M_Cid", M_Cid);
                json_object.put("T_id", T_id);
                json_object.put("C_id", C_id);
                json_object.put("Sc_id", Sc_id);

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

                Log.i("response", response.toString());



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
        else  if(str.equals("student_history")) {

            String json = voids[1];


            Log.i("utsav", "if..Question4");
            try {

                //String login_url = "http://192.168.43.54:80/arham/stud_history.php";
                String login_url = ip + "stud_history" +".php";

                URL url = new URL(login_url);


                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream out = new BufferedOutputStream(httpURLConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(json);
                writer.close();
                out.close();


                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                Log.i("response", response.toString());
  //              in.close();
                return response.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else  if(str.equals("verify")) {

            String id = voids[1];

            Log.i("utsav", "if..Verify");
            try {

                //String login_url = "http://192.168.43.54:80/arham/verify.php";
                String login_url = ip + "verify" +".php";

                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();
                json_object.put("St_Id", id);

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

                Log.i("response", response.toString());



                in.close();
                return response.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }else  if(str.equals("SignUp"))
        {
            String name1 = voids[1];
            String username1 = voids[2];

            String email_id = voids[3];
            String password = voids[4];

            String mobile = voids[5];


            Log.i("SignUp","if..SignUp");
            try {

                //String login_url = "http://192.168.43.54:80/arham/register.php";
                String login_url = ip + "register" +".php";

                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();

                json_object.put("name1", name1);
                json_object.put("username1", username1);
                json_object.put("email_id", email_id);
                json_object.put("password", password);
                json_object.put("mobile", mobile);


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

                Log.i("sresult",response.toString());


                in.close();
                return response.toString();



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();

            }

        }else  if(str.equals("check"))
        {


           /* String name1 = voids[1];
            String username1 = voids[2];

            String email_id = voids[3];
            String password = voids[4];

            String mobile = voids[5];
*/

            Log.i("SignUp","if..SignUp");
            try {

                //String login_url = "http://192.168.43.54:80/arham/check.php";
                String login_url = ip + "check" +".php";

                URL url = new URL(login_url);

               /* JSONObject json_object = new JSONObject();

                json_object.put("name1", name1);
                json_object.put("username1", username1);
                json_object.put("email_id", email_id);
                json_object.put("password", password);
                json_object.put("mobile", mobile);
*/

              //  String message = json_object.toString();

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

               /* OutputStream out = new BufferedOutputStream(httpURLConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(json_object.toString());
                writer.close();
                out.close();
*/

                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                Log.i("sresult",response.toString());

                if(!response.toString().trim().equals("try again"))
                {
                    pref.edit().putString("check", "Exist").apply();
                    pref.edit().apply();
                    pref.edit().commit();
                }else {
                    pref.edit().putString("check", "Sry").apply();
                    pref.edit().apply();
                    pref.edit().commit();
                }


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
