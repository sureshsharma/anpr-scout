package com.birdorg.anpr.sdk.simple.camera.example;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by john on 7/22/2014.
 */
public class PenalizimetSotme  extends ListActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.kontrollet_sotme);

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

        // set the message to display
        alertbox.setMessage("Nuk jane shtuar penalizime per diten e sotme.");
        alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                //   Class ourClass = Class.forName("com.birdorg.anpr.sdk.simple.camera.example.ANPRInfo");
                Intent ourIntent = new Intent(PenalizimetSotme .this, AnprSdkMenu.class);

                startActivity(ourIntent);
            }
        });
        alertbox.show();
    }
        @Override
    public void onClick(View view) {

    }
}
