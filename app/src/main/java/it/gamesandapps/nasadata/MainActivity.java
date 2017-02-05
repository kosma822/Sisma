package it.gamesandapps.nasadata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.gamesandapps.nasadata.adapters.EQAdapter;
import it.gamesandapps.nasadata.objects.Earthquake;
import it.gamesandapps.nasadata.objects.Geometry;
import it.gamesandapps.nasadata.objects.Properties;
import it.gamesandapps.nasadata.services.PosizioneService;

import static it.gamesandapps.nasadata.objects.Utils.API_EARTHQUAKE;

public class MainActivity extends AppCompatActivity {

    private TextView tv_count;
    private Button bt_refresh, bt_map;
    private ListView lv;
    private EditText et_gradi;
    private ArrayList<Earthquake> arrayList;
    private EQAdapter adapter;
    private String query;
    private double latitudine=0,longitudine=0;
    private String gradi = "10";
    private BroadcastReceiver receiver;

    private static int REFRESH = 10000;
    private Handler h = new Handler();
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            if(!et_gradi.getText().toString().isEmpty())
                if(latitudine!=0 || longitudine!=0)
                    getEarthQuakes();
            h.postDelayed(this, REFRESH);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        if(bar!=null) {
            bar.setLogo(R.mipmap.ic_launcher);
            bar.setDisplayUseLogoEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        arrayList = new ArrayList<>();
        adapter = new EQAdapter(this, arrayList);
        lv = (ListView)findViewById(R.id.lv_earthquake);

        bt_refresh = (Button)findViewById(R.id.bt_refresh);

        bt_map = (Button)findViewById(R.id.bt_map);
        bt_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });

        tv_count = (TextView)findViewById(R.id.tv_count);
        et_gradi = (EditText) findViewById(R.id.et_gradi);
        lv.setAdapter(adapter);

        et_gradi.setText(gradi);
        et_gradi.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                MainActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                return true;
            }
        });
        /*bt_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEarthQuakes();
            }
        });*/

        Intent locService = new Intent(this,  PosizioneService.class);
        startService(locService);

        h.post(r);
    }

    @Override
    protected void onStart() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String s = intent.getStringExtra(PosizioneService.TAG);

                if(s!=null) {
                    Log.e("POSIZIONE", s);
                    try {
                        JSONObject pos = new JSONObject(s);

                        latitudine = pos.getDouble("lat");
                        longitudine = pos.getDouble("lng");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        registerReceiver(receiver, new IntentFilter(PosizioneService.AZIONE));
        super.onStart();
    }

    @Override
    protected void onStop() {
        if(receiver != null)
            unregisterReceiver(receiver);
        super.onStop();
    }

    private void getEarthQuakes(){
        query = API_EARTHQUAKE +
                "&latitude=" + latitudine +
                "&longitude=" + longitudine +
                "&maxradius=" + et_gradi.getText().toString();

        new GetEarthquakes().execute(query);
    }

    private class GetEarthquakes extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            if(arrayList!=null)
                arrayList.clear();
        }

        @Override
        protected String doInBackground(String... params) {
            UserFunctions functions = new UserFunctions();
            return functions.getEarthquakes(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {

            if(s!=null)

                // Log.d("Response" , s);

                try {
                    Object tokener = new JSONTokener(s).nextValue();

                    if(tokener instanceof JSONObject){

                        JSONObject obj = (JSONObject)tokener;

                        JSONArray jsonArray = obj.getJSONArray("features");

                        tv_count.setText("Ci sono " + jsonArray.length() + " terremoti qui vicino");

                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject o = jsonArray.getJSONObject(i);
                            JSONObject p_obj = o.getJSONObject("properties");
                            JSONObject g_obj = o.getJSONObject("geometry");

                            Earthquake earthquake = new Earthquake();
                            Properties properties = new Properties();
                            Geometry geometry = new Geometry();

                            List<Double> glist = new ArrayList<>();
                            JSONArray coordinates = g_obj.getJSONArray("coordinates");
                            for(int j=0;j<coordinates.length();j++)
                                glist.add(coordinates.getDouble(j));
                            geometry.setCoordinates(glist);
                            earthquake.setGeometry(geometry);

                            properties.setMag(p_obj.getDouble("mag"));
                            properties.setPlace(p_obj.getString("place"));
                            properties.setTime(p_obj.getLong("time"));
                            earthquake.setProperties(properties);

                            arrayList.add(earthquake);

                            Collections.sort(arrayList, new Comparator<Earthquake>() {
                                @Override
                                public int compare(Earthquake e1, Earthquake e2) {
                                    if(e1.getProperties().getMag() > e2.getProperties().getMag()) return -1;
                                    if(e1.getProperties().getMag() < e2.getProperties().getMag()) return 1;
                                    return 0;
                                }
                            });

                        }
                    }

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }
    }
}
