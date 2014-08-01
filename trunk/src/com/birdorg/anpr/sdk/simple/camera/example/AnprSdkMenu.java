package com.birdorg.anpr.sdk.simple.camera.example;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.sauronsoftware.ftp4j.FTPClient;

public class AnprSdkMenu extends ListActivity {

    static final String[] MOBILE_OS =
            new String[] { "Kontrollo", "Lista", "Kontrollet e Sotme", "Penalizimet e Sotme", "Konfigurimet", "FtpUpload"};

    public static String SDK_PATH;
    public static String SDK_TEMP_PATH;


    /*********  work only for Dedicated IP ***********/
    static final String FTP_HOST= "comport.first.al";

    /*********  FTP USERNAME ***********/
    static final String FTP_USER = "androidfirst";

    /*********  FTP PASSWORD ***********/
    static final String FTP_PASS  ="MNXjqJET";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new MenuArrayAdapter(this, MOBILE_OS));


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        String cheese = MOBILE_OS[position];
        if (position == 2)
        {
            cheese = "KontrolletSotme";
        }else if (position == 3)
        {
            cheese = "PenalizimetSotme";
        }
        try {
            String username = getIntent().getExtras().getString("Username");
            Class ourClass = Class.forName("com.birdorg.anpr.sdk.simple.camera.example."+cheese);
            Intent ourIntent = new Intent(AnprSdkMenu.this, ourClass);
            ourIntent.putExtra("Username",username);
            startActivity(ourIntent);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }
    public void createDir(){


        FTPClient client = new FTPClient();

        try {

            client.connect(FTP_HOST,21);
            client.login(FTP_USER, FTP_PASS);
            client.setType(FTPClient.TYPE_BINARY);

            client.changeDirectory("/comport.first.al/anpr/uploads/");


            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            //get current date time with Date()
            Date date = new Date();

            client.createDirectory(dateFormat.format(date));



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
