package com.bignerdranch.android.shipit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    ListView lv;
    private Button button_radius;
    ArrayAdapter adapter;

    String[] mobileArray = {"24 E 41st St, New York, NY 10017","342 Madison Ave, New York, NY 10017",
            "4 W 43rd St, New York, NY 10036",
            "16 E 46th St, New York, NY 10017","575 5th Ave, New York, NY 10017","1231 6th Ave, New York, NY 10020","142 W 49th St, New York, NY 10019",
    "1601 Broadway, New York, NY 10019",
    "250 W 49th St, New York, NY 10019",
    "309 W 49th St, New York, NY 10019",
    "428 W 49th St, New York, NY 10019",
    "722 10th Ave, New York, NY 10019",
    "W 50th St, New York, NY 10019"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        button_radius = (Button) findViewById(R.id.button_radius);
        button_radius.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Intent intent =  new Intent (ListActivity.this, MapsActivity.class);
                    startActivity(intent);

                }



        });
      //  lv = (ListView) findViewbyId(R.id.listView);
        lv= (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mobileArray);

        lv.setAdapter(adapter);
    }
}
