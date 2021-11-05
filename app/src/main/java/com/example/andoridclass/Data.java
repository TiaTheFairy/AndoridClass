package com.example.andoridclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Data extends AppCompatActivity {

    private EditText data_et_amount;
    private Button data_bt_update;
    private Button data_bt_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        data_et_amount = findViewById(R.id.bt_et_amount);
        data_bt_update = findViewById(R.id.data_bt_update);
        data_bt_save = findViewById(R.id.data_bt_save);

        data_bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Data.this,"Data updated!",Toast.LENGTH_LONG).show();
            }
        });

        data_bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data_bookAmount = data_et_amount.getText().toString();

                if("".equals(data_bookAmount)){
                    Toast.makeText(Data.this, "Fill all field first!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Data.this, "Data saved!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}