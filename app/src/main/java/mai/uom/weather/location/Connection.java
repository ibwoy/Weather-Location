package mai.uom.weather.location;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
/**
 * Created by felix on 15/1/2016.
 */
public class Connection
{
    private URL url;            // URL reference
    private BufferedReader br;  // BufferedReader for input streams
    private String result;      // The response of http request

    public Connection(OpenWeatherURL ow)
    {
        try
        {
            url = new URL(ow.getURL());
        }
        catch (MalformedURLException me)
        {
            me.printStackTrace();
        }
        result = "";
    }

    // Initating the procedure of data fetching through HTTP GET requests
    public void init()
    {
        try
        {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder temp = new StringBuilder();
            while ((result = br.readLine()) != null)
                temp.append(result);
            result = temp.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    // Returns the image of the JSON object
    public String getResult()
    {
        return this.result;
    }




}
