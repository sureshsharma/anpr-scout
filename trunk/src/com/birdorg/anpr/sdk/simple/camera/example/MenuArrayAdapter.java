package com.birdorg.anpr.sdk.simple.camera.example;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by gh on 7/21/2014.
 */
public class MenuArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MenuArrayAdapter(Context context, String[] values) {
        super(context, R.layout.list_menu, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_menu, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values[position]);

        // Change icon based on name
        String s = values[position];
//"Kontrollet e Sotme", "Penalizimet e Sotme",
        System.out.println(s);

        if (s.equals("Kontrollo")) {
            imageView.setImageResource(R.drawable.cam);
        } else if (s.equals("Lista")) {
            imageView.setImageResource(R.drawable.listm);
        } else if (s.equals("Konfigurimet")) {
            imageView.setImageResource(R.drawable.set);
        } else if (s.equals("Kontrollet e Sotme")) {
            imageView.setImageResource(R.drawable.ksot);
        }else if (s.equals("Penalizimet e Sotme")) {
            imageView.setImageResource(R.drawable.write);
        }
        return rowView;
    }
}
