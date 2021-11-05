package com.example.andoridclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {

    private EditText editBook_et_name;
    private Button editBook_bt_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        editBook_et_name = findViewById(R.id.editBook_et_name);
        editBook_bt_save = findViewById(R.id.editBook_bt_save);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        String name = intent.getStringExtra("name");

        if(null!=name){
            editBook_et_name.setText(name);
        }

        editBook_bt_save.setOnClickListener(view-> {
            Intent backIntent = new Intent();
            backIntent.putExtra("position", position);
            backIntent.putExtra("name", editBook_et_name.getText().toString());
            setResult(BookListMainActivity.RESULT_CODE_ADD_DATA, backIntent);
            EditBookActivity.this.finish();
        });
    }
}