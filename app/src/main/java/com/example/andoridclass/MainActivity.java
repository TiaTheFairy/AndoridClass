package com.example.andoridclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttons(View v) {
        Intent intent = new Intent(MainActivity.this, ButtonEvents.class);
        startActivity(intent);
    }

    public void layout(View v) {
        Intent intent = new Intent(MainActivity.this, Layout.class);
        startActivity(intent);
    }

    public void view(View v) {
        Intent intent = new Intent(MainActivity.this, BookListMainActivity.class);
        startActivity(intent);
    }
}

