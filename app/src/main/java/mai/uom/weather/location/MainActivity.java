package mai.uom.weather.location;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
                geoLocationURL geo = new geoLocationURL(coor);

                // Connection to openweathermap.org
                Connection c = new Connection(geo);

                // Parser initiation. Setting CallBack functionality
                ResponseParser parser = new ResponseParser();
                parser.setCallBack(new ResponseParser.WeatherResultCallback() {
                    public void callBack(WeatherResults w) {
                        
                        /** Convert the weather to have on decimal **/
                        DecimalFormat df = new DecimalFormat("#.0");
                        String weather = "";
                        /** Create the weather string from the WeatherResults **/
                        weather += df.format(w.getTemp()) + "°" + " " + getText(R.string.in)
                                + " " + w.getCity() + " " + w.getCountry();
                        /** Create the dialog and display the data to the user **/
                        DisplayResultsDialog displayResultsDialog = new DisplayResultsDialog(MainActivity.this);
                        displayResultsDialog.setWeather(weather,w.getWeatherdescription(),
                                IndexingImages.getInstance().getImageResources(w.getWeathericon()));
                        displayResultsDialog.create().show();
                        /** Add to the map a marker with the current data **/
                        mapFragmentContainer.addMarker(lat, lng, weather, w.getWeatherdescription());

                    }
                });
                /** Execute the async task of the openweather **/
                parser.execute(c);

            }
        });

        /** FragmentManager show the map container fragment **/
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragmentContainer, "my_google_map").commit();

    }




}
