package exam.service.impl;

import exam.model.dtos.ShopImportDTO;
import exam.model.dtos.ShopsImportDTO;
import exam.model.entities.Shop;
import exam.model.entities.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
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
public class ShopServiceImpl implements ShopService {

    private final String absolutePath = "C:\\SoftUni\\SpringDB\\JavaDBSpringDataRetakeExam-13August2021\\skeleton\\src\\main\\resources\\files\\xml\\shops.xml";
    private ShopRepository shopRepository;
    private TownRepository townRepository;
    private JAXBContext context;
    private Unmarshaller unmarshaller;
    private ModelMapper mapper;
    private Validator validator;
    private Map<String, Shop> shopsList = new LinkedHashMap<>();

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository) throws JAXBException {

        this.shopRepository = shopRepository;

        this.townRepository = townRepository;

        this.context = JAXBContext.newInstance(ShopsImportDTO.class);

        this.unmarshaller = context.createUnmarshaller();

        this.mapper = new ModelMapper();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {

        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {

        return Files.readString(Path.of(absolutePath));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {

        ShopsImportDTO shopsImportDTO = (ShopsImportDTO) unmarshaller.unmarshal(new FileReader(absolutePath));

        String result = shopsImportDTO.getShops().stream().map(this::createShopResult).collect(Collectors.joining(System.lineSeparator()));

        shopRepository.saveAll(shopsList.values());

        return result;
    }

    private String createShopResult(ShopImportDTO shopDTO) {

        Set<ConstraintViolation<ShopImportDTO>> validate = validator.validate(shopDTO);

        if (validate.isEmpty()) {

            if (shopsList.containsKey(shopDTO.getName())) {

                return "Invalid shop";
            }

            String shopName = shopDTO.getTown().getName();

            Town town = townRepository.findByName(shopName);

            Shop shop = mapper.map(shopDTO, Shop.class);

            shop.setTown(town);

            shopsList.put(shop.getName(), shop);

        } else {

            return "Invalid shop";
        }

        return String.format("Successfully imported Shop %s - %f", shopDTO.getName(), shopDTO.getIncome());
    }
}
