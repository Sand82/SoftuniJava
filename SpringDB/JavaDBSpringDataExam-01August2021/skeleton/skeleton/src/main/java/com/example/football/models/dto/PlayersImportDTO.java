package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersImportDTO {

    @XmlElement(name = "player")
    List<PlayerImportDTO> players;

    public PlayersImportDTO() {
    }

    public List<PlayerImportDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerImportDTO> players) {
        this.players = players;
    }
}
