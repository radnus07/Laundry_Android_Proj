package com.example.laundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class sel extends AppCompatActivity {
String dum;
Button o;
String j,tr,ts;
int si,so;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel);

       TextView s=findViewById(R.id.toptext);
       String txt = "Order Your Laundry.";
       SpannableString ss = new SpannableString(txt);
       ForegroundColorSpan clr = new ForegroundColorSpan(Color.GRAY);
       ss.setSpan(clr,0,10, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
       s.setText(ss);

        Intent i = getIntent();
        dum =i.getStringExtra("dum");

        o = findViewById(R.id.order);
        TextView t = findViewById(R.id.textView15);
        t.setText("Service Chosen: "+dum);

        final ElegantNumberButton shorts = findViewById(R.id.shortco);
        final ElegantNumberButton shirt = findViewById(R.id.shirtco);
        final ElegantNumberButton tshirt = findViewById(R.id.tshirco);
        final ElegantNumberButton track = findViewById(R.id.traco);
        final ElegantNumberButton jean = findViewById(R.id.jeanco);

        shorts.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
            so = newValue;
            }
        });
        shirt.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                si = Integer.parseInt(shirt.getNumber());
            }
        });
        tshirt.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts = tshirt.getNumber();
            }
        });
        track.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                tr=track.getNumber();
            }
        });
        jean.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                j=jean.getNumber();
            }
        });


       o.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(so != 0 || si!= 0 || ts!=null || tr!=null || j!=null){
                   final Intent intent = new Intent(sel.this, Main3Activity.class);
                   intent.putExtra("shorts", so);
                   intent.putExtra("shirt", si);
                   intent.putExtra("tshirt", ts);
                   intent.putExtra("track", tr);
                   intent.putExtra("jean", j);
                   intent.putExtra("service", dum);
                   startActivity(intent);
               }
               else{
                   Toast.makeText(sel.this, "No Items Selected. \nSelect atleast one to continue", Toast.LENGTH_SHORT).show();
               }

           }
       });

    }
}
