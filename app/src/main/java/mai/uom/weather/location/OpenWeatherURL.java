package mai.uom.weather.location;

/**
 * Created by felix on 15/1/2016.
 */
public abstract class OpenWeatherURL
{
    private String rootUrl;  // Fixed url for openweather REST data fetching
    private static String ApiKey = "11aa26bdff96af8c0b2c147801fe47a0"; // OpenWeather API key for persmission

    public OpenWeatherURL()
    {
        rootUrl = "http://api.openweathermap.org/data/2.5/weather?";
    }

    public abstract String getURL(); // Method to be implemented by subclasses for returning the HTTP request URL

    public String getRootUrl() {
        return this.rootUrl;
    }

    public static String getApiKey() {
        return ApiKey;
    }

}
