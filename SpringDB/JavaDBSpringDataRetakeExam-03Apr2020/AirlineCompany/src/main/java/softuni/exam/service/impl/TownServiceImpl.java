package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Town;
import softuni.exam.models.dto.TownImportDTO;
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
    private ModelMapper mapper;
    private Validator validator;
    private Gson gson;

    private Map<String, Town> townsList;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {

        this.townRepository = townRepository;

        this.mapper = new ModelMapper();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.gson = new Gson();

        this.townsList = new LinkedHashMap<>();
    }

    @Override
    public boolean areImported() {

        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {

        Path path = Path.of("AirlineCompany/src/main/resources/files/json/towns.json").toAbsolutePath();

        return Files.readString(path);
    }

    @Override
    public String importTowns() throws IOException {

        String json = readTownsFileContent();

        TownImportDTO[] townsDTO = gson.fromJson(json, TownImportDTO[].class);

        StringBuilder result = createTowns(townsDTO);

        townRepository.saveAll(townsList.values());

        return result.toString();
    }

    private StringBuilder createTowns(TownImportDTO[] townsDTO) {

        StringBuilder sb = new StringBuilder();

        for (TownImportDTO townDTO : townsDTO) {

            Set<ConstraintViolation<TownImportDTO>> validate = validator.validate(townDTO);

            if (validate.isEmpty()) {

                Town town = mapper.map(townDTO, Town.class);

                townsList.put(town.getName(), town);

                sb.append(String.format("Successfully imported Town %s - %d", town.getName(), town.getPopulation()));

            } else {

                sb.append("Invalid town.");
            }

            sb.append(System.lineSeparator());
        }

        return sb;
    }
}
