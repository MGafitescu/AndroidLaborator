package com.gafitescu.lab2;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class SensorActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private static final String TAG = "Sensors";
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        final List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        SensorListAdapter adapter = new SensorListAdapter(this, deviceSensors);
        listView = (ListView) findViewById(R.id.sensor_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(SensorActivity.this, SensorDetailsActivity.class);
                int message = deviceSensors.get(position).getType();
                intent.putExtra("type",message);
                intent.putExtra("name",deviceSensors.get(position).getName());
                startActivity(intent);
            }
        });

    }
}
