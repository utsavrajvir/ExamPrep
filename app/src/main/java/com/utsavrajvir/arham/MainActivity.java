package com.utsavrajvir.arham;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    static String json_string1 = null;
    ProgressBar progressBar1;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String name,email;

    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar1 = (ProgressBar)findViewById(R.id.main_progress);

        pref = getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);
        editor  = pref.edit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        name = getIntent().getExtras().getString("name");
        email = getIntent().getExtras().getString("email");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        TextView name1 = (TextView)header.findViewById(R.id.drawer_username);
        TextView email1 = (TextView)header.findViewById(R.id.drawer_email);
        name1.setText(name);
        email1.setText(email);

        //json_string1 = getIntent().getExtras().getString("json_data");


        if(pref.getString("MobileVerification","sry").equals("0"))
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

            // Setting Dialog Title
            alertDialog.setTitle("Mobile Verification...");

            // Setting Dialog Message
            alertDialog.setMessage("We Will Verfing Phone Number:\n"+ pref.getString("St_MobileNo","sry") +"\nIs this OK, or would you like to edit the number ?");

            // Setting Icon to Dialog
            // alertDialog.setIcon(R.drawable.delete);

            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("Edit/Verify", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {


                    startActivity(new Intent(getApplicationContext(),Verification.class));
                    // Write your code here to invoke YES event
                    Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to invoke NO event
                    Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();

                    dialog.cancel();
                }
            });

            // Showing Alert Message
            alertDialog.show();

        }



        BackgroundTask backgroundTask = new BackgroundTask(this);

        progressBar1.setVisibility(View.VISIBLE);

        String s = null;
        try {
            s = backgroundTask.execute("main").get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        progressBar1.setVisibility(View.GONE);

        json_string1 = s.toString();


        ViewPager vp_pages= (ViewPager) findViewById(R.id.vp_pages);
        FragmentAdapter pagerAdapter=new FragmentAdapter(getSupportFragmentManager(),json_string1);
        vp_pages.setAdapter(pagerAdapter);

        TabLayout tbl_pages= (TabLayout) findViewById(R.id.tbl_pages);
        tbl_pages.setupWithViewPager(vp_pages);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.invite_frnd) {
            String smsEditText = "I am preparing for Competative 2018  Exam Prep with Arham App. Are you preparing for any competative exam? With Prep App,yes you can. Download today Link...";


            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            //smsIntent.putExtra("address", "12125551212");
            smsIntent.putExtra("sms_body",smsEditText);
            startActivity(smsIntent);
/*
            // Find the sms_message view.
            String smsEditText = "I am preparing for Competative 2018  Exam Prep with Arham App. Are you preparing for any competative exam? With Prep App,yes you can. Download today Link...";
            String smsNumber = "9924848640";
            // Get the text of the sms message.
            String sms = smsEditText;
            // Create the intent.
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
            // Set the data for the intent as the phone number.
            smsIntent.setData(Uri.parse(smsNumber));
            // Add the message (sms) with the key ("sms_body").
            smsIntent.putExtra("sms_body", sms);
            // If package resolves (target app installed), send intent.
            if (smsIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(smsIntent);
            } else {
                Log.i("sms", "Can't resolve app for ACTION_SENDTO Intent");
            }
*/

            /*Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("9924848640"));
            sendIntent.putExtra("sms_body", smsEditText);
            startActivity(sendIntent);
*/
        } else if (id == R.id.logout) {
            finish();
            session = new Session(this);
            session.logoutUser();

        } else if (id == R.id.verify) {
                startActivity(new Intent(this,Verification.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
