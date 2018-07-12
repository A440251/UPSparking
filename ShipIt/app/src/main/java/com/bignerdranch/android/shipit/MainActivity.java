package com.bignerdranch.android.shipit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button_enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button_enter = (Button) findViewById(R.id.button_enter);
        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent =  new Intent (MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });
    }


}
