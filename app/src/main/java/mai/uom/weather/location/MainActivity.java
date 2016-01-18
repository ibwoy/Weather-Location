package mai.uom.weather.location;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DecimalFormat;

import mai.uom.weather.location.datahelpers.DataWeatherHelper;


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

            public void onLocationPressed(double lat,double lng) {
                /** Process the point **/
                processPoint(lat,lng,-1);

            }
        });

        /** FragmentManager show the map container fragment **/
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragmentContainer, "my_google_map").commit();

    }

    /**
     * Process the point to get the weather results and display them
     * @param lat latitude
     * @param lng longitude
     * @param id location id
     */
    private void processPoint(final double lat,final double lng,final int id) {
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
                /** Setting the exact cords to w */
                w.setId(id);
                w.setLat(lat);
                w.setLon(lng);
                /** Show the result dialog **/
                initializeResultDialog(w).show();

                /** Add to the map a marker with the current data **/
                mapFragmentContainer.addMarker(w);

            }
        });
        /** Execute the async task of the openweather **/
        parser.execute(c);
    }

    /**
     * Creates and returns the Result dialog with the given results
     * @param w Weather results
     * @return The dialog
     */
    private Dialog initializeResultDialog(final WeatherResults w) {
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
                DataWeatherHelper wHelper = new DataWeatherHelper(MainActivity.this);
                wHelper.create(w);
            }
        });
        displayResultsDialog.setWeather(weather, w.getWeatherDesc(),
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /** Handle the result from the Restore activity **/
        if(resultCode == 100 && data !=null) {
            int id = data.getIntExtra("id",-1);
            double lat = data.getDoubleExtra("lat", 0);
            double lng = data.getDoubleExtra("lng",0);
            Log.e("id",Integer.toString(id));
            Log.e("lat",Double.toString(lat));
            Log.e("lng",Double.toString(lng));

            processPoint(lat, lng, id);
        }
        super.onActivityResult(requestCode, resultCode, data);
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
                Intent restore = new Intent(MainActivity.this,RestoreActivity.class);
                startActivityForResult(restore,89);
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
