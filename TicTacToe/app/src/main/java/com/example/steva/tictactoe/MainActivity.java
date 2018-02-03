package com.example.steva.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button multi = findViewById(R.id.multi);
        Button single = findViewById(R.id.single);

        multi.setOnClickListener((view)-> {

            Intent i = new Intent(this, ActivityMultiplayer.class);
            startActivity(i);
        });

        single.setOnClickListener((view)-> {

            Intent i = new Intent(this, ActivitySinglePlayer.class);
            startActivity(i);
        });
    }
}