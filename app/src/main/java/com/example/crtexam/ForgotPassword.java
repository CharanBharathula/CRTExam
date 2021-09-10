package com.example.crtexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {

    EditText email,otp,password,confirm_pwd;
    Button submit,resend_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        email=findViewById(R.id.email_for_forgotpw);
        otp=findViewById(R.id.otp);
        password=findViewById(R.id.password_for_forgotpw);
        confirm_pwd=findViewById(R.id.confirm_pwd);
        submit=findViewById(R.id.submit_forgotpwd);
        resend_otp=findViewById(R.id.resend_otp);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
