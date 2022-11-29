package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class CityServiceImpl implements CityService {

    private CountryRepository countryRepository;

    @Autowired
    public CityServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {

        return countryRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {

        Path path = Path.of("");
        return null;
    }
    @Override
    public String importCities() throws IOException {
        return null;
    }
}
