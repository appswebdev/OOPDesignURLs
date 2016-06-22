package college.edu.tomer.oopdesignurls;

import java.util.Date;

/**
 * Created by master on 22/06/16.
 */
public class Weather {
    private String city;
    private String temp;
    private String description;
    private Date sunset;
    private Date sunrise;

    public Weather(String city, String temp, String description, Date sunset, Date sunrise) {
        this.city = city;
        this.temp = temp;
        this.description = description;
        this.sunset = sunset;
        this.sunrise = sunrise;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

}
