package mai.uom.weather.location;

import java.util.Calendar;

/**
 * Created by felix on 15/1/2016.
 */
public class WeatherResults
{
    /* WeatherResults Objects attributes

    *   city : The name of the City corresponding to specific coordinates
    *   country : The country code
    *   weathermain : A single or double word describing the weather conditions
    *   weatherdescription : Like weathemain but more elaborate phrase
    *   lat : The latitude of the location
    *   lon : The Longitude of the location
    *   temp : The temperature in Celsius degrees
    *   weathericon : A code associated with an image
    * */
    private String city, country, weathermain, weatherdescription, weathericon;
    private double lat, lon, temp;
    private long time;

    public WeatherResults()
    {
        this.time = Calendar.getInstance().getTimeInMillis();
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setCounty(String country)
    {
        this.country = country;
    }

    public void setWeathermain(String weathermain)
    {
        this.weathermain = weathermain;
    }

    public void setWeatherdescription(String weatherdescription)
    {
        this.weatherdescription = weatherdescription;
    }

    public void setWeathericon(String weathericon)
    {
        this.weathericon = weathericon;
    }

    public void setTemp(double temp)
    {
        this.temp = temp - 273.15;
    }

    public double getLat() {return this.lat;}
    public double getLon() {return this.lon;}
    public String getCity() {return this.city;}
    public String getCountry() {return this.country;}
    public String getWeathermain() {return this.weathermain;}
    public String getWeatherdescription() {return this.weatherdescription;}
    public String getWeathericon() {return this.weathericon;}
    public double getTemp() {return this.temp;}
    public long getTime() {return this.time;}

    public String toString()
    {
        return "LAT : " + getLat() + "\n" + "LON : " + getLon() + "\n" + "TEMP : " + getTemp() + " C\n" + "WEATHER : " + getWeathermain()
                + "\n" + "WEATHER DESCRIPTION : " + getWeatherdescription() + "\n" + "CITY : " + getCity() + "\n" + "COUNTRY CODE : " + getCountry()
                + "\n" + "ICON CODE : " + getWeathericon();
    }



}
