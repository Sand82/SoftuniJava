package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityForecastImportDTO;
import softuni.exam.models.dto.ForecastImportDTO;
import softuni.exam.models.dto.ForecastsImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final String absolutePath = "C:\\SoftUni\\SpringDB\\JavaDBSpringDataRetakeExam-17April2022\\skeleton\\src\\main\\resources\\files\\xml\\forecasts.xml";
    private ForecastRepository forecastRepository;
    private CityRepository cityRepository;
    private Validator validator;
    private ModelMapper mapper;
    private JAXBContext context;
    private Unmarshaller unmarshaller;
    private List<Forecast> forecastsList = new ArrayList<>();

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository) throws JAXBException {

        this.forecastRepository = forecastRepository;

        this.cityRepository = cityRepository;

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.mapper = new ModelMapper();

        this.context = JAXBContext.newInstance(ForecastsImportDTO.class);

        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {

        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {

        Path path = Path.of(absolutePath);

        return Files.readString(path);
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {

        ForecastsImportDTO forecastsDTO = (ForecastsImportDTO) unmarshaller.unmarshal(new FileReader(absolutePath));

        String result = forecastsDTO.getForecasts().stream().map(this::validateInput).collect(Collectors.joining(System.lineSeparator()));

        forecastRepository.saveAll(forecastsList);

        return result;
    }

    private String validateInput(ForecastImportDTO forecastDTO) {

        if (forecastDTO.inputValidator().size() > 0) {

            return "Invalid forecast";
        }

        int cityId = forecastDTO.getCityId();

        City city = cityRepository.findById(cityId).get();

        Forecast forecastIdentityCheck = forecastsList.stream()
                .filter(f -> f.getDayOfWeek().equals(forecastDTO.getDayOfWeek()) && f.getCity().getCityName().equals(city.getCityName()))
                .findFirst().orElse(null);

        if (forecastIdentityCheck != null) {

            return "Invalid forecast";
        }

        Forecast forecast = mapper.map(forecastDTO, Forecast.class);

        forecast.setCity(city);

        forecastsList.add(forecast);

        return String.format("Successfully import forecast %s - %.2f", forecast.getDayOfWeek(), forecast.getMaxTemperature());
    }

    @Override
    public String exportForecasts() {

        DayOfWeek dayOfWeek = DayOfWeek.valueOf("SUNDAY");

        List<Forecast> forecasts = forecastRepository.findByDayOfWeekAndCityPopulationLessThanOrderByMaxTemperatureDescIdAsc(dayOfWeek, 150000);

        List<CityForecastImportDTO> models = forecasts.stream().map(f -> mapper.map(f, CityForecastImportDTO.class)).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (CityForecastImportDTO model : models) {

            sb.append(String.format("   City: %s:", model.getCityName()));
            sb.append(System.lineSeparator());
            sb.append(String.format("        -min temperature: %.2f", model.getMinTemperature()));
            sb.append(System.lineSeparator());
            sb.append(String.format("        --max temperature: %.2f", model.getMaxTemperature()));
            sb.append(System.lineSeparator());
            sb.append(String.format(String.format("        ---sunrise: %s", model.getSunrise())));
            sb.append(System.lineSeparator());
            sb.append(String.format(String.format("        ---sunset: %s", model.getSunset())));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
