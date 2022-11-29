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
}
