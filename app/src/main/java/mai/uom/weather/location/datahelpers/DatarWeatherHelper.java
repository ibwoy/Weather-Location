package mai.uom.weather.location.datahelpers;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import mai.uom.weather.location.WeatherResults;

/**
 * Created by io on 1/18/16.
 */
public class DatarWeatherHelper {

    private Context context;

    public DatarWeatherHelper(Context context) {
        this.context = context;
    }

    /**
     * Crate a new Location
     * @param  w
     */
    public void create(WeatherResults w) {
        try {
            SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
            String query =
                        "INSERT INTO LOCATION(id, country, city, lat, lon)"
                            + " VALUES(" + w.getId()+ ",'" + w.getCountry() + ",'" + w.getCity()
                            + ",'" + w.getLat() + ",'" + w.getLon() + ",'"
                            + "')";
            db.execSQL(query);
            db.close();
        }
        catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a location based on id
     * @param id
     */
    public void delete(int id) {
        try {
            SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
            String query =
                        "DELETE FROM LOCATION WHERE id="+id;
            db.execSQL(query);
            db.close();
        }
        catch(SQLiteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read all location's from database
     * @return results
     */
    public ArrayList<WeatherResults> findAll() {
        ArrayList<WeatherResults> results = new ArrayList<>();

        try {
            SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
            String query = "SELECT * FROM LOCATION";

            Cursor c = db.rawQuery(query, null);

            if(c.moveToFirst()) {
                do {
                    WeatherResults w = new WeatherResults();
                    w.setId(c.getInt(0));
                    w.setCounty(c.getString(1));
                    w.setCity(c.getString(2));
                    w.setLat(c.getDouble(3));
                    w.setLon(c.getDouble(4));
                }
                while(c.moveToNext());
            }
            c.close();
            db.close();
        }
        catch (SQLiteException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Read one location from database
     * @param id
     * @return weather location
     */
    public WeatherResults findOne(int id) {
        WeatherResults w = new WeatherResults();
        try {
            SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
            String query = "SELECT * FROM LOCATION where id="+id;
            Cursor c = db.rawQuery(query, null);

            if(c.moveToFirst()) {
                w.setId(c.getInt(0));
                w.setCounty(c.getString(1));
                w.setCity(c.getString(2));
                w.setLat(c.getDouble(3));
                w.setLon(c.getDouble(4));
            }
            c.close();
            db.close();
        }
        catch (SQLiteException e) {
            e.printStackTrace();
        }
        return w;
    }



}

