package com.example.steva.pin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText pwd = findViewById(R.id.pwd);

        Button enter = findViewById(R.id.enter);

        enter.setOnClickListener((View view) -> {
            if(pwd.getText().toString().equals("0000")){
                makeText(this, "pin corretto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Activity2.class);
                startActivity(intent);
            }else{
                makeText(this, "pin errato", Toast.LENGTH_SHORT).show();
            }
            pwd.setText("");
        });
    }
}
