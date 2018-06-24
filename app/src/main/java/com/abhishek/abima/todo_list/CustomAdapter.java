package com.abhishek.abima.todo_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private ArrayList<String> mTitle;


    CustomAdapter(@NonNull Context context, ArrayList<String> title) {
        super(context, R.layout.new_listview, title);
        this.mContext = context;
        this.mTitle = title;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.new_listview, parent, false);

        TextView myText = (TextView) listItem.findViewById(R.id.todotask);
        myText.setText(mTitle.get(position));

        return listItem;
    }
}































