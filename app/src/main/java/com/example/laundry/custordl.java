package com.example.laundry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class custordl extends ArrayAdapter<dade> {

private Activity context;
private List<dade> ordist;

    public custordl( Activity context, List<dade> ordist) {
        super(context,R.layout.activity_custordl, ordist);
        this.context=context;
        this.ordist=ordist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();

        View v = layoutInflater.inflate(R.layout.activity_custordl,null,true);

        TextView d = (TextView) v.findViewById(R.id.date);
        TextView t =(TextView)  v.findViewById(R.id.time);
        TextView tq = (TextView) v.findViewById(R.id.qua);
        TextView ta =(TextView)  v.findViewById(R.id.price);
        TextView s = (TextView) v.findViewById(R.id.ser);

        dade ordl = ordist.get(position);

        d.setText(ordl.getDate());
        t.setText(ordl.getTime());
        tq.setText("Item Quantity: "+ordl.getTotalquantity());
        ta.setText("Order Amount: "+ ordl.getTotalprice());
        s.setText(ordl.getService());


        return v;
    }


}
