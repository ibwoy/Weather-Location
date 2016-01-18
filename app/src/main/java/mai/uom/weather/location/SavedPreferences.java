package mai.uom.weather.location;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Save and Restore applications preferences
 */
public class SavedPreferences {
    /**
     * The state if is the first time that app launches
     */
    public final static String FIRST_TIME_STATE = "first_time_state";
    /**
     * Preferences name
     */
    private String pref_name = "shared_pref";
    /**
     * Application context
     */
    private Context context;

    /**
     * Constructor
     * @param context Context
     */
    public SavedPreferences(Context context) {

        this.context = context;
    }

    /**
     * Return the saved boolean value of the given key if exists.
     * Else return false
     * @param key Value key
     * @return True or False
     */
    public boolean getBoolean(String key,boolean defaultValue) {
        return context.getSharedPreferences(pref_name,Context.MODE_PRIVATE).getBoolean(key,defaultValue);
    }
    /**
     * Return the saved string value of the given key if exists.
     * Else return null
     * @param key Value key
     * @return Saved value or null
     */
    public String getString(String key,String defaultValue) {
        return context.getSharedPreferences(pref_name,Context.MODE_PRIVATE).getString(key, defaultValue);
    }
    /**
     * Return the saved integer value of the given key if exists.
     * Else return 0
     * @param key Value key
     * @return Saved value or 0
     */
    public int getInt(String key,int defaultValue) {
        return context.getSharedPreferences(pref_name,Context.MODE_PRIVATE).getInt(key, defaultValue);
    }
    public float getFloat(String key,float defaultValue) {
        return context.getSharedPreferences(pref_name,Context.MODE_PRIVATE).getFloat(key,defaultValue);
    }
    /**
     * Save the value of the given key
     * @param key Value key
     * @param value Boolean value
     */
    public void put(String key,boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(pref_name,Context.MODE_PRIVATE).edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    /**
     * Save the value of the given key
     * @param key Value key
     * @param value String value
     */
    public void put(String key,String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(pref_name,Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
    /**
     * Save the value of the given key
     * @param key Value key
     * @param value Int value
     */
    public void put(String key,int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(pref_name,Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Save the value fo the given key
     * @param key Value key
     * @param value float value
     */
    public void put(String key,float value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(pref_name,Context.MODE_PRIVATE).edit();
        editor.putFloat(key,value);
        editor.apply();
    }
}
