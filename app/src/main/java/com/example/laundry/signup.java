package com.example.laundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputLayout e,p,u;
    Button s;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
         e = (TextInputLayout) findViewById(R.id.email);
        p =(TextInputLayout) findViewById(R.id.pass);
        u =(TextInputLayout) findViewById(R.id.username);
        s=findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /* if(TextUtils.isEmpty(email)){
                         Toast.makeText(getApplicationContext(),"Please enter email-address",Toast.LENGTH_SHORT).show();
                         return;
                     }
                     if(TextUtils.isEmpty(password)){
                         Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
                         return;
                     }*/
                progressDialog.setMessage("Registering please wait...");
                progressDialog.show();
                register();

            }
        });
    }
    private void register(){
        String email = e.getEditText().getText().toString();
        String password = p.getEditText().getText().toString();
        final String username = u.getEditText().getText().toString();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(username);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                    UserProfileChangeRequest request= new UserProfileChangeRequest.Builder()
                            .setDisplayName(u.getEditText().getText().toString())
                            .build();
                    firebaseUser.updateProfile(request).
                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "failed to update", Toast.LENGTH_SHORT).show();
                                }
                            });
                    Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                    startActivity(new Intent(signup.this,MainActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"unsuccessful",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }
        });
    }

}


