package mai.uom.weather.location;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

/**
 * Created by felix on 17/1/2016.
 */
public class Splash extends Activity
{
    private final int SPLASH_SCREE_DISPLAY_TIME = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spash_layout);
        new Handler().postDelayed(new Runnable(){
            public void run()
            {
                Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }

        }, SPLASH_SCREE_DISPLAY_TIME);


    }
}