package com.example.football.models.dto;

import javax.persistence.Column;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatsImportDTO {

    @XmlElement(name = "stat")
   private List<StatImportDTO> stats;

    public StatsImportDTO() {
    }

    public List<StatImportDTO> getStats() {
        return stats;
    }

    public void setStats(List<StatImportDTO> stats) {
        this.stats = stats;
    }
}
