package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

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
public class CountryServiceImpl implements CountryService {

    private final Gson gson;
    private CountryRepository countryRepository;
    private Validator validator;
    private ModelMapper mapper;

    private Map< String, Country> countriesList = new LinkedHashMap<>();

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {

        this.countryRepository = countryRepository;

        this.gson = new GsonBuilder().create();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {

        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {

        Path path = Path.of("C:\\SoftUni\\SpringDB\\JavaDBSpringDataRetakeExam-17April2022\\skeleton\\src\\main\\resources\\files\\json\\countries.json");

        return Files.readString(path);
    }

    @Override
    public String importCountries() throws IOException {

        String json = readCountriesFromFile();

        CountryImportDTO[] countriesDTO = gson.fromJson(json, CountryImportDTO[].class);

        StringBuilder result = validateInput(countriesDTO);

        countryRepository.saveAll(countriesList.values());

        return result.toString().trim();
    }

    private StringBuilder validateInput(CountryImportDTO[] countriesDTO) {

        StringBuilder sb = new StringBuilder();

        for (CountryImportDTO countryDTO : countriesDTO) {

            Set<ConstraintViolation<CountryImportDTO>> validate = validator.validate(countryDTO);

            if (validate.isEmpty()) {

                Country country = mapper.map(countryDTO, Country.class);

                if (countriesList.containsKey(country.getCountryName())) {

                    sb.append("Invalid country");
                } else {

                    countriesList.put(country.getCountryName(), country);
                    sb.append(String.format("Successfully imported country %s - %s",
                            country.getCountryName(), country.getCurrency()));
                }

            } else {

                sb.append("Invalid country");
            }

            sb.append(System.lineSeparator());
        }

        return sb;
    }
}
