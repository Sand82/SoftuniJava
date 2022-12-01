package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dtos.LaptopImportDTO;
import exam.model.entities.Laptop;
import exam.model.entities.Shop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class LaptopServiceImpl implements LaptopService {

    private LaptopRepository laptopRepository;
    private ShopRepository shopRepository;
    private Gson gson;
    private ModelMapper mapper;
    private Map<String, Laptop> laptopsList = new LinkedHashMap<>();
    private Validator validator;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository) {

        this.laptopRepository = laptopRepository;

        this.shopRepository = shopRepository;

        this.gson = new Gson();

        this.mapper = new ModelMapper();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {

        return laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {

        Path path = Path.of("skeleton/src/main/resources/files/json/laptops.json").toAbsolutePath();

        return Files.readString(path);
    }

    @Override
    public String importLaptops() throws IOException {

        String json = readLaptopsFileContent();

        LaptopImportDTO[] laptopsDTO = gson.fromJson(json, LaptopImportDTO[].class);

        StringBuilder result = createLaptops(laptopsDTO);

        laptopRepository.saveAll(laptopsList.values());

        return result.toString();
    }

    private StringBuilder createLaptops(LaptopImportDTO[] laptopsDTO) {

        StringBuilder sb = new StringBuilder();

        for (LaptopImportDTO laptopDTO : laptopsDTO) {

            Set<ConstraintViolation<LaptopImportDTO>> validate =
                    validator.validate(laptopDTO);

            if (validate.size() > 0 ||
                    laptopsList.containsKey(laptopDTO.getMacAddress()) ||
                    !laptopDTO.getWarrantyType().equals("BASIC") && !laptopDTO.getWarrantyType().equals("PREMIUM") && !laptopDTO.getWarrantyType().equals("LIFETIME")) {

                sb.append("Invalid laptop");
            } else {

              Laptop laptop = mapper.map(laptopDTO, Laptop.class);

              Shop shop = shopRepository.findByName(laptopDTO.getShop().getName());

              laptop.setShop(shop);

              laptopsList.put(laptop.getMacAddress(), laptop);

              sb.append(String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                      laptop.getMacAddress(), laptop.getCpuSpeed(), laptop.getRam(), laptop.getStorage()));
            }

            sb.append(System.lineSeparator());
        }


        return sb;
    }

    @Override
    public String exportBestLaptops() {
        return null;
    }
}
