package com.gafitescu.lab2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter {
    private final Activity context;
    private final List<Item> items;

    public CustomListAdapter(Activity context, List<Item> items) {

        super(context, R.layout.listview_row, items);
        this.context = context;
        this.items = items;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null, true);
        TextView textField = (TextView) rowView.findViewById(R.id.list_item);
        textField.setText(items.get(position).getItemName());
        return rowView;
    }

    ;
}
