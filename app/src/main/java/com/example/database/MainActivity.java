package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsernameEt =(EditText)findViewById(R.id.etEmail);
        UsernameEt.requestFocus();

        PasswordEt =(EditText)findViewById(R.id.etPassword);
    }
    public void onLogin (View view){

        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this,username);
        backgroundWorker.execute(type,username,password);

    }

    public void OpenReg (View view){
        startActivity(new Intent(this, Register.class) );
    }
}