package com.example.football.service.impl;

import com.example.football.models.dto.TeamImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private TownRepository townRepository;
    private final Gson gson;
    private ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;

        this.gson = new GsonBuilder().create();

        this.mapper = new ModelMapper();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {

        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {

        Path path = Path.of("C:\\SoftUni\\SpringDB\\JavaDBSpringDataExam-01August2021\\skeleton\\skeleton\\src\\main\\resources\\files\\json\\teams.json");

        return Files.readString(path);
    }

    @Override
    public String importTeams() throws IOException {

        String json = readTeamsFileContent();

        TeamImportDTO[] teamImportDTO = gson.fromJson(json, TeamImportDTO[].class);

        List<Team> teams = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (TeamImportDTO importDTO : teamImportDTO) {

            Set<ConstraintViolation<TeamImportDTO>> validate = validator.validate(importDTO);

            if (validate.isEmpty()) {

                Town town = townRepository.findByName(importDTO.getTownName());

                Team team = mapper.map(importDTO, Team.class);
                team.setTown(town);

                teams.add(team);
                sb.append(String.format("Successfully imported Team %s - %d", team.getName(), team.getFanBase()));

            } else {

                sb.append("Invalid Team");
            }

            sb.append(System.lineSeparator());
        }

        teamRepository.saveAll(teams);

        return sb.toString().trim();
    }
}
