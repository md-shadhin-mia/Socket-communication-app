package com.example.socketcomunication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Messages> {
    final ArrayList<Messages> arr;
    final Context context;
    public ListAdapter(@NonNull Context context, ArrayList<Messages> messages) {
        super(context, 0, messages);
        arr = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.designlist, parent, false);
        Messages message = getItem(position);
        TextView textView = (TextView) rowView.findViewById(R.id.title);
        textView.setText(message.getTitle());
        RelativeLayout.LayoutParams layout= (RelativeLayout.LayoutParams) textView.getLayoutParams();
        if(!message.isSender()) {
            layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            textView.setLayoutParams(layout);
        }
        else {
            layout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            textView.setLayoutParams(layout);
        }

        return rowView;
    }
}
