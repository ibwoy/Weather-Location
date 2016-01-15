package mai.uom.weather.location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by felix on 15/1/2016.
 */
public class ResponseParser
{
    private String jsonResult;



    public ResponseParser(Connection connection)
    {
        connection.init();
        this.jsonResult = connection.getResult();
    }

    public WeatherResults parse()
    {
        WeatherResults wr = new WeatherResults();
        try {
            JSONObject json = new JSONObject(this.jsonResult);
            wr.setLat(json.getJSONObject("coord").getDouble("lat"));
            wr.setLon(json.getJSONObject("coord").getDouble("lon"));

            JSONArray jsonar = json.getJSONArray("weather");
            wr.setWeathermain(jsonar.getJSONObject(0).getString("main"));
            wr.setWeatherdescription(jsonar.getJSONObject(0).getString("description"));


            wr.setCounty(json.getJSONObject("sys").getString("country"));

            wr.setCity(json.getString("name"));

            wr.setTemp(json.getJSONObject("main").getDouble("temp"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return wr;

    }




}
