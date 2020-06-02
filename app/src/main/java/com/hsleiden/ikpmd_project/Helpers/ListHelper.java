package com.hsleiden.ikpmd_project.Helpers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hsleiden.ikpmd_project.Models.Route;
import com.hsleiden.ikpmd_project.R;

public class ListHelper extends BaseAdapter {

    private Context context;
    private Activity activity;
    private Route[] routes;
    private int layout;
    LayoutInflater inflater;

    public ListHelper(Context context, Activity activity, Route[] routes, int layout) {
        this.context = context;
        this.activity = activity;
        this.routes = routes;
        this.layout = layout;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return routes.length;
    }

    @Override
    public Object getItem(int position) {
        return routes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = this.inflater.inflate(this.layout, null);
        TextView textStart = (TextView) convertView.findViewById(R.id.textStart);
        TextView textEnd = (TextView) convertView.findViewById(R.id.textEnd);

        textStart.setText("Start: \n" + routes[position].getStart());
        textEnd.setText("Einde: \n" + routes[position].getEnd());

        return convertView;
    }
}
