package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentImportDTO;
import softuni.exam.models.dto.ApartmentsImportDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private String absolutPath = "C:\\SoftUni\\SpringDB\\JavaDBSpringDataExam-02April2022\\skeleton\\src\\main\\resources\\files\\xml\\apartments.xml";
    private ApartmentRepository apartmentRepository;
    private TownRepository townRepository;
    private ModelMapper mapper;
    private JAXBContext context;
    private Unmarshaller unmarshaller;
    private List<Apartment> apartmentsList = new ArrayList<>();

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository) throws JAXBException {

        this.apartmentRepository = apartmentRepository;

        this.townRepository = townRepository;

        this.mapper = new ModelMapper();

        this.context = JAXBContext.newInstance(ApartmentsImportDTO.class);

        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {

        return apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {

        Path path = Path.of(absolutPath);

        return Files.readString(path);
    }

    @Override
    public String importApartments() throws IOException, JAXBException {

        ApartmentsImportDTO apartmentsDTO = (ApartmentsImportDTO) unmarshaller.unmarshal(new FileReader(absolutPath));

        String result = apartmentsDTO.getApartments().stream().map(this::createApartment).collect(Collectors.joining(System.lineSeparator()));

        apartmentRepository.saveAll(apartmentsList);

        return result;
    }

    private String createApartment(ApartmentImportDTO apartmentDTO) {

        if (apartmentDTO.validator().size() != 0) {

            return "Invalid apartment";
        }

        Apartment apartmentToCheck = null;

        if (apartmentsList.size() > 0) {

            apartmentToCheck = apartmentsList.stream()
                    .filter(a -> a.getTown().equals(apartmentDTO.getTown()) && a.getArea() - apartmentDTO.getArea() == 0 )
                    .findFirst().orElse(null);
        }

        if (apartmentToCheck != null) {

            return "Invalid apartment";
        }

        Town town = townRepository.findByTownName(apartmentDTO.getTown());

        Apartment apartment = mapper.map(apartmentDTO, Apartment.class);

        apartment.setTown(town);

        apartmentsList.add(apartment);

        return String.format("Successfully imported apartment %s - %.2f", apartment.getApartmentType(), apartment.getArea());
    }
}
