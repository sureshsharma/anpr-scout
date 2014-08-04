package com.birdorg.anpr.sdk.simple.camera.example;


        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.io.InputStream;
        import java.lang.reflect.Array;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Vector;

/**
 * Created by john on 8/2/2014.
 */
public class PenalizimetSotemAdapter extends ArrayAdapter<ItemKontrollo> {

    private final Context context;

    ItemKontrollo item ;
    Vector<ItemKontrollo> vec = new Vector<ItemKontrollo>();

    public PenalizimetSotemAdapter(Context context, Vector<ItemKontrollo> vecc) {

        super(context, R.layout.penalizimet_sotme, vecc);
        this.context = context;
        this.vec = vecc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.penalizimet_sotme, parent, false);

        TextView t1 = (TextView) rowView.findViewById(R.id.label_targa);
        TextView t2 = (TextView) rowView.findViewById(R.id.label_data);
        TextView t3 = (TextView) rowView.findViewById(R.id.label_user);
        TextView t4hidden = (TextView) rowView.findViewById(R.id.label_path);
        TextView t5 = (TextView) rowView.findViewById(R.id.label_gjobat);
        TextView t6 = (TextView) rowView.findViewById(R.id.label_shuma);




        t1.setText(vec.get(position).getTarga());
        t2.setText(vec.get(position).getData());
        t3.setText(vec.get(position).getUser());
        t4hidden.setText(vec.get(position).getImgpath());
        t5.setText(vec.get(position).getGjoba());
        t6.setText(vec.get(position).getShuma());


        String path = t4hidden.getText().toString();
        Bitmap bitmap = getBitmap(path);



        if (bitmap != null) {

            ImageView imageView = (ImageView) rowView.findViewById(R.id.label_capture_image);
            imageView.setImageBitmap(bitmap);

        }



        return rowView;
    }
    private Bitmap getBitmap(String url)
    {

        try {
            Bitmap bitmap=null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream input = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);

            return bitmap;
        } catch (Exception ex){
            ex.printStackTrace();

            return null;
        }
    }
}
