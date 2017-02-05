package it.gamesandapps.nasadata;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class JSONParser {

    public JSONParser() {}

    public String simpleRequest(String url, String method){
        try {
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)obj.openConnection();
            conn.setConnectTimeout(15000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod(method);

            Log.e("URL", conn.getURL().toString());

            int responseCode = conn.getResponseCode();
            StringBuilder response = new StringBuilder();

            if(responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_PARTIAL){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;

                while((line = br.readLine()) != null){
                    response.append(line);
                }
                return response.toString();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
