package com.example.dth_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dth_project.PojoPack.Clist;
import com.example.dth_project.PojoPack.DetailsPojo;

import java.util.ArrayList;
import java.util.List;

public class AdapterName extends ArrayAdapter<Customerbal> {
    private static Object DetailsPojo;
    private static final Object Clist = DetailsPojo;
    List<Clist> clist;
    private Context context;
    private int resource;
    private List<Customerbal> items, tempItems, suggestions;
    public AdapterName(Context context, int custom_list_item, int resource, String[] arr) {

            super(context, resource);

            this.context = context;
            this.resource = resource;
            tempItems = new ArrayList<>(items);
            suggestions = new ArrayList<>();
    }

    public AdapterName(Context context, int simple_dropdown_item_1line, int resource, ArrayList<String> listInstituteNames) {
        super(context, resource);
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(resource, parent, false);
            }
            Customerbal fruit = getItem(position);
            TextView name = (TextView) view.findViewById(R.id.text_view_list_item);

            name.setText(Clist.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
    @Nullable
    @Override
    public Customerbal getItem(int position) {
        return items.get(position);
    }
    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}
