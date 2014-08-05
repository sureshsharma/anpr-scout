package com.birdorg.anpr.sdk.simple.camera.example;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.content.Intent;
import android.provider.Settings.Secure;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.widget.CheckBox;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class Penalizo extends Activity {
    private Spinner spinner1, spinner2,  spinner3;
    private Button print;
    CheckBox checkbox1;
    CheckBox checkbox2;
    CheckBox checkbox3;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    private static final String POST_COMMENT_URL = "http://www.comport.first.al/anpr/penalizo.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalize);
        final  AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        checkbox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkBox);
        checkbox3 = (CheckBox) findViewById(R.id.checkBox3);
        final String username = getIntent().getExtras().getString("Username");
        TextView tv3 = (TextView) findViewById(R.id.intent3);
        TextView tv4 = (TextView) findViewById(R.id.intent4);
        TextView tv5 = (TextView) findViewById(R.id.intent5);
        TextView tv6 = (TextView) findViewById(R.id.pathfoto);

        tv3.setText("" + getIntent().getExtras().getString("Targa"));
        tv4.setText("" + getIntent().getExtras().getString("Ngjyra"));
        tv5.setText("" + getIntent().getExtras().getString("Marka"));
        tv6.setText(""+getIntent().getExtras().getString("FotoPath"));
        String path = tv6.getText().toString();

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new SelectingItem());
        print = (Button) findViewById(R.id.button);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new SelectingItem());
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(new SelectingItem());

        print.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                if(checkbox1.isChecked())
                {
                    Intent i = new Intent(Penalizo.this, Printo.class);
                    i.putExtra("Targa", getIntent().getExtras().getString("Targa"));
                    i.putExtra("Marka", getIntent().getExtras().getString("Marka"));
                    i.putExtra("Gjoba", String.valueOf(spinner1.getSelectedItem()));
                    i.putExtra("Shuma", checkbox1.getText().toString());
                    i.putExtra("Gjoba2", "");
                    i.putExtra("Shuma2", "");
                    i.putExtra("Shuma3", "");
                    i.putExtra("Username", username);
                    startActivity(i);
                }
                if(checkbox2.isChecked())
                {
                    Intent i = new Intent(Penalizo.this, Printo.class);
                    i.putExtra("Targa", getIntent().getExtras().getString("Targa"));
                    i.putExtra("Marka", getIntent().getExtras().getString("Marka"));
                    i.putExtra("Gjoba", String.valueOf(spinner2.getSelectedItem()));
                    i.putExtra("Shuma", checkbox2.getText().toString());
                    i.putExtra("Gjoba2", "");
                    i.putExtra("Shuma2", "");
                    i.putExtra("Shuma3", "");
                    i.putExtra("Username", username);
                    startActivity(i);
                }
                if(checkbox3.isChecked())
                {
                    Intent i = new Intent(Penalizo.this, Printo.class);
                    i.putExtra("Targa", getIntent().getExtras().getString("Targa"));
                    i.putExtra("Marka", getIntent().getExtras().getString("Marka"));
                    i.putExtra("Shuma", String.valueOf(spinner3.getSelectedItem()));
                    i.putExtra("Gjoba2", "");
                    i.putExtra("Gjoba", "");
                    i.putExtra("Shuma2", "");
                    i.putExtra("Shuma3", "");
                    i.putExtra("Username", username);
                    startActivity(i);
                }
                if(!checkbox1.isChecked()&&!checkbox2.isChecked()&&!checkbox3.isChecked())
                {
                    alertbox.setMessage("Ju lutem perzgjidhni nje gjobe.");
                    alertbox.show();
                }
                if(checkbox1.isChecked()&&checkbox2.isChecked()&&!checkbox3.isChecked())
                {
                    Intent i = new Intent(Penalizo.this, Printo.class);
                    i.putExtra("Targa", getIntent().getExtras().getString("Targa"));
                    i.putExtra("Marka", getIntent().getExtras().getString("Marka"));
                    i.putExtra("Gjoba", String.valueOf(spinner1.getSelectedItem()));
                    i.putExtra("Gjoba2", String.valueOf(spinner2.getSelectedItem()));
                    i.putExtra("Shuma", checkbox1.getText().toString());
                    i.putExtra("Shuma2", checkbox2.getText().toString());
                    i.putExtra("Shuma3", "");
                    i.putExtra("Username", username);
                    startActivity(i);
                }
                if(checkbox1.isChecked()&&checkbox2.isChecked()&&checkbox3.isChecked())
                {
                    Intent i = new Intent(Penalizo.this, Printo.class);
                    i.putExtra("Targa", getIntent().getExtras().getString("Targa"));
                    i.putExtra("Marka", getIntent().getExtras().getString("Marka"));
                    i.putExtra("Gjoba", String.valueOf(spinner1.getSelectedItem()));
                    i.putExtra("Gjoba2", String.valueOf(spinner2.getSelectedItem()));
                    i.putExtra("Shuma3", String.valueOf(spinner3.getSelectedItem()));
                    i.putExtra("Shuma", checkbox1.getText().toString());
                    i.putExtra("Shuma2", checkbox2.getText().toString());
                    i.putExtra("Username", username);
                    startActivity(i);
                }
                if(checkbox2.isChecked()&&checkbox3.isChecked()&&!checkbox1.isChecked())
                {
                    Intent i = new Intent(Penalizo.this, Printo.class);
                    i.putExtra("Targa", getIntent().getExtras().getString("Targa"));
                    i.putExtra("Marka", getIntent().getExtras().getString("Marka"));
                    i.putExtra("Gjoba", String.valueOf(spinner2.getSelectedItem()));
                    i.putExtra("Shuma2", String.valueOf(spinner3.getSelectedItem()));
                    i.putExtra("Shuma", checkbox2.getText().toString());
                    i.putExtra("Shuma3", "");
                    i.putExtra("Gjoba2", "");
                    i.putExtra("Username", username);
                    startActivity(i);
                }
                if(checkbox1.isChecked()&&checkbox3.isChecked()&&!checkbox2.isChecked())
                {
                    Intent i = new Intent(Penalizo.this, Printo.class);
                    i.putExtra("Targa", getIntent().getExtras().getString("Targa"));
                    i.putExtra("Marka", getIntent().getExtras().getString("Marka"));
                    i.putExtra("Gjoba", String.valueOf(spinner1.getSelectedItem()));
                    i.putExtra("Shuma2", String.valueOf(spinner3.getSelectedItem()));
                    i.putExtra("Shuma", checkbox1.getText().toString());
                    i.putExtra("Gjoba2", "");
                    i.putExtra("Shuma3", "");
                    i.putExtra("Username", username);
                    startActivity(i);
                }
                new PostComment().execute();
            }
        });
    }

    class PostComment extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Penalizo.this);
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
            String targa = getIntent().getExtras().getString("Targa");


            String pajisje_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => "+c.getTime());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            String username = getIntent().getExtras().getString("Username");

            TextView tv6 = (TextView) findViewById(R.id.pathfoto);
            tv6.setText(""+getIntent().getExtras().getString("FotoPath"));
            String path = tv6.getText().toString();
            //We need to change this:
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Penalizo.this);

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("user_id", username));
                params.add(new BasicNameValuePair("pajisje_id", pajisje_id));
                params.add(new BasicNameValuePair("data", formattedDate));
                params.add(new BasicNameValuePair("targa", targa));
                params.add(new BasicNameValuePair("imazhi", path));
                if(checkbox1.isChecked()) {
                    params.add(new BasicNameValuePair("gjoba1", String.valueOf(spinner1.getSelectedItem())));
                    params.add(new BasicNameValuePair("shuma1", checkbox1.getText().toString()));
                }
                if(checkbox2.isChecked())
                {
                    params.add(new BasicNameValuePair("gjoba2",  String.valueOf(spinner2.getSelectedItem())));
                    params.add(new BasicNameValuePair("shuma2", checkbox2.getText().toString()));
                }
                if(checkbox3.isChecked())
                {
                    params.add(new BasicNameValuePair("gjoba3",  String.valueOf(spinner3.getSelectedItem())));
                }
                if(checkbox1.isChecked()&&checkbox2.isChecked()&&!checkbox3.isChecked())
                {
                    params.add(new BasicNameValuePair("gjoba1", String.valueOf(spinner1.getSelectedItem())));
                    params.add(new BasicNameValuePair("gjoba2",  String.valueOf(spinner2.getSelectedItem())));
                    params.add(new BasicNameValuePair("shuma1", checkbox1.getText().toString()));
                    params.add(new BasicNameValuePair("shuma2", checkbox2.getText().toString()));
                }
                if(checkbox1.isChecked()&&checkbox2.isChecked()&&checkbox3.isChecked())
                {
                    params.add(new BasicNameValuePair("gjoba1", String.valueOf(spinner1.getSelectedItem())));
                    params.add(new BasicNameValuePair("gjoba2",  String.valueOf(spinner2.getSelectedItem())));
                    params.add(new BasicNameValuePair("gjoba3",  String.valueOf(spinner3.getSelectedItem())));
                    params.add(new BasicNameValuePair("shuma1", checkbox1.getText().toString()));
                    params.add(new BasicNameValuePair("shuma2", checkbox2.getText().toString()));
                }
                if(checkbox2.isChecked()&&checkbox3.isChecked()&&!checkbox1.isChecked())
                {
                    params.add(new BasicNameValuePair("gjoba2",  String.valueOf(spinner2.getSelectedItem())));
                    params.add(new BasicNameValuePair("gjoba3",  String.valueOf(spinner3.getSelectedItem())));
                    params.add(new BasicNameValuePair("shuma2", checkbox2.getText().toString()));
                }
                if(checkbox1.isChecked()&&checkbox3.isChecked()&&!checkbox2.isChecked())
                {
                    params.add(new BasicNameValuePair("gjoba1",  String.valueOf(spinner1.getSelectedItem())));
                    params.add(new BasicNameValuePair("gjoba3",  String.valueOf(spinner3.getSelectedItem())));
                    params.add(new BasicNameValuePair("shuma1", checkbox1.getText().toString()));
                }
                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        POST_COMMENT_URL, "POST", params);

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
                Toast.makeText(Penalizo.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }
}

