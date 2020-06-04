package com.example.laundry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class home extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.home, container, false);
       // ImageButton imgButton = v.findViewById(R.id.imageButton) ;
     /* imgButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getActivity().getApplication(),Main3Activity.class));
          }
      });*/
        final GridView grid;
        int[] imageId = {
                R.drawable.wm,R.drawable.ironfn,R.drawable.ironwashf,R.drawable.dryc,R.drawable.dye};
        final String[] ditems = {"Wash","Ironing","Wash+Iron","Dry-wash","Dyeing"};
        custgridad adapter = new custgridad(getActivity(), ditems, imageId);
        grid=(GridView)v.findViewById(R.id.lv);


        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(getActivity(),selection.class);
                i.putExtra("dum",ditems[+ position].toString());
                startActivity(i);

            }
        });
       /* GridView l = v.findViewById(R.id.lv);
        ArrayAdapter<String> lvadap = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,ditems);
        l.setAdapter(lvadap);*/
        return v;
    }
}
