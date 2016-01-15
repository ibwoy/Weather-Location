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
    private String jsonResult;

    private WeatherResultCallback callback;


    public ResponseParser()
    {

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

    public void setCallBack(WeatherResultCallback callback)
    {
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Connection... connection)
    {
        Connection conn = connection[0];
        conn.init();
        this.jsonResult = conn.getResult();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        this.callback.callBack(parse());
    }

    public interface WeatherResultCallback
    {
        public void callBack(WeatherResults wr);
    }
}

