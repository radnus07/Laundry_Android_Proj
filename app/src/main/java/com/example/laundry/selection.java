package com.example.laundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class selection extends AppCompatActivity {
int sic=0,tsc=0,jc=0,soc=0,tc=0;
TextView tsco,jco,soco,tco,sico;
Button sip,sim,tsp,tsm,jp,jm,sop,som,tp,tm,o;
String dum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        sip=findViewById(R.id.sip);
        sim=findViewById(R.id.sim);
        tsp=findViewById(R.id.tsp);
        tsm=findViewById(R.id.tsm);
        jp=findViewById(R.id.jp);
        jm=findViewById(R.id.jm);
        sop=findViewById(R.id.sop);
        som=findViewById(R.id.som);
        tp=findViewById(R.id.tp);
        tm=findViewById(R.id.tm);
       sico=findViewById(R.id.sic);
        tsco=findViewById(R.id.tsc);
        jco=findViewById(R.id.jc);
        soco=findViewById(R.id.soc);
        tco=findViewById(R.id.tc);
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
        sip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sic=increase(sic);
                sico.setText(Integer.toString(sic));
            }
        });
        sim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sic!=0) {
                    sic = decrease(sic);
                    sico.setText(Integer.toString(sic));
                }
                else {
                    Toast.makeText(selection.this, "Can't go below 0", Toast.LENGTH_SHORT).show();
                     }
            }
        });

        tsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tsc=increase(tsc);
                tsco.setText(Integer.toString(tsc));
            }
        });

        tsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tsc!=0) {
                    tsc = decrease(tsc);
                    tsco.setText(Integer.toString(tsc));
                }
                else {
                    Toast.makeText(selection.this, "Can't go below 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        jp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jc=increase(jc);
                jco.setText(Integer.toString(jc));

            }
        });

        jm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jc!=0) {
                    jc = decrease(jc);
                    jco.setText(Integer.toString(jc));

                }
                else {
                    Toast.makeText(selection.this, "Can't go below 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soc=increase(soc);
                soco.setText(Integer.toString(soc));
            }
        });

        som.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soc!=0) {
                    soc = decrease(soc);
                    soco.setText(Integer.toString(soc));
                }
                else {
                    Toast.makeText(selection.this, "Can't go below 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tc=increase(tc);
                tco.setText(Integer.toString(tc));
            }
        });

        tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tc!=0) {
                    tc = decrease(tc);
                    tco.setText(Integer.toString(tc));
                }
                else {
                    Toast.makeText(selection.this, "Can't go below 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

o.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(soc != 0 || sic!= 0 || tsc!=0 || tc!=0 || jc!=0){
            final Intent intent = new Intent(selection.this, Main3Activity.class);
            intent.putExtra("shorts", soc);
            intent.putExtra("shirt", sic);
            intent.putExtra("tshirt", tsc);
            intent.putExtra("track", tc);
            intent.putExtra("jean", jc);
            intent.putExtra("service", dum);
            startActivity(intent);
        }
        else{
            Toast.makeText(selection.this, "No Items Selected. \nSelect atleast one to continue", Toast.LENGTH_SHORT).show();
        }
    }
});



    }
    public int increase(int a){
        a++;
        return a;
    }
    public int decrease(int b){
        b--;
        return b;
    }
}
