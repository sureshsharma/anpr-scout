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
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.sauronsoftware.ftp4j.FTPClient;

public class Info extends Activity {
    ImageView imageView2;
    ImageView imageView3;

    /*********  work only for Dedicated IP ***********/
    static final String FTP_HOST= "comport.first.al";

    /*********  FTP USERNAME ***********/
    static final String FTP_USER = "androidfirst";

    /*********  FTP PASSWORD ***********/
    static final String FTP_PASS  ="MNXjqJET";



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

        if(getIntent().getExtras().getString("Gjoba").equals("jo")) {
            imageView3.setImageResource(R.drawable.status2);
        }else if(getIntent().getExtras().getString("Gjoba").equals("po"))
        {
            imageView3.setImageResource(R.drawable.status1);
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

    public void uploadFile(File fileName, String name){


        FTPClient client = new FTPClient();

        try {

            client.connect(FTP_HOST,21);
            client.login(FTP_USER, FTP_PASS);
            client.setType(FTPClient.TYPE_BINARY);

            //  client.changeDirectory("/mdoklea/");


            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            //get current date time with Date()
            Date date = new Date();

            //  client.createDirectory(dateFormat.format(date));
            Format formatter = new SimpleDateFormat("HH.mm.ss");
            String time = formatter.format(new Date());

            client.changeDirectory("/comport.first.al/anpr/uploads/"+dateFormat.format(date)+"/");
            client.createDirectory(time+name+"/");
            client.changeDirectory("/comport.first.al/anpr/uploads/"+dateFormat.format(date)+"/"+time+name+"/");

            client.upload(fileName);


           /* Format formatter = new SimpleDateFormat("HH.mm.ss");
            String time = formatter.format(new Date());

            String name1 = fileName.getName();

            File fn = new File(time+name);
            fileName.renameTo(fn);
             client.upload(fn);*/
            // client.upload(f2);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                client.disconnect(true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
}