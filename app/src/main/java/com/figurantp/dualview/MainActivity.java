package com.figurantp.dualview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnCheckList;

    TextView txtKey;
    TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheckList = findViewById(R.id.mainBtnCheckList);

        txtKey = findViewById(R.id.mainTxtKeyData);
        txtValue = findViewById(R.id.mainTxtValueData);

        btnCheckList.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, EntryListActivity.class);
        startActivityForResult(intent, 0);
    }

    private void clearViews() {
        txtValue.setText("");
        txtKey.setText("");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent caller) {
        super.onActivityResult(requestCode, resultCode, caller);

        // I miss you ?. operator

        if (caller == null) {
            clearViews();
            return;
        }

        Bundle data = caller.getBundleExtra(EntryListActivity.RESULT);

        if (data == null) {
            clearViews();
        }
        else {

            Entry result = Entry.fromBundle(data);

            txtKey.setText(result.getKey());
            txtValue.setText(result.getValue());

        }
    }
}