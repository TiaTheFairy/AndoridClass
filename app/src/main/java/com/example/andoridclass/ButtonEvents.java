package com.example.andoridclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonEvents extends AppCompatActivity implements View.OnClickListener {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_events);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp = tv1.getText().toString();
                tv1.setText(tv2.getText().toString());
                tv2.setText(tmp);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((getResources().getString(R.string.button_tv_nottrigger)).equals(tv3.getText().toString())){
                    tv3.setText(getResources().getString(R.string.button_tv_trigger));
                }
                else{
                    tv3.setText(getResources().getString(R.string.button_tv_nottrigger));
                }
            }
        });

        bt3.setOnClickListener(new MyListener());
        bt4.setOnClickListener(this);
    }

    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            if((getResources().getString(R.string.button_tv_nottrigger)).equals(tv4.getText().toString())){
                tv4.setText(getResources().getString(R.string.button_tv_trigger));
            }
            else{
                tv4.setText(getResources().getString(R.string.button_tv_nottrigger));
            }
        }
    }

    @Override
    public void onClick(View v){
        if((getResources().getString(R.string.button_tv_nottrigger)).equals(tv5.getText().toString())){
            tv5.setText(getResources().getString(R.string.button_tv_trigger));
        }
        else{
            tv5.setText(getResources().getString(R.string.button_tv_nottrigger));
        }
    }

    public void click(View v){
        if((getResources().getString(R.string.button_tv_nottrigger)).equals(tv6.getText().toString())){
            tv6.setText(getResources().getString(R.string.button_tv_trigger));
        }
        else{
            tv6.setText(getResources().getString(R.string.button_tv_nottrigger));
        }
    }
};