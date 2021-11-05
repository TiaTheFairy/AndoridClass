package com.example.andoridclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Layout extends AppCompatActivity{

    private TextView layout_tv_hello;
    private TextView layout_tv_world;
    private Button layout_bt_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        layout_tv_hello = findViewById(R.id.layout_tv_hello);
        layout_tv_world = findViewById(R.id.layout_tv_world);
        layout_bt_change = findViewById(R.id.layout_bt_change);

        layout_bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp = layout_tv_hello.getText().toString();
                layout_tv_hello.setText(layout_tv_world.getText().toString());
                layout_tv_world.setText(tmp);
            }
        });
    }
}