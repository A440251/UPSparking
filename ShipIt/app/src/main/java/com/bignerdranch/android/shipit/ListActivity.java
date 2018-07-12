package com.bignerdranch.android.shipit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListActivity extends AppCompatActivity {


    private Button button_radius;
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
    }
}
