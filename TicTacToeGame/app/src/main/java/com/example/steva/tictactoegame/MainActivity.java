package com.example.steva.tictactoegame;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton singleplayer = findViewById(R.id.singleplayer);
        singleplayer.setOnClickListener((View view) -> {
            Intent i = new Intent(this, SinglePlayer.class);
            startActivity(i);
        });

        ImageButton multyplayer = findViewById(R.id.multiplayer);
        multyplayer.setOnClickListener((View view) -> {
            Intent i = new Intent(this, MultiPlayer.class);
            startActivity(i);
        });

        ImageButton online = findViewById(R.id.online);
        online.setOnClickListener((View view) -> {
            Intent i = new Intent(this, Online.class);
            startActivity(i);
        });

        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener((View view) -> {
            AlertDialog.Builder altdial = new AlertDialog.Builder(MainActivity.this);
            altdial.setMessage("do you want to quit the game?").setCancelable(false);
            altdial.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            altdial.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alert = altdial.create();
            alert.setTitle("ARE YOU SURE?");
            alert.show();
        });
    }
}
