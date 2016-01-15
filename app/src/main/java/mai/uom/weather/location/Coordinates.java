package mai.uom.weather.location;

/**
 * Created by felix on 15/1/2016.
 */
public class Coordinates
{
    private String lat, lot;

    public Coordinates(String lat, String lot)
    {
        this.lat = lat;
        this.lot = lot;
    }

    public String getLat()
    {
        return this.lat;
    }

    public String getLot()
    {
        return this.lot;
    }


}
