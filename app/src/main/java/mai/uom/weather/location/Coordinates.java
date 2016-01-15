package mai.uom.weather.location;

/**
 * Created by felix on 15/1/2016.
 */
public class Coordinates
{
    private String lat, lon;

    public Coordinates(String lat, String lon)
    {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat()
    {
        return this.lat;
    }

    public String getLot()
    {
        return this.lon;
    }


}
