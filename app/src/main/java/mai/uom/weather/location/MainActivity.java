package mai.uom.weather.location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private MapFragmentContainer mapFragmentContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Create the map container fragment **/
        mapFragmentContainer = new MapFragmentContainer();
        mapFragmentContainer.setMapContainerCallbacks(new MapFragmentContainer.MapContainerCallbacks() {
            @Override

            public void onMarkerAdded(double lat, double lng) {

                // Creating a Coordinates object triggered by user input
                Coordinates coor = new Coordinates(lat, lng);

                // Creating the OpenWeather object for constructing the specific url
                OpenWeather ow = new OpenWeather(coor);

                // Connection to openweathermap.org
                Connection c = new Connection(ow);

                // Parser initiation. Setting CallBack functionality
                ResponseParser parser = new ResponseParser();
                parser.setCallBack(new ResponseParser.WeatherResultCallback()
                {
                    public void callBack(WeatherResults w)
                    {
                        System.out.println(w.toString());
                    }
                });

                parser.execute(c);

            }
        });


        /** FragmentManager show the map container fragment **/
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragmentContainer, "my_google_map").commit();

    }
}
