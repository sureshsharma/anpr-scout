package com.birdorg.anpr.sdk.simple.camera.example;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 7/30/2014.
 */
public class StoreFile {


    public boolean storeImage(String filename) {
        //get path to external storage (SD card)
        String iconsStoragePath = Environment.getExternalStorageDirectory() + "/myAppDir/myImages/";
        File sdIconStorageDir = new File(iconsStoragePath);

        //create storage directories, if they don't exist
        sdIconStorageDir.mkdirs();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ");
        //get current date time with Date()
        Date date = new Date();

        Format formatter = new SimpleDateFormat("HH.mm.ss");
        String time = formatter.format(new Date());

        File dir = new File("/sdcard/sdk/example/images/"+dateFormat.format(date)+"/");



        try {
            File file = new File ("/sdcard/sdk/example/images/"+dateFormat.format(date)+"/"+filename+".txt");
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
            myOutWriter.append("----"+filename);
            myOutWriter.close();
            fOut.close();
        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        } catch (IOException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        }
        return false;
    }
}
