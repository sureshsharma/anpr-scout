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

public class AnprSdkMenu extends ListActivity {

    static final String[] MOBILE_OS =
            new String[] { "Kontrollo", "Lista", "Kontrollet e Sotme", "Penalizimet e Sotme", "Konfigurimet", "FtpUpload"};

    public static String SDK_PATH;
    public static String SDK_TEMP_PATH;


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

}
