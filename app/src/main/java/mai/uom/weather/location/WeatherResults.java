package mai.uom.weather.location;

/**
 * Created by felix on 15/1/2016.
 */
public class WeatherResults
{
    /* WeatherResults Objects attributes

    *   city : The name of the City corresponding to specific coordinates
    *   country : The country code
    *   weatherMain : A single or double word describing the weather conditions
    *   weatherDesc : Like weathemain but more elaborate phrase
    *   lat : The latitude of the location
    *   lon : The Longitude of the location
    *   temp : The temperature in Celsius degrees
    *   weathericon : A code associated with an image
    *   id : identification number for database manipulations
    *
    */
    private String city, country, weatherMain, weatherDesc, weatherIcon;
    private double lat, lon, temp;
    private int id;

    public WeatherResults()
    {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setWeatherMain(String weatherMain)
    {
        this.weatherMain = weatherMain;
    }

    public void setWeatherDesc(String weatherDesc)
    {
        this.weatherDesc = weatherDesc;
    }

    public void setWeatherIcon(String weatherIcon)
    {
        this.weatherIcon = weatherIcon;
    }

    public void setTemp(double temp)
    {
        this.temp = temp - 273.15;
    }



    public double getLat() {return this.lat;}
    public double getLon() {return this.lon;}
    public String getCity() {return this.city;}
    public String getCountry() {return this.country;}
    public String getWeatherMain() {return this.weatherMain;}
    public String getWeatherDesc() {return this.weatherDesc;}
    public String getWeatherIcon() {return this.weatherIcon;}
    public double getTemp() {return this.temp;}


    public String toString()
    {
        return "LAT : " + getLat() + "\n" + "LON : " + getLon() + "\n" + "TEMP : " + getTemp() + " C\n" + "WEATHER : " + getWeatherMain()
                + "\n" + "WEATHER DESCRIPTION : " + getWeatherDesc() + "\n" + "CITY : " + getCity() + "\n" + "COUNTRY CODE : " + getCountry()
                + "\n" + "ICON CODE : " + getWeatherIcon();
    }



}
