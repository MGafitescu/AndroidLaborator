package com.gafitescu.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String savedExtra = getIntent().getStringExtra("description");
        TextView myText = (TextView) findViewById(R.id.detailID);
        myText.setText(savedExtra);
    }
}
