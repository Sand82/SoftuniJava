package softuni.exam.models.dto;

import javax.persistence.Column;

public class CityForecastImportDTO {

    @Column(name = "city-name")
    private String cityName;

    @Column(name = "min-temperature")
    private float minTemperature;

    @Column(name = "max-temperature")
    private float maxTemperature;

    private String sunrise;

    private String sunset;

    public CityForecastImportDTO() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
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
}
