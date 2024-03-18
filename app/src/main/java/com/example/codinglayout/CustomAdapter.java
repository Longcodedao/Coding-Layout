package com.example.codinglayout;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class CustomAdapter extends ArrayAdapter<Float>{

    private ArrayList<Float> resultList;
    private Context context;

    public CustomAdapter(Context context, int layoutId, ArrayList<Float> resultList){
        super(context, layoutId, resultList);
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Float result = resultList.get(position);

        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(android.R.layout.simple_list_item_1, parent, false);

        }

        TextView text = (TextView) convertView.findViewById(android.R.id.text1);

        if (result != null){
            text.setText("Result: " + result);
        }else{
            text.setText("Error");
        }

        return convertView;

    }


}
