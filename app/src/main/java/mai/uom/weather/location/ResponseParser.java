package mai.uom.weather.location;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by felix on 15/1/2016.
 */
public class ResponseParser extends AsyncTask<Connection, Void, Void>
{
    private String jsonResult; // The string-represented JSON object

    private WeatherResultCallback callback; // A reference for a functionality to be called after a certain event is finished


    public ResponseParser()
    {

    }

    // Parser for the JSON results fetched by http request. After parsing a WeatherResults object is created and returned
    public WeatherResults parse()
    {
        WeatherResults wr = new WeatherResults();
        try {
            JSONObject json = new JSONObject(this.jsonResult);
            wr.setLat(json.getJSONObject("coord").getDouble("lat"));
            wr.setLon(json.getJSONObject("coord").getDouble("lon"));

            JSONArray jsonar = json.getJSONArray("weather");
            wr.setWeatherMain(jsonar.getJSONObject(0).getString("main"));
            wr.setWeatherDesc(jsonar.getJSONObject(0).getString("description"));
            wr.setWeatherIcon(jsonar.getJSONObject(0).getString("icon"));

            wr.setCounty(json.getJSONObject("sys").getString("country"));

            wr.setCity(json.getString("name"));

            wr.setTemp(json.getJSONObject("main").getDouble("temp"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return wr;

    }

    public void setCallBack(WeatherResultCallback callback)
    {
        this.callback = callback;
    }

    @Override // Initiating the connection in a separate thread for avoiding system crash due to delays in main thread
    protected Void doInBackground(Connection... connection)
    {
        Connection conn = connection[0];
        conn.init();
        this.jsonResult = conn.getResult();
        return null;
    }

    @Override // After the response, the callback functionality is called
    protected void onPostExecute(Void aVoid) {
        this.callback.callBack(parse());
    }

    // WeatherResultCallback interface for declaring a callback function
    public interface WeatherResultCallback
    {
        public void callBack(WeatherResults wr);
    }
}

