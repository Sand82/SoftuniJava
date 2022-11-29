package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "forecasts")
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "max_temperature", nullable = false)
    private float maxTemperature;

    @Column(name = "min_temperature", nullable = false)
    private float minTemperature;

    @Column(nullable = false)
    private LocalDate sunrise;

    @Column(nullable = false)
    private LocalDate sunset;

    @ManyToOne
    private City city;

    public Forecast() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
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

    public LocalDate getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalDate sunrise) {
        this.sunrise = sunrise;
    }

    public LocalDate getSunset() {
        return sunset;
    }

    public void setSunset(LocalDate sunset) {
        this.sunset = sunset;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
