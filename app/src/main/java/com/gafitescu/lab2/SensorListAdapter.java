package com.gafitescu.lab2;

import android.app.Activity;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SensorListAdapter extends ArrayAdapter {
    private final Activity context;
    private final List<Sensor> deviceSensors;

    public SensorListAdapter(Activity context, List<Sensor> items) {

        super(context, R.layout.listview_row, items);
        this.context = context;
        this.deviceSensors = items;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.sensor_row, null, true);
        TextView textField = (TextView) rowView.findViewById(R.id.sensor_name);
        textField.setText("Name: " +deviceSensors.get(position).getName());
        textField = (TextView) rowView.findViewById(R.id.sensor_vendor);
        textField.setText("Vendor: " +deviceSensors.get(position).getVendor());
        textField = (TextView) rowView.findViewById(R.id.sensor_type);
        textField.setText("Type: " +deviceSensors.get(position).getStringType());
        return rowView;
    }


}
