package com.birdorg.anpr.sdk.simple.camera.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Printo extends Activity {
    TextView txt_targa, txt_tipi, txt_gjoba, txt_shkelja,txt_gjoba2, txt_shkelja2,txt_shkelja3,txt_date, txt_id, txt_punonjesi;
    Button print;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        print = (Button) findViewById(R.id.button);
        String pajisja_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        txt_targa = (TextView) findViewById(R.id.txt_targa);
        txt_tipi = (TextView) findViewById(R.id.txt_tipi);
        txt_gjoba = (TextView) findViewById(R.id.txt_gjoba);
        txt_shkelja = (TextView) findViewById(R.id.txt_shkelja);
        txt_gjoba2 = (TextView) findViewById(R.id.txt_gjoba2);
        txt_shkelja2 = (TextView) findViewById(R.id.txt_shkelja2);
        txt_shkelja3 = (TextView) findViewById(R.id.txt_shkelja3);
        txt_date = (TextView) findViewById(R.id.txt_date);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_punonjesi= (TextView) findViewById(R.id.txt_punonjesi);

        txt_targa.setText(""+getIntent().getExtras().getString("Targa"));
        txt_tipi.setText(""+getIntent().getExtras().getString("Marka"));
        txt_shkelja.setText("" + getIntent().getExtras().getString("Gjoba"));
        txt_shkelja2.setText("" + getIntent().getExtras().getString("Gjoba2"));
        txt_shkelja3.setText("" + getIntent().getExtras().getString("Shuma3"));

        txt_gjoba.setText(""+getIntent().getExtras().getString("Shuma"));
        txt_gjoba2.setText(""+getIntent().getExtras().getString("Shuma2"));
        txt_date.setText(""+formattedDate);
        txt_id.setText(""+pajisja_id);
        txt_punonjesi.setText(""+getIntent().getExtras().getString("Username"));
        print.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v) {
                Intent i = new Intent(Printo.this, AnprSdkMain.class);
                startActivity(i);
            }
        });
    }

}
