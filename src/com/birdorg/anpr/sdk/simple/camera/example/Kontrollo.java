package com.birdorg.anpr.sdk.simple.camera.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import it.sauronsoftware.ftp4j.FTPClient;

public class Kontrollo extends Activity
{

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    private static final String POST_KONTROLLO_URL = "http://www.comport.first.al/anpr/kontrollo.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    TextView txt_targa,txt_date, txt_id;

    /*********  work only for Dedicated IP ***********/
    static final String FTP_HOST= "comport.first.al";

    /*********  FTP USERNAME ***********/
    static final String FTP_USER = "androidfirst";

    /*********  FTP PASSWORD ***********/
    static final String FTP_PASS  ="MNXjqJET";


    private String CAP_PATH="CAP_PATH";

    static int ANPR_REQUEST = 1;	// Identifier of our calling

    AnprSdkMenu mm = new AnprSdkMenu();

    private static String targafin = "TARGA" ;
    Context context = this;
    public static  String pathsdk;
    String time;


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
            String getf = fileName.getName();
            CAP_PATH = "http://www.comport.first.al/anpr/uploads/"+dateFormat.format(date)+"/"+time+name+"/"+getf;
            client.changeDirectory("/comport.first.al/anpr/uploads/"+dateFormat.format(date)+"/"+time+name+"/");

            client.upload(fileName);


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

                    //  String user = getIntent().getExtras().getString("Username");

                    //  Toast.makeText(context, "Username: "+user, Toast.LENGTH_LONG);


                    targafin = s;
                    String name = pathsdk+s+".jpg";    // photo file on the SD card
                    File fs = new File(name);
                    uploadFile(fs, "-"+s);


                    new PostComment().execute();

                    Intent intent = new Intent(Kontrollo.this, Info.class);
                    intent.putExtra("Targa", s);
                    startActivity(intent);


                }


            } else {
                TextView text = (TextView) findViewById(R.id.Text_Main_Result_ALPR);
                text.setText("---NO PHOTO TAKEN");
            }
        }
    }
    class PostComment extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Kontrollo.this);
            pDialog.setMessage("Posting Values...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String targa = targafin;

            String pajisje_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => "+c.getTime());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String data = df.format(c.getTime());


            String username = getIntent().getExtras().getString("Username");
            String cap_img = CAP_PATH;
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("targa", targa));
                params.add(new BasicNameValuePair("data", data));
                params.add(new BasicNameValuePair("pajisje_id", pajisje_id));
                params.add(new BasicNameValuePair("user_id", username));
                params.add(new BasicNameValuePair("capture_image", CAP_PATH));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        POST_KONTROLLO_URL, "POST", params);

                // full json response
                Log.d("Post Comment attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Values Added!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Insert Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Kontrollo.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }

}

