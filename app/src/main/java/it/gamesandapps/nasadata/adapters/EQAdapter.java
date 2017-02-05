package it.gamesandapps.nasadata.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import it.gamesandapps.nasadata.R;
import it.gamesandapps.nasadata.objects.Earthquake;

public class EQAdapter extends ArrayAdapter<Earthquake>{

    private Context ctx;
    private ArrayList<Earthquake> earthquakes;

    public EQAdapter(Context ctx, ArrayList<Earthquake> earthquakes) {
        super(ctx, -1, earthquakes);
        this.ctx = ctx;
        this.earthquakes = earthquakes;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Holder holder = new Holder();

        if(convertView==null){
            convertView = inflater.inflate(R.layout.list_row, null);
            holder.title = (TextView)convertView.findViewById(R.id.tv_title);
            holder.mag = (TextView)convertView.findViewById(R.id.tv_mag);
            holder.lat = (TextView)convertView.findViewById(R.id.tv_lat);
            holder.lng = (TextView)convertView.findViewById(R.id.tv_lng);
            holder.data = (TextView)convertView.findViewById(R.id.tv_data);
            convertView.setTag(holder);
        } else  {
            holder = (Holder)convertView.getTag();
        }

        Earthquake earthquake = earthquakes.get(position);

        if(earthquake!=null){

            if(earthquake.getProperties().getPlace()!=null)
                holder.title.setText(earthquake.getProperties().getPlace());
            if(earthquake.getProperties().getMag()!=null)
                holder.mag.setText(""+earthquake.getProperties().getMag());
            if(earthquake.getGeometry().getCoordinates().get(0)!=null)
                holder.lat.setText(""+earthquake.getGeometry().getCoordinates().get(0));
            if(earthquake.getGeometry().getCoordinates().get(1)!=null)
                holder.lng.setText(""+earthquake.getGeometry().getCoordinates().get(1));
            if(earthquake.getProperties().getTime()!=0) {
                DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(earthquake.getProperties().getTime());
                String data = format.format(calendar.getTime());
                holder.data.setText(data);
            }
        }

        return convertView;
    }

    static class Holder{
        TextView title;
        TextView mag;
        TextView lat;
        TextView lng;
        TextView data;
    }
}
