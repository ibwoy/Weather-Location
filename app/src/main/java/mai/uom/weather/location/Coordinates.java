package mai.uom.weather.location;

/**
 * Created by felix on 15/1/2016.
 */
public class Coordinates
{
    private double lat, lon;

    // Constructor taking Latitude and Longitude as parameters

    public Coordinates(double lat, double lon)
    {
        this.lat = lat;
        this.lon = lon;
    }

    // getter for Latitude
    public double getLat()
    {
        return this.lat;
    }

    // getter for Longitude
    public double getLon()
    {
        return this.lon;
    }


}
