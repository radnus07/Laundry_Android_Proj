package com.example.laundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
TextView t;
    TextInputLayout e,p;
    Button b;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t = findViewById(R.id.signup);
        b = findViewById(R.id.login);
        e = (TextInputLayout) findViewById(R.id.email);
        p = (TextInputLayout) findViewById(R.id.pass);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,signup.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = e.getEditText().getText().toString();
                String password = p.getEditText().getText().toString();
                if(TextUtils.isEmpty(email)&&TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please enter credentials",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                login();


            }
        });

    }
    private void login(){
        String email = e.getEditText().getText().toString();
        String password = p.getEditText().getText().toString();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();

                   startActivity(new Intent(Login.this,MainActivity.class));

                }
                else{
                    Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
