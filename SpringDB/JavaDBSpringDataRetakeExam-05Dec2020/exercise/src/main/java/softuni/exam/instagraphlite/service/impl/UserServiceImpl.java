package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.UsersImportDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PictureRepository pictureRepository;
    private Gson gson;
    private ModelMapper mapper;
    private Validator validator;
    private Map<String, User> usersList = new LinkedHashMap<>();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PictureRepository pictureRepository) {

        this.userRepository = userRepository;

        this.pictureRepository = pictureRepository;

        this.gson = new Gson();

        this.mapper = new ModelMapper();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {

        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {

        Path path = Path.of("exercise/src/main/resources/files/users.json");

        return Files.readString(path);
    }

    @Override
    public String importUsers() throws IOException {

        String json = readFromFileContent();

        UsersImportDTO[] usersDTO = gson.fromJson(json, UsersImportDTO[].class);

        StringBuilder result = createUsers(usersDTO);

        userRepository.saveAll(usersList.values());

        return result.toString();
    }

    private StringBuilder createUsers(UsersImportDTO[] usersDTO) {

        StringBuilder sb = new StringBuilder();

        for (UsersImportDTO userDTO : usersDTO) {

            if (!userDTO.validator().isEmpty()) {

                sb.append("Invalid user");
            } else {

                User user = mapper.map(userDTO, User.class);

                Picture picture = pictureRepository.findByPath(userDTO.getProfilePicture());

                if (picture == null) {

                    sb.append("Invalid user");
                } else {

                    user.setPicture(picture);

                    usersList.put(user.getUsername(), user);

                    sb.append(String.format("Successfully imported User: %s", user.getUsername()));
                }
            }

            sb.append(System.lineSeparator());

        }

        return sb;
    }

    @Override
    public String exportUsersWithTheirPosts() {

        List<User> userList = userRepository.findAll(Sort.by("id"));

        Comparator<User> comparator = Comparator.comparing(u -> u.getPosts().size());
        comparator.thenComparing(User::getId);

        userList.sort(comparator.reversed());

        StringBuilder result = createResult(userList);

        return result.toString();
    }

    private StringBuilder createResult(List<User> userList) {

        StringBuilder sb = new StringBuilder();

        for (User user : userList) {

            sb.append(String.format("User: %s", user.getUsername()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Post count: %d", user.getPosts().size()));
            sb.append(System.lineSeparator());
            sb.append(String.format("==Post Details:"));
            sb.append(System.lineSeparator());

            for (Post post : user.getPosts()) {

                sb.append(String.format("----Caption: %s", post.getCaption()));
                sb.append(System.lineSeparator());
                sb.append(String.format("----Picture Size: %.2f", post.getPicture().getSize()));
                sb.append(System.lineSeparator());
            }
            sb.append(System.lineSeparator());
        }

        return sb;
    }
}
