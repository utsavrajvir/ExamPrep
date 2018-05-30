package com.utsavrajvir.arham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class ForgetPassword extends AppCompatActivity {

    EditText email;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        email = (EditText)findViewById(R.id.forget_email);
        btn = (Button)findViewById(R.id.btnforget);

        email.setVisibility(View.VISIBLE);
        btn.setText("Recovery password");

    }

    public void recovery(View view) {

        String email1 = email.getText().toString().trim();

        if(TextUtils.isEmpty(email1))
        {
            email.setError("Required");
            email.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches())
        {
            email.setError("Please Enter Valid Email Address");
            email.requestFocus();
            return;
        }
        else
        {
            email.setVisibility(View.INVISIBLE);

            final TextView txt = new TextView(this);

            txt.setText("We have sent your password to your email.Please check your inbox.");

            txt.setEllipsize(email.getEllipsize());

            btn.setText("Proceed to Login");

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }
            });

            // Send password
        }

    }



}
