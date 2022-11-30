package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownsImportDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;
    private Validator validator;
    private ModelMapper mapper;
    private Gson gson;
    private Map<String, Town> townsList = new LinkedHashMap<>();

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {

        this.townRepository = townRepository;

        this.gson = new Gson();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {

        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {

        Path path = Path.of("C:\\SoftUni\\SpringDB\\JavaDBSpringDataExam-02April2022\\skeleton\\src\\main\\resources\\files\\json\\towns.json");

        return Files.readString(path);
    }

    @Override
    public String importTowns() throws IOException {

        String json = readTownsFileContent();

        TownsImportDTO[] townsDTO = gson.fromJson(json, TownsImportDTO[].class);

        StringBuilder result = createResult(townsDTO);

        townRepository.saveAll(townsList.values());

        return result.toString();
    }

    private StringBuilder createResult(TownsImportDTO[] townsDTO) {

        StringBuilder sb = new StringBuilder();

        for (TownsImportDTO townDTO : townsDTO) {

            Set<ConstraintViolation<TownsImportDTO>> validate = validator.validate(townDTO);

            if (validate.isEmpty()) {

                if (townsList.containsKey(townDTO.getTownName())) {

                    sb.append("Invalid town");
                } else {

                    Town town = mapper.map(townDTO, Town.class);

                    townsList.put(town.getTownName(), town);

                    sb.append(String.format("Successfully imported town %s - %d", town.getTownName(), town.getPopulation()));
                }

            } else {

                sb.append("Invalid town");
            }

            sb.append(System.lineSeparator());
        }

        return sb;
    }
}
