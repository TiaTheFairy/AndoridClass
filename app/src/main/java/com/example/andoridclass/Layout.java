package com.example.andoridclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Layout extends AppCompatActivity{

    private TextView tv1;
    private TextView tv2;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        bt = findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp = tv1.getText().toString();
                tv1.setText(tv2.getText().toString());
                tv2.setText(tmp);
            }
        });
    }
}