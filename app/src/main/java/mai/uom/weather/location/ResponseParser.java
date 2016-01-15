package mai.uom.weather.location;

import org.json.JSONObject;
/**
 * Created by felix on 15/1/2016.
 */
public class ResponseParser
{
    private String jsonResult;
    public ResponseParser(Connection connection, OpenWeather openWeather)
    {
        connection = new Connection(openWeather);
        connection.init();
        this.jsonResult = connection.getResult();
    }





}
