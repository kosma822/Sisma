package it.gamesandapps.nasadata.services;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class PosizioneService extends Service {

    PowerManager.WakeLock wakeLock;
    LocationManager locationManager;
    Intent i;
    public static String AZIONE = "GPS";
    public static String TAG = "posizione";
    public PosizioneService() {}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(AZIONE, "Servizio Avviato");

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "DoNotSleep");

        i = new Intent(AZIONE);
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER))
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER))
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, listener);

        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(loc!=null)
            inviaBroadcast(loc);
    }

    private LocationListener listener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {

            // Log.e("GPS", "Posizione Aggiornata");

            if (location == null)
                return;
            if (isConnectingToInternet(getApplicationContext())) {

                JSONObject posizione = new JSONObject();
                try {

                    posizione.put("lat", location.getLatitude());
                    posizione.put("lng", location.getLongitude());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i.putExtra(TAG, posizione.toString());
                sendBroadcast(i);
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            /*
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                bestProvider = LocationManager.GPS_PROVIDER;
            } else {
                bestProvider = LocationManager.NETWORK_PROVIDER;
            }
            */
        }

        @Override
        public void onProviderEnabled(String provider) {
            /*
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                bestProvider = LocationManager.GPS_PROVIDER;
            } else {
                bestProvider = LocationManager.NETWORK_PROVIDER;
            }
            */
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };

    public static boolean isConnectingToInternet(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (NetworkInfo anInfo : info)
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    private void inviaBroadcast(Location location){
        JSONObject posizione = new JSONObject();
        try {

            posizione.put("lat", location.getLatitude());
            posizione.put("lng", location.getLongitude());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        i.putExtra(TAG, posizione.toString());
        sendBroadcast(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(AZIONE, "Servizio arrestato");
        if(wakeLock.isHeld())
            wakeLock.release();
    }
}