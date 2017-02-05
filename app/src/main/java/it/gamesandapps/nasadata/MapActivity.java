package it.gamesandapps.nasadata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.gamesandapps.nasadata.objects.Earthquake;
import it.gamesandapps.nasadata.objects.Geometry;
import it.gamesandapps.nasadata.objects.Properties;

import static it.gamesandapps.nasadata.objects.Utils.API_EARTHQUAKE;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        getEarthQuakes();
    }

    private void getEarthQuakes(){
        String query = API_EARTHQUAKE +
                "&latitude = 39" +
                "&longitude = 8.6" +
                "&maxradius = 70";

        new GetEarthquakes().execute(query);
    }

    private class GetEarthquakes extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            if(mMap!=null)
                mMap.clear();
        }

        @Override
        protected String doInBackground(String... params) {
            UserFunctions functions = new UserFunctions();
            return functions.getEarthquakes(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {

            if(s!=null)

                Log.d("Response" , s);

            try {
                Object tokener = new JSONTokener(s).nextValue();

                if(tokener instanceof JSONObject){

                    JSONObject obj = (JSONObject)tokener;

                    JSONArray jsonArray = obj.getJSONArray("features");

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

                        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                        b = Bitmap.createScaledBitmap(b, 75,75, false);

                        Marker m = mMap.addMarker(new MarkerOptions()
                                .title(earthquake.getProperties().getPlace())
                                .icon(BitmapDescriptorFactory.fromBitmap(b))
                                .snippet("Magnitude: " + earthquake.getProperties().getMag())
                                .position(new LatLng(
                                        earthquake.getGeometry().getCoordinates().get(1),
                                        earthquake.getGeometry().getCoordinates().get(0)))
                        );

                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
