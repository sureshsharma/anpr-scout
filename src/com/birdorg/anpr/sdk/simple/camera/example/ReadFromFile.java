package com.birdorg.anpr.sdk.simple.camera.example;

import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by john on 7/22/2014.
 */
public class ReadFromFile {

    public String readFromFileSDKCut() {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //get current date time with Date()
        Date date = new Date();
        String pathsdk =  "/sdcard/sdk/example/images/"+dateFormat.format(date)+"/";

        File dirr = new File(pathsdk);//"/sdcard/sdk/example/images/");

        String str = "FILE";
        if (dirr.isDirectory()) {
            str = "--->>DIR";
        }


        String tt = "TARGA IS NULL";
        int sz = 0;
        Vector vec = new Vector();
        String some = null;
        for (File imageFile : dirr.listFiles()) {

            if (imageFile != null) {
                //  array[i++] = imageFile.getName();
                tt = imageFile.getName();

                int len = tt.length();
                some = tt.substring(2, (len - 4));
                String ss = some;

                ss = "AA" + ss;
                vec.add(sz, ss);
                sz++;
            }
            // Toast.makeText(getBaseContext(), "PIRA: " + tt, Toast.LENGTH_SHORT).show();

        }
        return some;
    }
    public String readFromFileSDK() {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //get current date time with Date()
        Date date = new Date();
        String pathsdk =  "/sdcard/sdk/example/images/"+dateFormat.format(date)+"/";

        File dirr = new File(pathsdk);//"/sdcard/sdk/example/images/");

        String str = "FILE";
        if (dirr.isDirectory()) {
            str = "--->>DIR";
        }


        String tt = "TARGA IS NULL";
        int sz = 0;
        Vector vec = new Vector();
        String some = null;
        for (File imageFile : dirr.listFiles()) {

            //  array[i++] = imageFile.getName();
            tt = imageFile.getName();


           // ss = "AA"+ss;
            vec.add(sz, tt);
            sz++;
            // Toast.makeText(getBaseContext(), "PIRA: " + tt, Toast.LENGTH_SHORT).show();

        }
        return tt;
    }
}
