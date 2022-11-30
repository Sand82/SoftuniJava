package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferInputDTO;
import softuni.exam.models.dto.OffersInputDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private String absolutPath = "C:\\SoftUni\\SpringDB\\JavaDBSpringDataExam-02April2022\\skeleton\\src\\main\\resources\\files\\xml\\offers.xml";
    private OfferRepository offerRepository;
    private AgentRepository agentRepository;
    private ApartmentRepository apartmentRepository;
    private ModelMapper mapper;
    private JAXBContext context;
    private Unmarshaller unmarshaller;
    private Validator validator;

    private List<Offer> offersList = new ArrayList<>();

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, AgentRepository agentRepository, ApartmentRepository apartmentRepository) throws JAXBException {

        this.offerRepository = offerRepository;

        this.agentRepository = agentRepository;

        this.apartmentRepository = apartmentRepository;

        this.mapper = new ModelMapper();

        this.context = JAXBContext.newInstance(OffersInputDTO.class);

        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {

        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {


        return Files.readString(Path.of(absolutPath));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {

        OffersInputDTO offersDTO = (OffersInputDTO) unmarshaller.unmarshal(new FileReader(absolutPath));

        String result = offersDTO.getOffers().stream().map(this::createOffer).collect(Collectors.joining(System.lineSeparator()));

        offerRepository.saveAll(offersList);

        return result;
    }

    private String createOffer(OfferInputDTO offerDTO) {
        Set<ConstraintViolation<OfferInputDTO>> validate = validator.validate(offerDTO);

        if (validate.isEmpty()) {

            String name = offerDTO.getAgent().getName();

            Agent agent = agentRepository.findByFirstName(name);

            if (agent == null) {

                return "Invalid offer";
            }

            String date = offerDTO.getPublishedOn();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate publishedOn = LocalDate.parse(date, formatter);

            Apartment apartment = apartmentRepository.getById(offerDTO.getApartment().getId());

            Offer offer = mapper.map(offerDTO, Offer.class);

            offer.setAgent(agent);
            offer.setApartment(apartment);
            offer.setPublishedOn(publishedOn);

            offersList.add(offer);

        } else {

            return "Invalid offer";
        }

        return String.format("Successfully imported offer %.2f", offerDTO.getPrice());
    }

    @Override
    public String exportOffers() {
        return null;
    }
}
