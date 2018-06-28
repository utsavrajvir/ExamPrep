package com.utsavrajvir.arham;

import android.arch.core.executor.TaskExecutor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {

    EditText number, verify;
    Button btn,btn1;
    private FirebaseAuth mAuth;
    private String verificationId;
    TextView resend;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        this.setTitle("Verification");

        pref = getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);
        editor  = pref.edit();


        mAuth = FirebaseAuth.getInstance();
        number = (EditText) findViewById(R.id.phone_number);
        verify = (EditText) findViewById(R.id.verification_text);
        resend = (TextView) findViewById(R.id.resend);


        number.setText(pref.getString("St_MobileNo","sry"));

        btn = (Button) findViewById(R.id.verification_button);
        btn1 = (Button) findViewById(R.id.verification_button1);



    }

    public void send(View view) {

        verify.setVisibility(View.VISIBLE);
        btn1.setVisibility(View.VISIBLE);
        btn.setVisibility(View.GONE);
        resend.setVisibility(View.VISIBLE);

        if (number.getText().toString().isEmpty() || number.getText().toString().length() < 10) {
            number.setError("Valid Number is Required");
            number.requestFocus();
            return;
        }


        sendVerificationCode(number.getText().toString());

    }


    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
                    backgroundTask.execute("verify",pref.getString("St_Id","sry"));

                    finish();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("name",pref.getString("Name","Sry"));
                    intent.putExtra("email",pref.getString("Email","Sry Again"));
                    startActivity(intent);

                } else {
                    Toast.makeText(Verification.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendVerificationCode(String Number) {

         PhoneAuthProvider.getInstance().verifyPhoneNumber(
                Number,        // Phone number to verify
                120,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationId = s;

            Toast.makeText(Verification.this, verificationId, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            verify.setText(code);
            Toast.makeText(Verification.this, code, Toast.LENGTH_SHORT).show();

            if (code != null) {

                verifyCode(code);
            }
        }


        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Verification.this, "Error in Sending Code", Toast.LENGTH_SHORT).show();
        }
    };


    public void Submit(View view) {

            String code1 = verify.getText().toString().trim();

        if (code1.isEmpty() || code1.length() < 6) {
            verify.setError("Enter Code..");
            verify.requestFocus();
            return;
        }
        verifyCode(code1);
    }
}
