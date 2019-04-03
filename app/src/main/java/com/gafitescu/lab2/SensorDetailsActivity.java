package com.gafitescu.lab2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

public class SensorDetailsActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_details);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        int savedExtra = getIntent().getIntExtra("type",0);
        String savedName = getIntent().getStringExtra("name");
        TextView myText = (TextView)findViewById(R.id.sname);
        myText.setText("Name: "+savedName);
        sensor = sensorManager.getDefaultSensor(savedExtra);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<sensorEvent.values.length;i++)
        {
            sb.append(sensorEvent.values[i]);
            sb.append("\t");
        }
        TextView myText = (TextView) findViewById(R.id.values);
        myText.setText("Values: "+sb.toString());

    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
