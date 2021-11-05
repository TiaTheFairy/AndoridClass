package com.example.andoridclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonEvents extends AppCompatActivity implements View.OnClickListener {
    private TextView buttons_tv_hello;
    private TextView buttons_tv_world;
    private TextView buttons_tv_anoy;
    private TextView buttons_tv_cust;
    private TextView buttons_tv_exte;
    private TextView buttons_tv_xml;
    private Button buttons_bt_switch;
    private Button buttons_bt_anoy;
    private Button buttons_bt_cust;
    private Button buttons_bt_exte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_events);

        buttons_tv_hello = findViewById(R.id.buttons_tv_hello);
        buttons_tv_world = findViewById(R.id.buttons_tv_world);
        buttons_tv_anoy = findViewById(R.id.buttons_tv_anoy);
        buttons_tv_cust = findViewById(R.id.buttons_tv_cust);
        buttons_tv_exte = findViewById(R.id.buttons_tv_exte);
        buttons_tv_xml = findViewById(R.id.buttons_tv_xml);
        buttons_bt_switch = findViewById(R.id.buttons_bt_switch);
        buttons_bt_anoy = findViewById(R.id.buttons_bt_anoy);
        buttons_bt_cust = findViewById(R.id.buttons_bt_cust);
        buttons_bt_exte = findViewById(R.id.buttons_bt_exte);

        buttons_bt_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp = buttons_tv_hello.getText().toString();
                buttons_tv_hello.setText(buttons_tv_world.getText().toString());
                buttons_tv_world.setText(tmp);
            }
        });

        buttons_bt_anoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((getResources().getString(R.string.button_tv_nottrigger)).equals(buttons_tv_anoy.getText().toString())){
                    buttons_tv_anoy.setText(getResources().getString(R.string.button_tv_trigger));
                }
                else{
                    buttons_tv_anoy.setText(getResources().getString(R.string.button_tv_nottrigger));
                }
            }
        });

        buttons_bt_cust.setOnClickListener(new MyListener());
        buttons_bt_exte.setOnClickListener(this);
    }

    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            if((getResources().getString(R.string.button_tv_nottrigger)).equals(buttons_tv_cust.getText().toString())){
                buttons_tv_cust.setText(getResources().getString(R.string.button_tv_trigger));
            }
            else{
                buttons_tv_cust.setText(getResources().getString(R.string.button_tv_nottrigger));
            }
        }
    }

    @Override
    public void onClick(View v){
        if((getResources().getString(R.string.button_tv_nottrigger)).equals(buttons_tv_exte.getText().toString())){
            buttons_tv_exte.setText(getResources().getString(R.string.button_tv_trigger));
        }
        else{
            buttons_tv_exte.setText(getResources().getString(R.string.button_tv_nottrigger));
        }
    }

    public void click(View v){
        if((getResources().getString(R.string.button_tv_nottrigger)).equals(buttons_tv_xml.getText().toString())){
            buttons_tv_xml.setText(getResources().getString(R.string.button_tv_trigger));
        }
        else{
            buttons_tv_xml.setText(getResources().getString(R.string.button_tv_nottrigger));
        }
    }
}