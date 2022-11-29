package com.example.football.service.impl;

import com.example.football.models.dto.StatImportDTO;
import com.example.football.models.dto.StatsImportDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    private final String path = "C:\\SoftUni\\SpringDB\\JavaDBSpringDataExam-01August2021\\skeleton\\skeleton\\src\\main\\resources\\files\\xml\\stats.xml";

    private ModelMapper mapper;
    private final JAXBContext context;
    private StatRepository statRepository;
    private final Unmarshaller unmarshaller;
    private Validator validator;

    private List<Stat> statsList = new ArrayList<>();

    @Autowired
    public StatServiceImpl(StatRepository statRepository) throws JAXBException {
        this.statRepository = statRepository;

        this.mapper = new ModelMapper();

        this.context = JAXBContext.newInstance(StatsImportDTO.class);

        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {

        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {



        return Files.readString(Path.of(path));
    }

    @Override
    public String importStats() throws JAXBException, IOException {

        StatsImportDTO statsDTO = (StatsImportDTO) unmarshaller.unmarshal(new FileReader(path));

        String result = statsDTO.getStats().stream().map(this::importStats).collect(Collectors.joining(System.lineSeparator()));

        statRepository.saveAll(statsList);

        return result;
    }

    private String importStats(StatImportDTO statDTO) {

        Set<ConstraintViolation<StatImportDTO>> validate =
                validator.validate(statDTO);

        if (validate.size() > 0) {

            return "Invalid Stat";
        }

        Optional<Stat> opStats = statRepository.findByShootingAndPassingAndEndurance(
                statDTO.getShooting(), statDTO.getPassing(), statDTO.getEndurance());

        if (opStats.isPresent()) {

            return "Invalid Stat";
        }

        Stat stat = mapper.map(statDTO, Stat.class);

        statsList.add(stat);

        return String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                stat.getPassing(), stat.getShooting(), stat.getEndurance());
    }
}
