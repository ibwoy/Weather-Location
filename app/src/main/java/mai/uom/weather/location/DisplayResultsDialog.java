package mai.uom.weather.location;

import android.app.AlertDialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Panos on 16/1/2016.
 */
public class DisplayResultsDialog {
    private WeatherResults results;
    private Context context;

    private View view;

    public DisplayResultsDialog(Context context,View  view) {
        results = new WeatherResults();

        this.view = view;
        this.context = context;
    }
    public void setWeatherResults(WeatherResults results) {
        this.results = results;
    }

    public void create() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.weather);
        builder.setPositiveButton(R.string.ok, null);
        builder.setView(view);
        builder.create().show();

    }





}
