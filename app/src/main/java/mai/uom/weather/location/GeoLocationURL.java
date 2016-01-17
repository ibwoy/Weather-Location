package mai.uom.weather.location;

/**
 * Created by felix on 17/1/2016.
 */
public class GeoLocationURL extends OpenWeatherURL
{
    private Coordinates coor;

    public GeoLocationURL(Coordinates coor)
    {
        super();
        this.coor = coor;
    }

    public String getURL()
    {
        return super.rootUrl + "lat=" + this.coor.getLat() + "&lon=" + this.coor.getLon() + "&APPID=" + super.ApiKey;
    }
}
