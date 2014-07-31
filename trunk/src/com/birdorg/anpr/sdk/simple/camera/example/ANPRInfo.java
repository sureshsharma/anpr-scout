package com.birdorg.anpr.sdk.simple.camera.example;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 7/18/2014.
 */
public class ANPRInfo extends Activity implements View.OnClickListener {




    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityinfo);


        setTitle("FIRST AUTO-LPR - Version 1.0.8 - Tirana");




        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);



        TextView tv3 = (TextView) findViewById(R.id.intent3);
        //tv3.setText("Targa: ");




        ReadFromFile rd = new  ReadFromFile();
        String tr = rd.readFromFileSDK();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //get current date time with Date()
        Date date = new Date();
        String pathsdk =  "/sdcard/sdk/example/images/"+dateFormat.format(date)+"/";

        String name = pathsdk+tr;    // photo file on the SD card
        Bitmap bitmap = BitmapFactory.decodeFile(name);

        String tt = rd.readFromFileSDKCut();





        String sm = tt.substring(0, 1);

        tv3.setText("AA "+tt);
        Toast.makeText(getApplicationContext(), "Targa: AA"+tt+"-", Toast.LENGTH_SHORT).show();

        int some = Integer.parseInt(sm);

        if (some == 1) {

            TextView tv4 = (TextView) findViewById(R.id.intent4);
            tv4.setText("Ngjyra: E Bardhe");
            TextView tv5 = (TextView) findViewById(R.id.intent5);
            tv5.setText("Marka: Peageut");
            TextView tv6 = (TextView) findViewById(R.id.intent6);
            tv6.setText("Gjoba: Jo");
            TextView tv7 = (TextView) findViewById(R.id.intent7);
            tv7.setText("Kontroll Teknik: Po");
            TextView tv8 = (TextView) findViewById(R.id.intent8);
            tv8.setText("Siguracione: Po");


        }else if (some == 2 ){

            TextView tv4 = (TextView) findViewById(R.id.intent4);
            tv4.setText("Ngjyra: E Zeze ");
            TextView tv5 = (TextView) findViewById(R.id.intent5);
            tv5.setText("Marka: Ford");
            TextView tv6 = (TextView) findViewById(R.id.intent6);
            tv6.setText("Gjoba: Po");
            TextView tv7 = (TextView) findViewById(R.id.intent7);
            tv7.setText("Kontroll Teknik: Jo");
            TextView tv8 = (TextView) findViewById(R.id.intent8);
            tv8.setText("Siguracione: Jo");



        }else if (some == 3)
        {
            TextView tv4 = (TextView) findViewById(R.id.intent4);
            tv4.setText("Ngjyra: E Kuqe");
            TextView tv5 = (TextView) findViewById(R.id.intent5);
            tv5.setText("Marka: Toyota");
            TextView tv6 = (TextView) findViewById(R.id.intent6);
            tv6.setText("Gjoba: Jo");
            TextView tv7 = (TextView) findViewById(R.id.intent7);
            tv7.setText("Kontroll Teknik: Po");
            TextView tv8 = (TextView) findViewById(R.id.intent8);
            tv8.setText("Siguracione: Po");


        }else if (some == 4)
        {
            TextView tv4 = (TextView) findViewById(R.id.intent4);
            tv4.setText("Ngjyra: Blu");
            TextView tv5 = (TextView) findViewById(R.id.intent5);
            tv5.setText("Marka: BMW");
            TextView tv6 = (TextView) findViewById(R.id.intent6);
            tv6.setText("Gjoba: Po");
            TextView tv7 = (TextView) findViewById(R.id.intent7);
            tv7.setText("Kontroll Teknik: Po");
            TextView tv8 = (TextView) findViewById(R.id.intent8);
            tv8.setText("Siguracione: Po");



        }else if (some == 5)
        {
            TextView tv4 = (TextView) findViewById(R.id.intent4);
            tv4.setText("Ngjyra: Gri");
            TextView tv5 = (TextView) findViewById(R.id.intent5);
            tv5.setText("Marka: Renault");
            TextView tv6 = (TextView) findViewById(R.id.intent6);
            tv6.setText("Gjoba: Po");
            TextView tv7 = (TextView) findViewById(R.id.intent7);
            tv7.setText("Kontroll Teknik: Po");
            TextView tv8 = (TextView) findViewById(R.id.intent8);
            tv8.setText("Siguracione: Jo");


        }else if (some == 6)
        {
            TextView tv4 = (TextView) findViewById(R.id.intent4);
            tv4.setText("Ngjyra: E Zeze");
            TextView tv5 = (TextView) findViewById(R.id.intent5);
            tv5.setText("Marka: Chevrolet");
            TextView tv6 = (TextView) findViewById(R.id.intent6);
            tv6.setText("Gjoba: Jo");
            TextView tv7 = (TextView) findViewById(R.id.intent7);
            tv7.setText("Kontroll Teknik: Po");
            TextView tv8 = (TextView) findViewById(R.id.intent8);
            tv8.setText("Siguracione: Po");



        }else {

            TextView tv4 = (TextView) findViewById(R.id.intent4);
            tv4.setText("Ngjyra: E Kuqe");
            TextView tv5 = (TextView) findViewById(R.id.intent5);
            tv5.setText("Marka: Ford");
            TextView tv6 = (TextView) findViewById(R.id.intent6);
            tv6.setText("Gjoba: Jo");
            TextView tv7 = (TextView) findViewById(R.id.intent7);
            tv7.setText("Kontroll Teknik: Po");
            TextView tv8 = (TextView) findViewById(R.id.intent8);
            tv8.setText("Siguracione: Jo");

        }
        if (bitmap != null) {
            imageView2.setImageBitmap(bitmap);
        } else {
            imageView2.setImageResource(R.drawable.car2);

        }
        Button but1 = (Button) findViewById(R.id.button);
        but1.setOnClickListener(ANPRInfo.this);
        Button but2 = (Button) findViewById(R.id.button2);
        but2.setOnClickListener(ANPRInfo.this);
        Button but3 = (Button) findViewById(R.id.button3);
        but3.setOnClickListener(ANPRInfo.this);

    }
    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.button) {
            Class ourClass = null;
            try {
                ourClass = Class.forName("com.birdorg.anpr.sdk.simple.camera.example.ANPRPenalizo");
                Intent ourIntent = new Intent(ANPRInfo.this, ourClass);
                startActivity(ourIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }else if (id == R.id.button2) {
            Class ourClass = null;
            try {
                ourClass = Class.forName("com.birdorg.anpr.sdk.simple.camera.example.KontrolletSotme");
                Intent ourIntent = new Intent(ANPRInfo.this, ourClass);
                startActivity(ourIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }else if (id == R.id.button3) {
        Class ourClass = null;
        try {
            ourClass = Class.forName("com.birdorg.anpr.sdk.simple.camera.example.Kontrollo");
            Intent ourIntent = new Intent(ANPRInfo.this, ourClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    }
}


