package com.figurantp.dualview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnCheckList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheckList = findViewById(R.id.mainBtnCheckList);
        btnCheckList.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, EntryListActivity.class);
        startActivity(intent);
    }

}