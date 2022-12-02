package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.PicturesImportDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidatePicturePath;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static softuni.exam.instagraphlite.util.ValidatePicturePath.validatePicturePath;

@Service
public class PictureServiceImpl implements PictureService {

    private PictureRepository pictureRepository;
    private Gson gson;
    private ModelMapper mapper;
    private Validator validator;
    private Map<String, Picture> picturesList = new LinkedHashMap<>();

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {

        this.pictureRepository = pictureRepository;

        this.gson = new Gson();

        this.mapper = new ModelMapper();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();


    }

    @Override
    public boolean areImported() {

        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {

        Path path = Path.of("exercise/src/main/resources/files/pictures.json").toAbsolutePath();

        return Files.readString(path);
    }

    @Override
    public String importPictures() throws IOException {

        String json = readFromFileContent();

        PicturesImportDTO[] picturesDTO = gson.fromJson(json, PicturesImportDTO[].class);

        StringBuilder result = createPictures(picturesDTO);

        pictureRepository.saveAll(picturesList.values());

        return result.toString();
    }

    private StringBuilder createPictures(PicturesImportDTO[] picturesDTO) {

        StringBuilder sb = new StringBuilder();

        for (PicturesImportDTO pictureDTO : picturesDTO) {

            Set<ConstraintViolation<PicturesImportDTO>> validate = validator.validate(pictureDTO);

            String picturePath = pictureDTO.getPath();

            List<String> imageErrors = validatePicturePath.validatePath(picturePath);

            if (!validate.isEmpty() || !imageErrors.isEmpty() || picturesList.containsKey(pictureDTO.getPath()) ) {

                sb.append("Invalid picture");
            } else {

               Picture picture = mapper.map(pictureDTO, Picture.class);

               picturesList.put(picture.getPath(), picture);

               sb.append(String.format("Successfully imported Picture, with size %.2f", picture.getSize()));
            }

            sb.append(System.lineSeparator());
        }

        return sb;
    }

    @Override
    public String exportPictures() {

        List<Picture> picturesList = pictureRepository.findBySizeGreaterThanOrderBySizeAsc(3000.00f);

        String result = picturesList.stream().map(this::pictureFormat).collect(Collectors.joining(System.lineSeparator()));

        return result;
    }

    private String pictureFormat(Picture picture){

        return String.format("%.2f - %s", picture.getSize(), picture.getPath());
    }
}
