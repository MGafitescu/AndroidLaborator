package com.gafitescu.lab2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

public class ShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
    }

    protected void ShareApp(View btn)
    {
        String content = "Hey! Check out this app: OUR APP. ";
        EditText edit = (EditText)findViewById(R.id.share_text);
        content = content + edit.getText().toString();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,content);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }

}
