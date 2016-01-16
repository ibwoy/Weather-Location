package mai.uom.weather.location;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
                        displayResultDialog(w);

                    }
                });

                parser.execute(c);

            }
        });

        /** FragmentManager show the map container fragment **/
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragmentContainer, "my_google_map").commit();

    }


    private void displayResultDialog(WeatherResults w) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.weather);
        View view = getLayoutInflater().inflate(R.layout.dialog_weather_results,(ViewGroup)findViewById(android.R.id.content),false);
        builder.setView(view);
        showData(view,w);
        builder.setPositiveButton(R.string.ok, null);
        builder.create().show();
    }
    private void showData(View view,WeatherResults w) {
        ImageView imageView = (ImageView)view.findViewById(R.id.imWeather);
        imageView.setImageResource(IndexingImages.getInstance().getImageResources(w.getWeathericon()));

        TextView tv = (TextView)view.findViewById(R.id.tvTemp);
        tv.setText(Double.toString(w.getTemp()));
        tv = (TextView)view.findViewById(R.id.tvCity);
        tv.setText(w.getCity());
        tv = (TextView)view.findViewById(R.id.tvCountry);
        tv.setText(w.getCountry());

        tv = (TextView)view.findViewById(R.id.tvDesc);
        tv.setText(w.getWeatherdescription());

    }
}
