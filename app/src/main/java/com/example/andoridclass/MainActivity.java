package com.example.andoridclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button main_bt_buttons;
    private Button main_bt_layout;
    private Button main_bt_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_bt_buttons = findViewById(R.id.main_bt_buttons);
        main_bt_layout = findViewById(R.id.main_bt_layout);
        main_bt_recycler = findViewById(R.id.main_bt_recycler);

        main_bt_buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ButtonEvents.class);
                startActivity(intent);
            }
        });

        main_bt_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Layout.class);
                startActivity(intent);
            }
        });

        main_bt_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookListMainActivity.class);
                startActivity(intent);
            }
        });
    }
}