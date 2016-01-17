package mai.uom.weather.location;

import android.app.Activity;

import android.app.Dialog;

import android.content.DialogInterface;
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
    private DisplayResultDialogCallbacks callbacks;

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
        /** Handle the Save Button click event **/
        builder.setNegativeButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /** Close the dialog **/
                dialog.dismiss();
                /** process the event **/
                saveButtonClick();
            }
        });
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
    private void saveButtonClick() {
        if(callbacks!=null)
            callbacks.OnSaveButtonClicked();
    }
    /**
     * Set DisplayResults dialog callbacks
     * @param callbacks
     */
    public void setDisplayResultsCallbacks(DisplayResultDialogCallbacks callbacks) {
        this.callbacks = callbacks;
    }
    /**
     * DisplayDialogCallbacks
     */
    public interface DisplayResultDialogCallbacks {
        void OnSaveButtonClicked();
    }





}
