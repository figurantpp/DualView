package com.figurantp.dualview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EntryListActivity extends AppCompatActivity implements View.OnClickListener{


    EntryAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    public static final String RESULT = "return";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);

        recyclerView = findViewById(R.id.entryListRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EntryAdapter(this, Entry.getExampleList(this));

        recyclerView.setAdapter(adapter);

        floatingActionButton = findViewById(R.id.entryListFloatingActionButton);

        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Entry result = adapter.getSelectedEntry();

        if (result == null) {
            Toast.makeText(this, R.string.errorNothingSelected, Toast.LENGTH_LONG).show();
            return;
        }

        Intent caller = getIntent();
        caller.putExtra(RESULT, result.toBundle());
        setResult(RESULT_OK, caller);

        finish();

    }
}