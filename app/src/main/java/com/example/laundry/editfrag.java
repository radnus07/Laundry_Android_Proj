package com.example.laundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class editfrag extends AppCompatActivity {
TextInputLayout e;
Button s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editfrag);
        setTitle("Update Profile");
        e=findViewById(R.id.textInputLayout2);
        s=findViewById(R.id.button2);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                        .setDisplayName(e.getEditText().getText().toString())
                        .build();
                firebaseUser.updateProfile(request).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(editfrag.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(editfrag.this, "failed to update", Toast.LENGTH_SHORT).show();
                            }
                        });
                finish();
                startActivity(new Intent(editfrag.this,MainActivity.class));


            }
        });

    }
    public void onBackPressed(){
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
