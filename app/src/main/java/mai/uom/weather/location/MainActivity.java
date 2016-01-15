package mai.uom.weather.location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MapFragmentContainer mapFragmentContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragmentContainer = new MapFragmentContainer();


        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragmentContainer, "my_google_map").commit();

    }
}
