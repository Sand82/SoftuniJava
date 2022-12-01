package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dtos.CustomerImportDTO;
import exam.model.entities.Customer;
import exam.model.entities.Town;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private TownRepository townRepository;
    private ModelMapper mapper;
    private Gson gson;
    private Map<String, Customer> customersList = new LinkedHashMap<>();
    private Validator validator;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository) {

        this.customerRepository = customerRepository;

        this.townRepository = townRepository;

        this.mapper = new ModelMapper();

        this.gson = new Gson();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {

        return customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {

        Path path = Path.of("skeleton/src/main/resources/files/json/customers.json").toAbsolutePath();

        return Files.readString(path);
    }

    @Override
    public String importCustomers() throws IOException {

        String json = readCustomersFileContent();

        CustomerImportDTO[] customersDTO = gson.fromJson(json, CustomerImportDTO[].class);

        StringBuilder result = createCustomer(customersDTO);

        customerRepository.saveAll(customersList.values());

        return result.toString();
    }

    private StringBuilder createCustomer(CustomerImportDTO[] customersDTO) {

        StringBuilder sb = new StringBuilder();

        for (CustomerImportDTO customerDTO : customersDTO) {

            Set<ConstraintViolation<CustomerImportDTO>> validate =
                    validator.validate(customerDTO);

            if (validate.isEmpty()) {

                if (customersList.containsKey(customerDTO.getEmail())) {

                    sb.append("Invalid customer");
                } else {

                    Customer customer = mapper.map(customerDTO, Customer.class);

                    Town town = townRepository.findByName(customerDTO.getTown().getName());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(customerDTO.getRegisteredOn(), formatter);

                    customer.setTown(town);
                    customer.setRegisterOn(date);

                    customersList.put(customer.getEmail(), customer);

                    sb.append(String.format("Successfully imported Customer %s - %s",
                            customer.getFirstName() + " " + customer.getLastName(), customer.getEmail()));
                }

            } else {

                sb.append("Invalid customer");
            }

            sb.append(System.lineSeparator());

        }

        return sb;
    }
}
