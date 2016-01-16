package mai.uom.weather.location;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by Panos on 16/1/2016.
 */
public class DisplayResultsDialog {
    private WeatherResults results;
    private Context context;

    public DisplayResultsDialog(Context context) {
        this.context = context;
    }
    public void setWeatherResults(WeatherResults results) {
        this.results = results;
    }

    public void create() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.weather);
        builder.setPositiveButton(R.string.ok,null);







    }





}
