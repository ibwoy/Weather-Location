package mai.uom.weather.location;

import android.app.Activity;

import android.app.Dialog;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Display a dialog with the weather and
 * the description of the given location
 */
public class DisplayResultsDialog {
    private String weather;
    private String description;
    private int icon;
    private Activity activity;

    /**
     * Constructor
     * @param activity
     */
    public DisplayResultsDialog(Activity activity) {
        this.activity = activity;
    }

    /**
     * Set weather data
     * @param weather text
     * @param description text
     * @param resIcon Resources image
     */
    public void setWeather(String weather,String description,int resIcon) {
        this.weather = weather;
        this.description = description;
        this.icon = resIcon;
    }

    /**
     * Create the dialog and returns it as a Dialog object
     * @return Dialog
     */
    public Dialog create() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.weather);
        builder.setPositiveButton(R.string.ok, null);
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_weather_results, (ViewGroup)activity.getCurrentFocus(),false);
                builder.setView(view);
        builder.setView(view);
        notifyDialogData(view);
        builder.setPositiveButton(R.string.ok, null);
        return builder.create();
    }

    /**
     * Notify the dialog view components with the
     * data for display
     * @param view The view of the dialog
     */
    private void notifyDialogData(View view) {
        ImageView imageView = (ImageView)view.findViewById(R.id.imWeather);
        imageView.setImageResource(icon);
        TextView tv = (TextView)view.findViewById(R.id.tvWeather);
        tv.setText(weather);
        tv = (TextView)view.findViewById(R.id.tvDesc);
        tv.setText(description);

    }





}
