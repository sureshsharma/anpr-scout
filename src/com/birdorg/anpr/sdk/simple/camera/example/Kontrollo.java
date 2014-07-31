package com.birdorg.anpr.sdk.simple.camera.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import it.sauronsoftware.ftp4j.FTPClient;

public class Kontrollo extends Activity
{

    /*********  work only for Dedicated IP ***********/
    static final String FTP_HOST= "comport.first.al";

    /*********  FTP USERNAME ***********/
    static final String FTP_USER = "androidfirst";

    /*********  FTP PASSWORD ***********/
    static final String FTP_PASS  ="MNXjqJET";


    static int ANPR_REQUEST = 1;	// Identifier of our calling

    AnprSdkMenu mm = new AnprSdkMenu();
    public String targa;

    public final String targ ;
    Context context = this;
    public static  String pathsdk;
    String time;
    public Kontrollo() {
        this.targ = targa;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anpr);

        setTitle("FIRST AUTO-LPR - Version 1.0.8 - Tirana");


        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //get current date time with Date()
        Date date = new Date();
        Format formatter = new SimpleDateFormat("HH.mm.ss");
        time = formatter.format(new Date());


        pathsdk =  "/sdcard/sdk/example/images/"+dateFormat.format(date)+"/";

        // Button button = (Button) findViewById(R.id.Button_Main_StartAnpr_ALPR);
        //     button.setOnClickListener(this);





        //  @Override
        //  public void onClick(View v)
        //  {
        //  int id = v.getId();
        //  if (id == R.id.Button_Main_StartAnpr_ALPR)
        // {
        ImageView imageView = (ImageView)findViewById(R.id.Bitmap_Main_Photo_ALPR);	// delete image on screen
        imageView.setImageBitmap(null);


        Intent intent = new Intent("android.intent.action.SEND");	// set intent to call ANPR SDK
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setComponent(new ComponentName("com.birdorg.anpr.sdk.simple.camera", "com.birdorg.anpr.sdk.simple.camera.ANPRSimpleCameraSDK"));



        ///////////////////////////////////////////////// setup the parameters //////////////////

        intent.putExtra("Orientation", "landscape");	// portrait orientation

        intent.putExtra("FullScreen", false);	// not fullscreen (with titlebar)

        intent.putExtra("TitleText", "DOKLEA-Test: FIRST AUTO-LPR - Version 1.0.8 - Tirana");	// text on titlebar

        //  intent.putExtra("IndicatorVisible", true);	// ANPR indicator (litle cirle) will shown

        //    intent.putExtra("MaxRecognizeNumber", 0);	// infinite recogzing

        //   intent.putExtra("DelayAfterRecognize", 1000);	// recognized string will displayed until 3 secundum

        intent.putExtra("SoundEnable", true);	// sound will be hearing when recognized

        intent.putExtra("ResolutionSettingByUserEnable", true);	// allows user to change camera resolution

        intent.putExtra("ResolutionSettingDialogText", "Camera resolution:");	// title of the resolution setting dialog

//		    intent.putExtra("ResolutionWidth", 640);	// camera resolution x
//		    intent.putExtra("ResolutionHeight", 480);	// camera resolution y

        intent.putExtra("ResultTextColor", Color.GREEN); // color of the display of recognized string

        //  intent.putExtra("ListEnable", true);	// recognized strings displayed in list

        intent.putExtra("ListMaxItems", 1);	// max 5 items in list

        intent.putExtra("ListTextColor", 0xff7070f0);	// color of the list

        intent.putExtra("ListTitle", "Lasts:");	// title of the list

        intent.putExtra("ListDeletable", true);	// allow to delete string from list

        intent.putExtra("ListDeleteDialogMessage", "Are you sure to delete: ");	// message in delete dialog

        intent.putExtra("ListDeleteDialogYesButtonText", "Yes");	// text of yes button

        intent.putExtra("ListDeleteDialogNoButtonText", "No");	// text of no button

        intent.putExtra("ImageSaveDirectory", pathsdk);	// pictures will be saved in this directory



        //intent.putExtra("CheckServiceClass", "com.birdorg.anpr.sdk.simple.camera.example.AnprSdkExampleCheckingService");	// every NumberPlate will be checked in this service




        try
        {
            startActivityForResult(intent, ANPR_REQUEST);	// call ANPR app with intent
        }
        catch (ActivityNotFoundException e)		// if ANPR intent not found (not installed)
        {
            Toast toast = Toast.makeText(context, "The ANPR not installed!", Toast.LENGTH_LONG);
            toast.show();
        }

        // }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)	// this called when ANPR app finished
    {
        Bitmap bitmap;
        if (requestCode == ANPR_REQUEST) {// ANPR app id
            if (resultCode == RESULT_OK)    // if ANPR app terminated normally
            {


                Bundle b = data.getExtras();    // result of ANPR app  (a Bundle var)
                if (b != null) {
                    String error = b.getString("Errors");    // in bundle the recognized string

                    String s = b.getString("PlateNums");    // in bundle the error string

                    createDir();

                    StoreFile st = new StoreFile();
                    st.storeImage(time+"-"+s);



                /*        File f = new File(s+".jpg");





                        String ss = s.substring(2);
                        ss = "AA" + ss;
                        targa = ss;



                        File dirr = new File(pathsdk);//"/sdcard/sdk/example/images/");

                        File ff = new File(pathsdk+s+".jpg");

                        File fs = new File("/sdcard/sdk/example/images/26-07-2014/YX171EK.jpg");//  "/sdcard/logo.png");

                        Toast.makeText(getBaseContext(), "File: " + ff.getName(), Toast.LENGTH_SHORT).show();


                        uploadFile(fs);

                        String str = "FILE";
                        if (dirr.isDirectory()) {
                            str = "--->>DIR";
                        }


                        String tt = "TARGA IS NULL";
                        int sz = 0;
                        for (File imageFile : dirr.listFiles()) {

                            //  array[i++] = imageFile.getName();
                            uploadFile(imageFile);
                            tt = imageFile.getName();
                            sz++;
                            Toast.makeText(getBaseContext(), "PIRA: " + tt, Toast.LENGTH_SHORT).show();

                        }
                        Toast.makeText(getBaseContext(), "SIZE: " +sz, Toast.LENGTH_SHORT).show();

*/
                        try {
                            Intent intent = new Intent(Kontrollo.this, Info.class);


                            intent.putExtra("Plate", s);
                            intent.putExtra("Color", "");
                            intent.putExtra("Type", "");
                            startActivity(intent);
                            Class ourClass = Class.forName("com.birdorg.anpr.sdk.simple.camera.example.Info");

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "exception on info", Toast.LENGTH_SHORT).show();


                        }

                }


            } else {
                TextView text = (TextView) findViewById(R.id.Text_Main_Result_ALPR);
                text.setText("---NO PHOTO TAKEN");
            }
        }
    }


}

