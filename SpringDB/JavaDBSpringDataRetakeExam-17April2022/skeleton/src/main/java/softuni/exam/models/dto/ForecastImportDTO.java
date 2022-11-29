package softuni.exam.models.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastImportDTO {

    @XmlElement(name = "day_of_week")
    private String dayOfWeek;

    @XmlElement(name = "max_temperature")
    private float maxTemperature;

    @XmlElement(name = "min_temperature")
    private float minTemperature;

    @XmlElement
    private String sunrise;

    @XmlElement
    private String sunset;

    @XmlElement(name = "city")
    private int cityId;

    public ForecastImportDTO() {
    }

    public ForecastImportDTO(String dayOfWeek, float maxTemperature, float minTemperature, String sunrise, String sunset, int city) {
        this.dayOfWeek = dayOfWeek;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.cityId = city;

        inputValidator();
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public List<String> inputValidator() {

        List<String> errors = new ArrayList<>();

        if (!dayOfWeek.equals("FRIDAY") && !dayOfWeek.equals("SATURDAY") && !dayOfWeek.equals("SUNDAY")) {
            errors.add("Invalid day of week.");
        }

        if (maxTemperature < -20.0 || maxTemperature > 60.0) {
            errors.add("Temperature should be between - 20 and 60 degrees.");
        }

        if (minTemperature < -50.0 || minTemperature > 40.0) {
            errors.add("Temperature should be between - 50 and 40 degrees.");
        }

        return errors;
    }
}
