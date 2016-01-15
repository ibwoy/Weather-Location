package mai.uom.weather.location;

/**
 * Created by felix on 15/1/2016.
 */
public class WeatherResults
{
    private String city, country, weathermain, weatherdescription;
    private double lat, lon, temp;

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
    public double getTemp() {return this.temp;}

    public String toString()
    {
        return "LAT : " + getLat() + "\n" + "LON : " + getLon() + "\n" + "TEMP : " + getTemp() + " C\n" + "WEATHER : " + getWeathermain()
                + "\n" + "WEATHER DESCRIPTION : " + getWeatherdescription() + "\n" + "CITY : " + getCity() + "\n" + "COUNTRY CODE : " + getCountry();
    }


}
