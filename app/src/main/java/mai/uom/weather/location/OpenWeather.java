package mai.uom.weather.location;

/**
 * Created by felix on 15/1/2016.
 */
public class OpenWeather
{
    private String rootUrl;  // Fixed url for openweather REST data fetching
    private Coordinates coor;  // Coordinates object reference
    private static String ApiKey = "11aa26bdff96af8c0b2c147801fe47a0"; // OpenWeather API key for persmission

    public OpenWeather(Coordinates coor)
    {
        this.coor = coor;
        rootUrl = "http://api.openweathermap.org/data/2.5/weather?";
    }

    @Override
    public String toString()
    {
        return this.rootUrl + "lat=" + this.coor.getLat() + "&lon=" + this.coor.getLon() + "&APPID=" + OpenWeather.ApiKey;
    }

}
