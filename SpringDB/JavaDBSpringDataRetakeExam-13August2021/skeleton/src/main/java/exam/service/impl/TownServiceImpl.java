package exam.service.impl;

import exam.model.dtos.TownImportDTO;
import exam.model.dtos.TownsImportsDTO;
import exam.model.entities.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private final String absolutePath = "C:\\SoftUni\\SpringDB\\JavaDBSpringDataRetakeExam-13August2021\\skeleton\\src\\main\\resources\\files\\xml\\towns.xml";
    private TownRepository townRepository;
    private JAXBContext context;
    private Unmarshaller unmarshaller;
    private ModelMapper mapper;
    private Validator validator;

    private Map<String, Town> townsList = new LinkedHashMap<>();

    @Autowired
    public TownServiceImpl(TownRepository townRepository) throws JAXBException {

        this.townRepository = townRepository;

        this.context = JAXBContext.newInstance(TownsImportsDTO.class);

        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {

        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {

        return Files.readString(Path.of(absolutePath));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {

        TownsImportsDTO townsImportsDTO = (TownsImportsDTO) unmarshaller.unmarshal(new FileReader(absolutePath));

        String result = townsImportsDTO.getTowns().stream().map(this::createOutput).collect(Collectors.joining(System.lineSeparator()));

        townRepository.saveAll(townsList.values());

        return result;
    }

    private String createOutput(TownImportDTO townDTO) {

        Set<ConstraintViolation<TownImportDTO>> validate = validator.validate(townDTO);

        if (validate.isEmpty()) {

            if (townsList.containsKey(townDTO.getName())) {

                return "Invalid town";
            } else {

                Town town = mapper.map(townDTO, Town.class);

                townsList.put(town.getName(), town);
            }

        } else {

            return "Invalid town";
        }

        return String.format("Successfully imported Town %s", townDTO.getName());
    }
}
