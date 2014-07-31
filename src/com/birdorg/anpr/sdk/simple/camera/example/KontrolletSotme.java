package com.birdorg.anpr.sdk.simple.camera.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by john on 7/22/2014.
 */
public class KontrolletSotme extends ListActivity{


    Vector<String> vec = new Vector<String>();
    Vector<String> vecc= new Vector<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.listatargave);


        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //get current date time with Date()
        Date date = new Date();
        String pathsdk =  "/sdcard/sdk/example/images/"+dateFormat.format(date)+"/";

        File dirr = new File(pathsdk);//"/sdcard/sdk/example/images/");

        String str = "FILE";
        if (dirr.isDirectory()) {
            str = "--->>DIR";


            String tt = "TARGA IS NULL";
            int sz = 0;

            for (File imageFile : dirr.listFiles()) {

                if(!(imageFile.isDirectory()) && (imageFile != null)){
                    Toast.makeText(getBaseContext(), "Eimai Image " + tt, Toast.LENGTH_SHORT).show();

                    //  array[i++] = imageFile.getName();
                    tt = imageFile.getName();

                    int len = tt.length();

                    String ss = tt.substring(0, (len - 4));
                    vecc.add(ss);

                    sz++;
                } else if (imageFile.isDirectory()){
                    imageFile.delete();
                    Toast.makeText(getBaseContext(), "EImai Dir " + tt, Toast.LENGTH_SHORT).show();

                    break;
                }
            }
        }


       if(vecc == null) {

           AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

           // set the message to display
           alertbox.setMessage("Nuk jane shtuar kontrolle per diten e sotme.");
           alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {

                   //   Class ourClass = Class.forName("com.birdorg.anpr.sdk.simple.camera.example.ANPRInfo");
                   Intent ourIntent = new Intent(KontrolletSotme.this, AnprSdkMenu.class);

                   startActivity(ourIntent);
               }
           });
           alertbox.show();
       }
        setListAdapter(new KontrollArrayAdapter(this, vecc ));

    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        String cheese = vecc.get(position);


      //  String item = ((TextView)v.findViewById(R.id.plate_name)).getText().toString();

        Toast.makeText(getBaseContext(), "Cheese: "+cheese, Toast.LENGTH_SHORT).show();
        try {
            Class ourClass = Class.forName("com.birdorg.anpr.sdk.simple.camera.example.Info");
            Intent intent = new Intent(KontrolletSotme.this, Info.class);

            intent.putExtra("Plate", cheese);
            intent.putExtra("Color", "");
            intent.putExtra("Type", "");
            startActivity(intent);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();

        }


    }


}

