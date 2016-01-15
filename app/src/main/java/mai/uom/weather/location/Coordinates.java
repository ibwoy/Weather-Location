package mai.uom.weather.location;

/**
 * Created by felix on 15/1/2016.
 */
public class Coordinates
{
    private double lat, lon;

    public Coordinates(double lat, double lon)
    {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat()
    {
        return this.lat;
    }

    public double getLon()
    {
        return this.lon;
    }


}
