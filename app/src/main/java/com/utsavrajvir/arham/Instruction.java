package com.utsavrajvir.arham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class Instruction extends AppCompatActivity {

    TextView instruction;
    String M_Cid,T_Id,C_Id,cat,M_Time,Sc_Id;
     String C_Time,Sc_Time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);


        cat = getIntent().getExtras().getString("cat");
        M_Cid = getIntent().getExtras().getString("M_id");
        T_Id = getIntent().getExtras().getString("T_id");



        instruction = (TextView)findViewById(R.id.instruction);


    }

    public void test_start(View view) {



        if(cat.equals("category"))
        {
            M_Time = getIntent().getExtras().getString("M_Time");
            Intent intent = new Intent(this,Display_Questions.class);
            intent.putExtra("M_id",M_Cid);
            intent.putExtra("T_id",T_Id);
            intent.putExtra("cat",cat);
            intent.putExtra("M_Time",M_Time);
            startActivity(intent);
        }
        else if(cat.equals("sub_category"))
        {
            C_Id = getIntent().getExtras().getString("C_id");
            C_Time = getIntent().getExtras().getString("C_Time");
            Intent intent = new Intent(this,Display_Questions.class);
            intent.putExtra("M_id",M_Cid);
            intent.putExtra("T_id",T_Id);
            intent.putExtra("C_id",C_Id);
            intent.putExtra("C_Time",C_Time);
            intent.putExtra("cat",cat);
            startActivity(intent);
        }
        else if(cat.equals("sub_category1"))
        {
            C_Id = getIntent().getExtras().getString("C_id");
            Sc_Time = getIntent().getExtras().getString("Sc_Time");
            Sc_Id = getIntent().getExtras().getString("Sc_Id");

            Intent intent = new Intent(this,Display_Questions.class);
            intent.putExtra("M_id",M_Cid);
            intent.putExtra("T_id",T_Id);
            intent.putExtra("C_id",C_Id);
            intent.putExtra("Sc_Time",Sc_Time);
            intent.putExtra("cat",cat);
            intent.putExtra("Sc_id",Sc_Id);
            startActivity(intent);
        }



    }
}
