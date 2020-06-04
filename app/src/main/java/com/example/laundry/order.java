package com.example.laundry;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class order extends Fragment {
    private ListView a;
    TextView e;
    private DatabaseReference databaseArtists;
private FirebaseAuth mauth= FirebaseAuth.getInstance();
    //a list to store all the artist from firebase database
    private List<dade> ordist;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order, container, false);

        a =(ListView) v.findViewById(R.id.ord);


        ordist = new ArrayList<>();
        String userid = Objects.requireNonNull(mauth.getCurrentUser()).getUid();
        //getting the reference of artists node
        //Toast.makeText(this, userid, Toast.LENGTH_SHORT).show();
        databaseArtists = FirebaseDatabase.getInstance().getReference().child("Orders").child(userid);

        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ordist.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    dade artist = postSnapshot.getValue(dade.class);
                    //adding artist to the list
                    ordist.add(artist);
                }
                custordl artistAdapter = new custordl(Objects.requireNonNull(getActivity()),ordist);
                a.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }
}