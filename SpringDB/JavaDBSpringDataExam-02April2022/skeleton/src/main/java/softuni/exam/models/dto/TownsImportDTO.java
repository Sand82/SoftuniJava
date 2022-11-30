package softuni.exam.models.dto;

import javax.persistence.Column;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownsImportDTO {

    @Column(name = "town-name")
    @Size(min = 2)
    private String townName;

    @Positive
    private int population;

    public TownsImportDTO() {
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
