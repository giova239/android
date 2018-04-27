package com.example.steva.zombielabyrinth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class start_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        NumberPicker rowPicker = findViewById(R.id.row);
        NumberPicker columnPicker = findViewById(R.id.column);

        rowPicker.setMinValue(3);
        rowPicker.setMaxValue(100);
        rowPicker.setWrapSelectorWheel(false);
        rowPicker.setValue(3);

        columnPicker.setMinValue(3);
        columnPicker.setMaxValue(100);
        columnPicker.setWrapSelectorWheel(false);
        columnPicker.setValue(3);

        Button btn = findViewById(R.id.play);
        btn.setOnClickListener((View view) -> {

            Intent i = new Intent(this, gameActivity.class);
            i.putExtra("row", rowPicker.getValue());
            i.putExtra("column", columnPicker.getValue());

            Log.d("debug", "row: " + Integer.toString(i.getIntExtra("row", 0)));
            Log.d("debug", "column: " + Integer.toString(i.getIntExtra("column", 0)));

            startActivity(i);

        });
    }
}
