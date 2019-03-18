package com.gafitescu.lab2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ViewSiteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visit_site_actvity);

    }

    protected void VisitWebsite(View btn)
    {
        Intent browser =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/site/fiiandroidprogramming/"));
        startActivity(browser);
    }
}
