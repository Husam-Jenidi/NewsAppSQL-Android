package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText name, email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.rgName);
        email = findViewById(R.id.rgEmail);
        password = findViewById(R.id.rgPassword);


    }
    public void back_to_login (View view){
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void onRegister(View view){
        String str_name = name.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this,str_name);
        backgroundWorker.execute(type,str_name,str_email,str_password);

    }
}