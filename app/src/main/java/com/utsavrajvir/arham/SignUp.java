package com.utsavrajvir.arham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    EditText name,username,email,password,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText)findViewById(R.id.name);
        username = (EditText)findViewById(R.id.username);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        mobile = (EditText)findViewById(R.id.mobile);

    }

    public void register(View view) {


        String name1 = name.getText().toString().trim();
        String username1 = username.getText().toString().trim();

        String email1 = email.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        String mobile1 = mobile.getText().toString().trim();


        if(TextUtils.isEmpty(name1)){
            name.setError("Required");
            name.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(username1)){
            username.setError("Required");
            username.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(email1)){
            email.setError("Required");
            email.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(password1)){
            password.setError("Required");
            password.requestFocus();;
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("Please Enter Valid Email Address");
            email.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(mobile1)){
            mobile.setError("Required");
            mobile.requestFocus();
            return;
        }

        if(password1.length() < 6)
        {
            password.setError("Length should be equal or grater than 6");
            password.requestFocus();
            return;
        }
        else{
            // Record entry Call
        }

    }
}
