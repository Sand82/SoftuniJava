package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;

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
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;
    private CountryRepository countryRepository;
    private Validator validator;
    private ModelMapper mapper;
    private Gson gson;
    private Map<String, City> citiesList = new LinkedHashMap<>();

    @Autowired

    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;

        this.countryRepository = countryRepository;

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.mapper = new ModelMapper();

        this.gson = new Gson();
    }

    @Override
    public boolean areImported() {

        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {

        Path path = Path.of("C:\\SoftUni\\SpringDB\\JavaDBSpringDataRetakeExam-17April2022\\skeleton\\src\\main\\resources\\files\\json\\cities.json");

        return Files.readString(path);
    }

    @Override
    public String importCities() throws IOException {

        String json = readCitiesFileContent();

        CityImportDTO[] citiesDTOS = gson.fromJson(json, CityImportDTO[].class);

        StringBuilder result = validateInput(citiesDTOS);

        cityRepository.saveAll(citiesList.values());

        return result.toString().trim();
    }

    private StringBuilder validateInput(CityImportDTO[] citiesDTOS) {

        StringBuilder sb = new StringBuilder();

        for (CityImportDTO citiesDTO : citiesDTOS) {

            Set<ConstraintViolation<CityImportDTO>> validate = validator.validate(citiesDTO);

            if (validate.isEmpty()) {

                City city = mapper.map(citiesDTO, City.class);

                if (citiesList.containsKey(city.getCityName())) {

                    sb.append("Invalid city");
                } else {

                    Country country = countryRepository.findById(citiesDTO.getCountry()).get();

                    city.setCountry(country);

                    citiesList.put(city.getCityName(), city);

                    sb.append(String.format("Successfully imported city %s - %d", city.getCityName(), city.getPopulation()));
                }
            } else {

                sb.append("Invalid city");
            }

            sb.append(System.lineSeparator());
        }

        return sb;
    }
}
