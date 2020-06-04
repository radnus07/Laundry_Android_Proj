package com.example.laundry;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends Fragment {
    private FirebaseAuth mAuth;
    TextView n,e;
    Button s;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.profile, container, false);
        Button l = v.findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        n=v.findViewById(R.id.name);
        e=v.findViewById(R.id.email);
        s=v.findViewById(R.id.edit);
        String u = user.getEmail();
        e.setText("Email Addr: "+u);
        n.setText("Username: "+user.getDisplayName());
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(),Login.class));
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                startActivity(new Intent(getContext(),editfrag.class));
            }
        });
        return v;
    }
}
