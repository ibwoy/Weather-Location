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
                // Log.i("lat",Double.toString(lat));
                // Log.i("lng",Double.toString(lng));
                Coordinates coor = new Coordinates(lat, lng);
                OpenWeather ow = new OpenWeather(coor);

                Connection c = new Connection(ow);


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
