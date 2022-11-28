package com.example.football.service.impl;

import com.example.football.models.dto.TownImportDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
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
public class TownServiceImpl implements TownService {

    private final Validator validation;
    private TownRepository townRepository;

    private final Gson gson;

    private ModelMapper mapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;

        this.gson = new GsonBuilder().create();
        this.mapper = new ModelMapper();

        this.validation = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {

       return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {

        Path path = Path.of("src", "main", "resources", "files", "json", "towns.json");

        return Files.readString(path);
    }

    @Override
    public String importTowns() throws IOException {

        String json = readTownsFileContent();
        TownImportDTO [] townsDTO = gson.fromJson(json, TownImportDTO[].class);

        StringBuilder sb = new StringBuilder();
        List<Town> towns = new ArrayList<>();

        for (TownImportDTO townDTO : townsDTO) {

            Set<ConstraintViolation<TownImportDTO>> validate = this.validation.validate(townDTO);

            if (validate.isEmpty()) {

                Town town =  mapper.map(townDTO, Town.class);
                towns.add(town);

                sb.append(String.format("Successfully imported Town %s - %s", town.getName(), town.getPopulation()));
            } else {

                sb.append("Invalid Town");
            }

            sb.append(System.lineSeparator());
        }

        townRepository.saveAll(towns);

        return sb.toString().trim();
    }
}
