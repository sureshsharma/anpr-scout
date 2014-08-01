package com.birdorg.anpr.sdk.simple.camera.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by john on 7/25/2014.
 */
public class KontrollArrayAdapter extends ArrayAdapter<String> {
    private final Context context;

    Vector<String> vec = new Vector<String>();
    public KontrollArrayAdapter(Context context, Vector<String> vec) {
        super(context, R.layout.kontrolletsotme, vec);
        this.context = context;
        this.vec = vec;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.kontrolletsotme, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);

        textView.setText(vec.get(position));
        // Change icon based on name
        String s = vec.get(position);

        String ss = s.substring(2,3);
        int some = Integer.parseInt(ss);
        if(some == 1 || some == 3 || some == 6)
        {
            imageView.setImageResource(R.drawable.ksotj);
        }else
            imageView.setImageResource(R.drawable.ksotk);

        return rowView;
    }
}
