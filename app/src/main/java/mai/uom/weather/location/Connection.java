package mai.uom.weather.location;

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
    private URL url;
    private BufferedReader br;
    private String result;

    public Connection(OpenWeather ow)
    {
        try
        {
            url = new URL(ow.toString());
        }
        catch (MalformedURLException me)
        {
            me.printStackTrace();
        }
        result = "";
    }

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

    public String getResult()
    {
        return this.result;
    }




}
