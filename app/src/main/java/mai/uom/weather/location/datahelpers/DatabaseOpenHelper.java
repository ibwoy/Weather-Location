package mai.uom.weather.location.datahelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;


/**
 * Created by io on 1/18/16.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String db = "weather.db";

    public DatabaseOpenHelper(Context context) {
        super(context, db, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL("CREATE TABLE LOCATIONS(ID integer primary key autoincrement, COUNTRY text, CITY text,"
                    + "LAT numeric, LON numeric)");
        }
        catch (SQLiteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
