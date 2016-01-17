package mai.uom.weather.location;

/**
 * Created by felix on 17/1/2016.
 */
public class geoLocationURL extends OpenWeatherURL
{
    private Coordinates coor;

    public geoLocationURL(Coordinates coor)
    {
        super();
        this.coor = coor;
    }

    public String getURL()
    {
        return super.rootUrl + "lat=" + this.coor.getLat() + "&lon=" + this.coor.getLon() + "&APPID=" + super.ApiKey;
    }
}
