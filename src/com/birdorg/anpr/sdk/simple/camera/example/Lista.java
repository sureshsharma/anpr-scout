package com.birdorg.anpr.sdk.simple.camera.example;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.EditText;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.text.Editable;

import java.util.Set;
import java.util.HashSet;

public class Lista extends ListActivity {
    private ProgressDialog pDialog;
    private static final String TARGA_URL = "http://www.comport.first.al/anpr/lista.php";

    private static final String TAG_NGJYRA = "ngjyra";
    private static final String TAG_TARGA= "targa";
    private static final String TAG_POSTS = "posts";
    private static final String TAG_GJOBA = "gjoba";
    private static final String TAG_GJOBA_STATUS = "gjoba_status";
    private static final String TAG_MARKA = "marka";
    private static final String TAG_SIGURACION = "siguracion";
    private static final String TAG_PRONAR = "pronar";
    private static final String TAG_SGS = "sgs";
    private static final String TAG_IMAZHI = "imazhi";
    ArrayAdapter<String> myAdapter;
    EditText inputSearch;

    private JSONArray mPlates = null;

    // manages all of our comments in a list.
    private ArrayList<HashMap<String, String>> mPlatesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // note that use read_comments.xml instead of our single_post.xml
        setContentView(R.layout.listatargave);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        new LoadLista().execute();
    }

    public void updateJSONdata() {
        mPlatesList = new ArrayList<HashMap<String, String>>();
        JSONParser jParser = new JSONParser();

        JSONObject json = jParser.getJSONFromUrl(TARGA_URL);
        try {
            mPlates = json.getJSONArray(TAG_POSTS);

            // looping through all posts according to the json object returned
            for (int i = 0; i < mPlates.length(); i++) {
                JSONObject c = mPlates.getJSONObject(i);

                // gets the content of each tag
                String targa = c.getString(TAG_TARGA);
                String ngjyra = c.getString(TAG_NGJYRA);
                String marka = c.getString(TAG_MARKA);
                String gjoba_status = c.getString(TAG_GJOBA_STATUS);
                String siguracion = c.getString(TAG_SIGURACION);
                String sgs = c.getString(TAG_SGS);
                String pronar = c.getString(TAG_PRONAR);
                String imazhi = c.getString(TAG_IMAZHI);
                String po = "po";
                String jo = "jo";
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();

                map.put(TAG_NGJYRA, ngjyra);
                map.put(TAG_MARKA, marka);
                map.put(TAG_SIGURACION, siguracion);
                if(gjoba_status.equals("1")) {
                    map.put(TAG_GJOBA, po);
                }
                else if((gjoba_status == null) || (gjoba_status.equals("0"))){
                    map.put(TAG_GJOBA, jo);
                }
                map.put(TAG_PRONAR, pronar);
                map.put(TAG_SGS, sgs);
                map.put(TAG_IMAZHI, imazhi);
                map.put(TAG_TARGA, targa);
                // adding HashList to ArrayList
                mPlatesList.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts the parsed data into the listview.
     */
    private void updateList() {
        ListView lv = getListView();

        ListAdapter adapter = new SimpleAdapter(this, mPlatesList,
                R.layout.plates, new String[] { TAG_TARGA, TAG_NGJYRA, TAG_MARKA, TAG_SIGURACION, TAG_GJOBA, TAG_PRONAR, TAG_SGS, TAG_IMAZHI },
                new int[] { R.id.title, R.id.ngjyra, R.id.marka, R.id.siguracion, R.id.gjoba, R.id.pronar, R.id.sgs, R.id.imazhi});
        setListAdapter(adapter);

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String targa = ((TextView)view.findViewById(R.id.title)).getText().toString();
                String ngjyra = ((TextView)view.findViewById(R.id.ngjyra)).getText().toString();
                String marka = ((TextView)view.findViewById(R.id.marka)).getText().toString();
                String siguracion = ((TextView)view.findViewById(R.id.siguracion)).getText().toString();
                String gjoba = ((TextView)view.findViewById(R.id.gjoba)).getText().toString();
                String sgs = ((TextView)view.findViewById(R.id.sgs)).getText().toString();
                String pronar = ((TextView)view.findViewById(R.id.pronar)).getText().toString();
                String imazhi = ((TextView)view.findViewById(R.id.imazhi)).getText().toString();
                String username = getIntent().getExtras().getString("Username");

                Intent intent = new Intent(Lista.this,Info.class);
                intent.putExtra("Targa",targa);
                intent.putExtra("Ngjyra",ngjyra);
                intent.putExtra("Marka",marka);
                intent.putExtra("Gjoba",gjoba);
                intent.putExtra("Siguracion",siguracion);
                intent.putExtra("Sgs",sgs);
                intent.putExtra("Pronar",pronar);
                intent.putExtra("Username",username);
                intent.putExtra("FotoPath",imazhi);
                startActivity(intent);
            }
        });
    }
    public void search(){
        ListView lv = getListView();
        Set<String> unionSet = new HashSet<String>();
        for (HashMap<String, String> hashMap : mPlatesList) {
            for(String key : hashMap.keySet())
                if(key.equals(TAG_TARGA))
                    unionSet.add(hashMap.get(key));
        }

        String[] table = unionSet.toArray(new String[unionSet.size()]);
        inputSearch = (EditText) findViewById (R.id.searchText);
        myAdapter = new ArrayAdapter<String>(this, R.layout.plates, R.id.title, table);
        lv.setAdapter(myAdapter);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Lista.this.myAdapter.getFilter().filter(cs);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }

    public class LoadLista extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Lista.this);
            pDialog.setMessage("Targat po ngarkohen...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected Boolean doInBackground(Void... arg0) {
            updateJSONdata();
           // search();
            return null;

        }
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            updateList();
        }
    }
}

