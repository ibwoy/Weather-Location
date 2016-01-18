package mai.uom.weather.location;


import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    private MapFragmentContainer mapFragmentContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Create the map container fragment **/
        mapFragmentContainer = new MapFragmentContainer();
        /** Handles the MapContainerFragment callbacks **/
        mapFragmentContainer.setMapContainerCallbacks(new MapFragmentContainer.MapContainerCallbacks() {
            @Override

            public void onLocationPressed(final double lat, final double lng) {

                // Creating a Coordinates object triggered by user input
                Coordinates coor = new Coordinates(lat, lng);

                // Creating the OpenWeather object for constructing the specific url
                GeoLocationURL geo = new GeoLocationURL(coor);

                // Connection to openweathermap.org
                Connection c = new Connection(geo);

                // Parser initiation. Setting CallBack functionality
                ResponseParser parser = new ResponseParser();
                parser.setCallBack(new ResponseParser.WeatherResultCallback() {
                    public void callBack(WeatherResults w) {

                        /** Show the result dialog **/
                        initializeResultDialog(w).show();

                        /** Add to the map a marker with the current data **/
                        mapFragmentContainer.addMarker(lat,lng,w);

                    }
                });
                /** Execute the async task of the openweather **/
                parser.execute(c);

            }
        });

        /** FragmentManager show the map container fragment **/
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragmentContainer, "my_google_map").commit();

    }

    /**
     * Creates and returns the Result dialog with the given results
     * @param w Weather results
     * @return The dialog
     */
    private Dialog initializeResultDialog(WeatherResults w) {
        /** Convert the weather to have one decimal **/
        DecimalFormat df = new DecimalFormat("#.0");
        String weather = "";
        /** Create the weather string from the WeatherResults **/
        weather += df.format(w.getTemp()) + "°" + " " + getText(R.string.in)
                + " " + w.getCity() + " " + w.getCountry();
        /** Create the dialog and display the data to the user **/
        DisplayResultsDialog displayResultsDialog = new DisplayResultsDialog(MainActivity.this);
        displayResultsDialog.setDisplayResultsCallbacks(new DisplayResultsDialog.DisplayResultDialogCallbacks() {
            @Override
            public void OnSaveButtonClicked() {
                /** On save button click save the weather result object **/
            }
        });
        displayResultsDialog.setWeather(weather,w.getWeatherDesc(),
                IndexingImages.getInstance().getImageResources(w.getWeatherIcon()));
        return displayResultsDialog.create();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /** Create activity menu **/
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pin:
                mapFragmentContainer.pushCurrentLocation();
                break;
            case R.id.action_clear_map:
                showConfirmClearMapDialog();
                break;
            case R.id.action_satellite:
                /** Alert the checked Status **/
                item.setChecked(!item.isChecked());
                mapFragmentContainer.satelliteView(item.isChecked());
                break;
            case R.id.action_restore:
                /** Call restore activity **/
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Show a confirmation dialog to before clear the map
     */
    private void showConfirmClearMapDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.clear_map);
        builder.setMessage(R.string.clear_map_message);
        builder.setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.clear, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mapFragmentContainer.clearMap();
            }
        });
        builder.create().show();
    }
}
