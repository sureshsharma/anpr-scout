package com.birdorg.anpr.sdk.simple.camera.example;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

/**
 * Created by john on 7/18/2014.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.sauronsoftware.ftp4j.FTPClient;

public class Info extends Activity {
    ImageView imageView2;
    ImageView imageView3;
    String path;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityinfo);

        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);

        TextView tv3 = (TextView) findViewById(R.id.intent3);
        TextView tv4 = (TextView) findViewById(R.id.intent4);
        TextView tv5 = (TextView) findViewById(R.id.intent5);
        TextView tv6 = (TextView) findViewById(R.id.intent6);
        TextView tv7 = (TextView) findViewById(R.id.intent7);
        TextView tv8 = (TextView) findViewById(R.id.intent8);
        TextView tv9 = (TextView) findViewById(R.id.intent9);
        TextView tv10 = (TextView) findViewById(R.id.intent10);

        Button btnValidate = (Button)findViewById(R.id.button);
        Button lista = (Button)findViewById(R.id.button2);
        Button kontrollo = (Button)findViewById(R.id.button3);
        String username = getIntent().getExtras().getString("Username");

        tv3.setText(""+getIntent().getExtras().getString("Targa"));
        tv4.setText("Ngjyra: "+getIntent().getExtras().getString("Ngjyra"));
        tv5.setText("Marka: "+getIntent().getExtras().getString("Marka"));
        tv6.setText("Gjoba: "+getIntent().getExtras().getString("Gjoba"));
        tv7.setText("Siguracion: "+getIntent().getExtras().getString("Siguracion"));
        tv8.setText("Sgs: "+getIntent().getExtras().getString("Sgs"));
        tv9.setText("Pronar: "+getIntent().getExtras().getString("Pronar"));
        tv10.setText(""+getIntent().getExtras().getString("FotoPath"));

        if(getIntent().getExtras().getString("Gjoba").equals("jo")) {
            imageView3.setImageResource(R.drawable.status2);
        }else if(getIntent().getExtras().getString("Gjoba").equals("po"))
        {
            imageView3.setImageResource(R.drawable.status1);
        }else
            imageView3.setImageResource(R.drawable.status1);


        path = tv10.getText().toString();
        Bitmap bitmap = getBitmap(path);



        if (bitmap != null) {

            ImageView imageView = (ImageView) findViewById(R.id.imageView2);
            imageView.setImageBitmap(bitmap);

        }
        btnValidate.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {
                final TextView plate  = (TextView) findViewById(R.id.intent3);
                final TextView ngjyra  = (TextView) findViewById(R.id.intent4);
                final TextView marka  = (TextView) findViewById(R.id.intent5);
               // final TextView pronar  = (TextView) findViewById(R.id.intent9);

                Intent intent = new Intent(Info.this,Penalizo.class);
                intent.putExtra("Targa", plate.getText().toString());
                intent.putExtra("Ngjyra", ngjyra.getText().toString());
                intent.putExtra("Marka", marka.getText().toString());
                intent.putExtra("FotoPath", path);
                intent.putExtra("Username", getIntent().getExtras().getString("Username"));
                startActivity(intent);

            }
        });
        lista.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {
                Intent intent = new Intent(Info.this,Lista.class);
                intent.putExtra("Username", getIntent().getExtras().getString("Username"));
                startActivity(intent);

            }
        });
        kontrollo.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {
                Intent intent = new Intent(Info.this,Kontrollo.class);
                intent.putExtra("Username", getIntent().getExtras().getString("Username"));
                startActivity(intent);
            }
        });
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