package com.example.laundry;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class Main3Activity extends AppCompatActivity {
 TextView sh,shq,shp,ts,tsq,tsp,j,jq,jp,s,sq,sp,tr,trq,trp,toq,tot,d,t,ser;
String sert;
int shorq;
    int shirq;
    int tshq;
    int jeq;
    int traq;
    int topr=0;
        int shirt=0,tshirt=0,jeans=0,track=0,shorts=0;
        int spr=0,tspr=0,jpr=0,tpr=0,sopr=0;
int q=0;
FirebaseAuth mauth;
TableRow shirtr,tshirtr,jeanr,shortr,trackr;




    //our database reference object
    DatabaseReference databaseArtists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mauth=FirebaseAuth.getInstance();

       ser = findViewById(R.id.textView11);
        sh = (TextView) findViewById(R.id.shorts);
        shq=findViewById(R.id.shortq);
        shp=findViewById(R.id.shorpr);
        ts=findViewById(R.id.tshirt);
        tsq=findViewById(R.id.tshirq);
        tsp=findViewById(R.id.tshirpr);
        j=findViewById(R.id.jean);
        jp=findViewById(R.id.jeanpr);
        jq=findViewById(R.id.jeanq);
        s=findViewById(R.id.shirt);
        sq=findViewById(R.id.shirq);
        sp= findViewById(R.id.shirpr);
        tr= findViewById(R.id.track);
        trq= findViewById(R.id.tracq);
        trp= findViewById(R.id.tracpr);

        toq=findViewById(R.id.toq);
        tot=findViewById(R.id.totpr);
        shirtr = findViewById(R.id.shirtrow);
        tshirtr=findViewById(R.id.tshirtrow);
        jeanr=findViewById(R.id.jeanrow);
        shortr=findViewById(R.id.shortrow);
        trackr=findViewById(R.id.trackrow);
Button o = findViewById(R.id.Order);

d=findViewById(R.id.date);
t=findViewById(R.id.time);

        Intent intent = getIntent();
       shorq= intent.getIntExtra("shorts",0);
        shq.setText(Integer.toString(shorq));
          sert =intent.getStringExtra("service");
        ser.setText("Service chosen : "+sert);
          tshq=intent.getIntExtra("tshirt",0);
        tsq.setText(Integer.toString(tshq));
          shirq=intent.getIntExtra("shirt",0);
        sq.setText(Integer.toString(shirq));
          jeq=intent.getIntExtra("jean",0);
       jq.setText(Integer.toString(jeq));
          traq=intent.getIntExtra("track",0);
        trq.setText(Integer.toString(traq));
//String a = sert.trim().replaceAll("\\s+", " ");
        if(shorq == 0) {
            shortr.setVisibility(View.GONE);
        }
        if(tshq == 0){
            tshirtr.setVisibility(View.GONE);
        }
        if(shirq == 0){
            shirtr.setVisibility(View.GONE);
        }
        if(jeq == 0){
            jeanr.setVisibility(View.GONE);
        }
        if(traq == 0){
            trackr.setVisibility(View.GONE);
        }
         q=shorq+tshq+shirq+jeq+traq;
        toq.setText(Integer.toString(q,0));

        if(sert.length() == 4){
            shirt=5;
            tshirt=6;
            jeans=12;
            track=7;
            shorts=5;
        }
        if(sert.length() == 7){
            shirt=5;
            tshirt=5;
            jeans=8;
            track=6;
            shorts=5;
        }
         if(sert.length() == 9){
            shirt=10;
            tshirt=10;
            jeans=15;
            track=12;
            shorts=8;
        }
         if(sert.length() == 8 || sert.length() == 6){
            shirt=20;
            tshirt=15;
            jeans=25;
            track=15;
            shorts=15;
        }
        spr=shirq*shirt;
        sp.setText("Rs."+spr);
        tspr=tshq*tshirt;
        tsp.setText("Rs."+tspr);
        jpr=jeq*jeans;
        jp.setText("Rs."+jpr);
        tpr=traq*track;
        trp.setText("Rs."+tpr);
        sopr=shorq*shorts;
        shp.setText("Rs."+sopr);


        topr=spr+tspr+jpr+tpr+sopr;
tot.setText("Rs."+topr);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        String[] arr=strDate.split(" ");
        d.setText(arr[0]);
        t.setText(arr[1]);

       String userid = Objects.requireNonNull(mauth.getCurrentUser()).getUid();
        //getting the reference of artists node
        //Toast.makeText(this, userid, Toast.LENGTH_SHORT).show();
        databaseArtists = FirebaseDatabase.getInstance().getReference().child("Orders").child(userid);

        //getting views

        //listViewArtists = (ListView) findViewById(R.id.listViewArtists);


        //list to store artists
        //





o.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),pay.class);
        i.putExtra("amt",topr);
        startActivity(i);
        addtoDB();
    }
});
    }
    private void addtoDB() {
        //getting the values to save
        String totalq = toq.getText().toString();
        String total = tot.getText().toString();
        String date = d.getText().toString();
        String time = t.getText().toString();
        String service = ser.getText().toString();

        //checking if the value is provided


            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseArtists.push().getKey();

            //creating an Artist Object
            dade artist = new dade(id, total, totalq,date,time,service);

            //Saving the Artist
            databaseArtists.child(id).setValue(artist);

            //setting edittext to blank again

            //displaying a success toast
           Toast.makeText(this, "Order added to DB", Toast.LENGTH_SHORT).show();
        }
    }
