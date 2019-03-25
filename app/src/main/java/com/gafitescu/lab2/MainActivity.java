package com.gafitescu.lab2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Item> items = new ArrayList<>();
    ListView listView;
    private static final String TAG = "MainActivity";

    private void populateItems() {

        for (int i = 0; i < 5; i++) {
            String name = "Item " + Integer.toString(i);
            String description = "Description for item " + Integer.toString(i);
            Item item = new Item(name, description);
            this.items.add(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            Log.v(TAG, "On create, text salvat: " + savedInstanceState.getString("TEXT"));
        this.populateItems();
        setContentView(R.layout.activity_main);
        CustomListAdapter adapter = new CustomListAdapter(this, items);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                String message = items.get(position).getItemDescription();
                intent.putExtra("description", message);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle item selection
        CharSequence title = item.getTitle();
        switch (item.getItemId()) {
            case R.id.visit:
                Intent visit = new Intent(this, ViewSiteActivity.class);
                startActivity(visit);
                break;
            case R.id.share:
                Intent share = new Intent(this, ShareActivity.class);
                startActivity(share);
                break;
            case R.id.login:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Login");

                LayoutInflater inflater = this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.login, null));

                builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String greeting = "Welcome !";
                        Toast toast = Toast.makeText(MainActivity.this, greeting, Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(MainActivity.this, "You canceled the login", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.settings:
                Intent i = new Intent(this, Preferences.class);
                startActivity(i);
        }
        return true;

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TEXT", "Text salvat");

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String savedText = savedInstanceState.getString("TEXT");
        Log.d(TAG, "On Restore, textul salvat: " + savedText);

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "Se apeleaza onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "Se apeleaza onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "Se apeleaza onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "Se apeleaza onPause");
        String filename = "myfile.txt";
        String name = "";
        FileOutputStream outputStream;
        StringBuilder sb = new StringBuilder();
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            for (int i = 0; i < this.items.size(); i++) {
                name = this.items.get(i).getItemName();
                sb.append(name);
                sb.append("\n");
            }
            outputStream.write(sb.toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            FileInputStream fis = getApplicationContext().openFileInput("myfile.txt");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            Log.v(TAG, sb.toString());

        } catch (FileNotFoundException e) {
            Log.e(TAG, "Exceptie");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Exceptie");
        } catch (IOException e) {
            Log.e(TAG, "Exceptie");
        }

    }
}
